package com.devcamp.eztour.service.pay;

import com.devcamp.eztour.dao.guest.GuestDao;
import com.devcamp.eztour.dao.pay.PayDao;
import com.devcamp.eztour.dao.reserv.TravelerInfoDao;
import com.devcamp.eztour.dao.user.UserDao;
import com.devcamp.eztour.domain.guest.GuestDto;
import com.devcamp.eztour.domain.pay.CancelViewDto;
import com.devcamp.eztour.domain.pay.PayDto;
import com.devcamp.eztour.domain.pay.PayResultDto;
import com.devcamp.eztour.domain.pay.PayViewDto;
import com.devcamp.eztour.domain.reserv.*;
import com.devcamp.eztour.domain.user.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    PayDao payDao;
    @Autowired
    GuestDao guestDao;
    @Autowired
    UserDao userDao;
    @Autowired
    TravelerInfoDao travelerInfoDao;

    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(20))
            .build();

    @Override
    public GuestDto getGuestInfo(String gst_id){
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
        Map<String, Object> responseMap;

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

        responseMap = (Map<String, Object>) gson.fromJson(result, Map.class).get("response");
        String code = gson.fromJson(result, Map.class).get("code").toString();

        if(!"0.0".equals(code)){
            throw new CancelException("환불 처리과정에서 문제가 발생하여 환불처리에 실패했습니다.");
        }

        return responseMap;
    }

    @Override
    public int deleteTrvlrList(String rsvt_no) {
        int rowCnt = 0;
        try {
            rowCnt = travelerInfoDao.deleteTrvlrInfoList(rsvt_no);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCnt;
    }

    @Override
    public PayViewDto getMlgAndPrdInfo(String pay_no){
        PayViewDto payViewDto = null;
        try {
            payViewDto = payDao.selectMlgAndPrdInfo(pay_no);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payViewDto;
    }

    @Override
    public void savePayResult(PayResultDto payResultDto) throws Exception{
        if(payDao.updatePayAndRsvtResult(payResultDto)==0){
            throw new Exception("결제 후 서버 데이터 저장이 정상적으로 처리되지 않았습니다.");
        }
    }

    @Override
    public List<StatsGndrAndAgePerHourDto> getGndrAndAgePerHour(){
        List<StatsGndrAndAgePerHourDto> list = null;
        try{
            list = payDao.selectGndrAndAgePerHour();
        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<StatsTopListDto> getTopNList(int limitNum){
        List<StatsTopListDto> list = null;
        try{
            list = payDao.selectTopNList(limitNum);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<StatsTopListDto> getTopNPrdLikelyPay(int limitNum) {
        List<StatsTopListDto> list = null;
        try{
            list = payDao.selectTopNPrdLikelyPay(limitNum);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}

class CancelException extends RuntimeException {
    CancelException(){}
    CancelException(String msg){
        super(msg);
    }
}