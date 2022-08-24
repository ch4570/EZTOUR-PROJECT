package com.devcamp.eztour.service.reserv;

import com.devcamp.eztour.dao.reserv.GuestDao;
import com.devcamp.eztour.dao.reserv.PayDao;
import com.devcamp.eztour.dao.user.UserDao;
import com.devcamp.eztour.domain.reserv.*;
import com.devcamp.eztour.domain.user.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import retrofit2.http.HTTP;

import java.net.Authenticator;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    PayDao payDao;
    @Autowired
    GuestDao guestDao;
    @Autowired
    UserDao userDao;

    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(20))
            .build();

    @Override
    public GuestDto getGuestInfo(String gst_id){
        //바로 전에 로그인 확인을 했으니, 예외는 controller까지 가지 않아도 될 듯?
        GuestDto guestDto = null;
        try {
            guestDto = guestDao.selectGuest(gst_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return guestDto;
    }

    @Override
    public UserDto getUserInfo(String usr_id){
        UserDto userDto = null;
        try {
            userDto = null;
            userDto = userDao.selectUsr(usr_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDto;
    }

    @Override
    public String getPayStatus(String rsvt_no, String usr_id){
        Map<String, String> map = new HashMap<>();
        map.put("rsvt_no", rsvt_no);
        map.put("usr_id", usr_id);

        String pay_status = "";
        try {
            pay_status = payDao.selectPayStatus(map);
            //null값일 수 있음
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pay_status;
    }

    @Override
    public int savePayInfo(PayDto payDto){
        int rowCnt = 0;
        try {
            rowCnt = payDao.insertPay(payDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCnt;
        //예외발생은.. 값이 다 안왔을 떄임
        //validator처리해야
    }

    @Override
    public String getToken(String imp_key, String imp_secret){
        String token = "";

        //
        Map<String, String> map = new HashMap<>();
        map.put("imp_key", imp_key);
        map.put("imp_secret", imp_secret);

        try {
            // 참조 https://openjdk.org/groups/net/httpclient/recipes.html#jsonPost
            //map을 HttpReqeust의 BodyPublisher를 통해 값으로 넣는 방법
            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(map);

            // 참조 https://mkyong.com/java/java-11-httpclient-examples/
            //HttpClient를 통해 POST로 다른 서버에 요청을 보내는 예제
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .uri(URI.create("https://api.iamport.kr/users/getToken"))
                    .timeout(Duration.ofMinutes(2))
                    .build();

            CompletableFuture<HttpResponse<String>> response = HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            String result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);

            // 참조 https://sumin2.tistory.com/58
            // gson으로 JSON값을 읽어 오는 방법
            Gson gson = new Gson();
            String stringResponse = gson.fromJson(result, Map.class).get("response").toString();
            token = gson.fromJson(stringResponse, Map.class).get("access_token").toString();
        } catch (Exception e) {
            e.printStackTrace();
            //token을 받아오지 못하는 경우, 결제 취소로
        }

        return token;
    }

    @Override
    public Map<String, Object> getPaymentData(String imp_uid, String access_token){
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.iamport.kr/payments/"+imp_uid))
                .header("Authorization", access_token)
                .build();

        Map<String, Object> map = null;
        CompletableFuture<HttpResponse<String>> response = HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        try {
            String result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);
            System.out.println("result = " + result);

            Gson gson = new Gson();
            map = (Map<String, Object>) gson.fromJson(result, Map.class).get("response");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public CancelViewDto getCancelInfo(String rsvt_no) {
        CancelViewDto cancelViewDto = null;
        try {
            cancelViewDto = payDao.selectCancelInfo(rsvt_no);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cancelViewDto;
    }

    @Override
    public PayDto getPayInfo(String rsvt_no, String usr_id) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("rsvt_no", rsvt_no);
        map.put("usr_id", usr_id);
        return payDao.selectPayById(map);
    }

    @Override
    public Map<String, Object> cancelPay(PayDto payDto, String access_token) throws Exception {
        Map<String, Object> responseMap = null;

        Map<String, Object> map = new HashMap<>();
        map.put("reason", payDto.getCnc_rsn());
        map.put("imp_uid", payDto.getImp_uid());
        map.put("amount", payDto.getPay_prc()+"");

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(map);

        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Content-Type", "application/json")
                .setHeader("Authorization", access_token)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(URI.create("https://api.iamport.kr/payments/cancel"))
                .timeout(Duration.ofMinutes(2))
                .build();

        CompletableFuture<HttpResponse<String>> response = HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        String result = response.thenApply(HttpResponse::body).get(5, TimeUnit.SECONDS);

        Gson gson = new Gson();
        //여기서 값을 못가져옴
//            String stringResponse = gson.fromJson(result, Map.class).get("response").toString();
        responseMap = (Map<String, Object>) gson.fromJson(result, Map.class).get("response");
        String code = gson.fromJson(result, Map.class).get("code").toString();
//        int code = Integer.parseInt(responseMap.get("code"));

        if(!"0.0".equals(code)){
            throw new CancelException("환불 처리과정에서 문제가 발생하여 환불처리에 실패했습니다.");
        }

        return responseMap;
    }
}

class CancelException extends RuntimeException {
    CancelException(){}
    CancelException(String msg){
        super(msg);
    }
}
