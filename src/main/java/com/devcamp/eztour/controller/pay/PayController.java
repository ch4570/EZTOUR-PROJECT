package com.devcamp.eztour.controller.pay;

import com.devcamp.eztour.common.ImportAPI;
import com.devcamp.eztour.common.PrepareData;
import com.devcamp.eztour.domain.guest.GuestDto;
import com.devcamp.eztour.domain.pay.CancelViewDto;
import com.devcamp.eztour.domain.pay.PayDto;
import com.devcamp.eztour.domain.pay.PayResultDto;
import com.devcamp.eztour.domain.pay.PayViewDto;
import com.devcamp.eztour.domain.reserv.*;
import com.devcamp.eztour.domain.user.UserDto;
import com.devcamp.eztour.service.pay.PayService;
import com.devcamp.eztour.service.reserv.ReservService;
import com.google.gson.JsonObject;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    ReservService reservService;
    @Autowired
    PayService payService;

    private final IamportClient client;

    public PayController(ImportAPI api){
        String impKey = api.getIMP_KEY();
        String impSecret = api.getIMP_SECRET();
        client = new IamportClient(impKey, impSecret);
    }

    //결제승인상태 서버 정보 기반 상태코드
    static final String PAY_APPV_BEING_PROCESSED = "8A";   //결제 중
    static final String PAY_APPV_FAIL = "8B";              //결제 실패
    static final String PAY_APPV_SUCCESS= "8C";            //결제 완료
    static final String PAY_APPV_REFUND_FAIL= "8D";        //환불 실패
    static final String PAY_APPV_REFUND_SUCCESS= "8E";     //환불 완료
    static final String PAY_APPV_READY= "8F";              //가상계좌 발급

    //결제상태 사용자 측 상태코드
    static final String PAY_STT_READY= "7A";               //결제 대기
    static final String PAY_STT_CANCELLED= "7B";           //결제 취소
    static final String PAY_STT_COMPLETE= "7C";            //결제 성공
    static final String PAY_STT_FAILED= "7D";              //결제 실패
    static final String PAY_STT_PREPARE = "7E";            //결제 준비중
    static final String PAY_STT_FORGERY_PRC = "7F";        //결제 위조 시도 - 금액
    static final String PAY_STT_FORGERY_MLG = "7G";        //결제 위조 시도 - 마일리지

    //예약 상태
    static final String RESERV_ACCEPT= "6A";                //예약접수
    static final String RESERV_APPV= "6B";                  //예약승인
    static final String RESERV_RETURNED= "6C";              //예약반려
    static final String RESERV_CANCEL= "6D";                //예약취소
    static final String RESERV_COMPELET= "6E";              //예약완료
    static final String RESERV_UNACCEPT= "6F";              //예약불가
    static final String RESERV_ETC= "6G";                   //예약기타상태


    //결제 페이지 진입
    @GetMapping("/pay")
    public String getPayView(String rsvt_no, String prd_nm, HttpServletRequest req, HttpSession session, RedirectAttributes rttr, Model m){
        ReservDto reservDto = null;

        //유저 or 게스트 id
        boolean isUser = verifyUser(session);
        String id = getUserOrGuestId(session);

        try {
            // 예약 번호와 아이디로 예약 정보 가져오기
            Map<String, String> map = new HashMap<>();
            map.put("rsvt_no", rsvt_no);
            map.put("usr_id", id);
            reservDto = reservService.checkReservInfo(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //예약 안됐다면, 또는 예약이 승인되지 않았다면 리턴
        if (reservDto==null || !reservDto.getCmn_cd_rsvt_stt().equals(RESERV_APPV)) {
            rttr.addFlashAttribute("msg", "INVALID_ACCESS");
            return "redirect:"+req.getHeader("Referer");
        }

        //사용자 정보 가져와서 넣기
        UserDto userInfo;
        GuestDto guestDto;
        UserDto userDto;

        try {
            userInfo = new UserDto();
            userInfo.setUsr_id(id);

            if(isUser){
                userDto = (UserDto) session.getAttribute("userDto");
                userInfo.setUsr_nm(userDto.getUsr_nm());
                userInfo.setEmail(userDto.getEmail());
                userInfo.setPhn(userDto.getPhn());
                userInfo.setMlg(reservService.getUserMlg(id));
                m.addAttribute("userDto", userDto);
            } else {
                guestDto = payService.getGuestInfo(id);
                userInfo.setUsr_nm(guestDto.getGst_nm());
                userInfo.setPhn(guestDto.getPhn());
                userInfo.setMlg(0);
                m.addAttribute("userDto", guestDto);
            }

            long pay_ftr_prc = reservService.getPayFtrPrc(rsvt_no);

            m.addAttribute("pay_ftr_prc", pay_ftr_prc);
            m.addAttribute("prd_nm", prd_nm);

        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", URLEncoder.encode("이용자 정보가 유효하지 않습니다. 예약페이지로 이동합니다", StandardCharsets.UTF_8));
            return "/reserv/reservView.tiles";
        }

        return "pay/payView.tiles";
    }

    //결제전 결제금액 사전 전달
    @ResponseBody
    @PostMapping("/prepare")
    public String preparePay(@RequestBody PayDto payDto, HttpSession session){
        JsonObject jsonResult = new JsonObject();

        String merchant_uid = generateUid();
        String id = getUserOrGuestId(session);

        try {
            //실제 결제금액  = 상품 금액 - 사용한 마일리지
            long pay_ftr_prc = reservService.getPayFtrPrc(payDto.getRsvt_no());
            int used_mlg = payDto.getUsed_mlg();
            long amount = pay_ftr_prc - used_mlg;

            //portOne에 결제금액 전달
            PrepareData prepareData = new PrepareData(merchant_uid, new BigDecimal(amount));
            client.postPrepare(prepareData);
        } catch (Exception e) {
            e.printStackTrace();

            jsonResult.addProperty("status", "fail");

            return jsonResult.toString();
        }

        //사전 결제 정보 저장
        payDto.setUsr_id(id);
        payDto.setPay_no(merchant_uid);
        payDto.setCmn_cd_pay_stt(PAY_STT_READY);
        payDto.setCmn_cd_pay_appr(PAY_APPV_BEING_PROCESSED);
        payService.savePayInfo(payDto);

        jsonResult.addProperty("status", "true");
        jsonResult.addProperty("merchant_uid", merchant_uid);

        return jsonResult.toString();
    }

    //웹훅 테스트
    @PostMapping("/test")
    public void test(){
        System.out.println("test success!");
    }

    //결제 성공 후 요청(웹훅 있는 버전)
    //PortOne에서 직접 요청
    @ResponseBody
    @PostMapping("/webhookTest")
    public String saveWebhookResult(String imp_uid, String merchant_uid, HttpSession session){
        JsonObject jsonResult = new JsonObject();
        boolean isUser = verifyUser(session);
        PayResultDto payResultDto;
        long amount = 0;

        try {
            //portOne 결제 결과
            IamportResponse<Payment> payment_response = client.paymentByImpUid(imp_uid);
            amount = payment_response.getResponse().getAmount().longValue();

            //결제 후 결제 금액 2차 확인
            PayViewDto payViewDto = payService.getMlgAndPrdInfo(merchant_uid);
            boolean isPaymentAmountMatching = matchPaymentAmount(amount, payViewDto);

            if(!isPaymentAmountMatching){
                throw new PayForgeryException("결제금액 불일치");
            }

            //user 마일리지 차감
            if(isUser){
                reservService.updateUserMlg("minus", payViewDto.getUsed_mlg(), getUserOrGuestId(session));
            }

            //결제 데이터 업데이트
            payResultDto = new PayResultDto(merchant_uid,
                    amount,
                    new Date(),
                    "card",
                    PAY_STT_COMPLETE,
                    PAY_APPV_SUCCESS,
                    RESERV_COMPELET,
                    imp_uid);

            payService.savePayResult(payResultDto);

            //상품 예약 인원 수 변경
            reservService.changeReservCount(payViewDto.getPrd_dtl_cd(), payViewDto.getRsvt_no(), "plus");

            jsonResult.addProperty("status", "success");

        }  catch (PayForgeryException e){
            try{
                //결제데이터 업데이트
                payResultDto = new PayResultDto(merchant_uid,
                        amount,
                        new Date(),
                        "card",
                        PAY_STT_FORGERY_PRC,
                        PAY_APPV_SUCCESS,
                        RESERV_ETC,
                        imp_uid);

                payService.savePayResult(payResultDto);
            } catch (Exception ex){
                ex.printStackTrace();
                //portOne서버에서 정보를 보내주지 않은 경우
            }

            jsonResult.addProperty("status", "forgery");
        } catch (Exception e){
            //portOne서버에서 정보를 보내주지 않은 경우
            //사전 결제 데이터 저장이 이루어지지 않은 경우
            e.printStackTrace();
        }
        return jsonResult.toString();
    }

    @ResponseBody
    @PostMapping("/webhookPayFail")
    public boolean handlePayFail(@RequestBody PayDto payDto){
        boolean result = false;

        try {
        //error_code를 portone에서 제공해주지 않아 PAY_STT_CANCELLED로 저장
        PayResultDto payResultDto = new PayResultDto(payDto.getPay_no(),
                0,
                new Date(),
                "card",
                PAY_STT_CANCELLED,
                PAY_APPV_FAIL,
                RESERV_APPV,
                payDto.getImp_uid());

            payService.savePayResult(payResultDto);
            result = true;
        } catch (Exception e) {
            //portone과 연결문제
            e.printStackTrace();
        }
        return result;
    }

    //결제 성공 후 요청(웹훅 없는 버전)
    @ResponseBody
    @PostMapping("/saveResult")
    public String savePayResult(@RequestBody PayDto payDto, HttpSession session){
        JsonObject jsonResult = new JsonObject();
        boolean isUser = verifyUser(session);
        PayResultDto payResultDto;
        long amount = 0;

        try {
            //portOne 결제 결과
            IamportResponse<Payment> payment_response = client.paymentByImpUid(payDto.getImp_uid());
            amount = payment_response.getResponse().getAmount().longValue();

            //결제 후 결제 금액 2차 확인
            PayViewDto payViewDto = payService.getMlgAndPrdInfo(payDto.getPay_no());
            boolean isPaymentAmountMatching = matchPaymentAmount(amount, payViewDto);

            if(!isPaymentAmountMatching){
                throw new PayForgeryException("결제금액 불일치");
            }

            //user 마일리지 차감
            if(isUser){
                reservService.updateUserMlg("minus", payViewDto.getUsed_mlg(), getUserOrGuestId(session));
            }

            //결제 결과 업데이터
            payResultDto = new PayResultDto(payDto.getPay_no(),
                    amount,
                    new Date(),
                    "card",
                    PAY_STT_COMPLETE,
                    PAY_APPV_SUCCESS,
                    RESERV_COMPELET,
                    payDto.getImp_uid());

            payService.savePayResult(payResultDto);
            //상품 예약 인원 수 변경
            reservService.changeReservCount(payViewDto.getPrd_dtl_cd(), payViewDto.getRsvt_no(), "plus");

            jsonResult.addProperty("status", "success");

        }  catch (PayForgeryException e){
            try{
                //결제 결과 업데이트
                payResultDto = new PayResultDto(payDto.getPay_no(),
                        amount,
                        new Date(),
                        "card",
                        PAY_STT_FORGERY_PRC,
                        PAY_APPV_SUCCESS,
                        RESERV_ETC,
                        payDto.getImp_uid());

                payService.savePayResult(payResultDto);
            } catch (Exception ex){
                ex.printStackTrace();
            }

            jsonResult.addProperty("status", "failed");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult.toString();
    }

    //결제 성공 후 결제 완료 화면 진입
    @GetMapping("/confirm")
    public String getPayConfirmInfo(@RequestParam(defaultValue = "") String rsvt_no, Model m){
        List list = null;

        try {
            list = reservService.getReservView(rsvt_no);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/pay/pay?rsvt_no"+rsvt_no;
        }

        ReservConfInfoDto rcid = null;
        PayDto payDto = null;
        List<TravelerInfoDto> trvlrInfoDtos = new ArrayList<>();

        for(Object obj: list){
            //예약 정보
            if(obj instanceof ReservConfInfoDto){
                rcid = (ReservConfInfoDto) obj;
                continue;
            }
            //결제 정보
            if(obj instanceof PayDto || obj == null){
                payDto = (PayDto) obj;
                continue;
            }
            //여행객 정보 불러오기
            trvlrInfoDtos.add((TravelerInfoDto) obj);
        }

        m.addAttribute("payDto", payDto);
        m.addAttribute("rcid", rcid);
        m.addAttribute("tid", trvlrInfoDtos);

        return "pay/payConfirm.tiles";
    }

    //결제 취소 화면 진입
    @GetMapping("/cnc")
    public String cancel(String rsvt_no, HttpSession session, Model m, HttpServletRequest req){
        String id = getUserOrGuestId(session);

        try {
            //결제 여부 확인
            if(!isPaid(rsvt_no, id)){
                return "forward:/reserv/cnc";
            }

            //결제취소 화면 준비
            CancelViewDto cancelViewDto = payService.getCancelInfo(rsvt_no);
            m.addAttribute("cncViewDto", cancelViewDto);

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:"+req.getHeader("referer");
        }
        return "pay/cancel.tiles";
    }

    //결제 취소 처리
    @ResponseBody
    @PostMapping("/cnc")
    public String processCancel(@RequestBody CancelViewDto cancelViewDto, HttpSession session){
        JsonObject jsonResult = new JsonObject();
        IamportResponse<Payment> payment_response;
        PayDto payDto;

        boolean isUser = verifyUser(session);
        String id = getUserOrGuestId(session);

        try {
            payDto = payService.getPayInfo(cancelViewDto.getRsvt_no(), id);
        } catch (Exception e) {
            //취소에 사용되는 결제 정보가 유효하지 않음
            e.printStackTrace();
            jsonResult.addProperty("status", "ACCESS_DENIED");
            return jsonResult.toString();
        }

        String status = payDto.getCmn_cd_pay_stt();

        //결제 완료된 상품인지 확인
        if(!status.equals(PAY_STT_COMPLETE)){
            jsonResult.addProperty("status", "ACCESS_DENIED");
            return jsonResult.toString();
        }

        //취소 금액과 결제 금액 일치 확인
        if(!isMatchPayment(cancelViewDto.getPay_prc(), payDto.getPay_prc())){
            jsonResult.addProperty("status", "ACCESS_DENIED");
            return jsonResult.toString();
        }

        try {
            //portOne 결제 취소
            payment_response = client.paymentByImpUid(payDto.getImp_uid());
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.addProperty("status", "CANCEL_FAILED");
            return jsonResult.toString();
        }

        //user 마일리지 되돌려주기
        if(isUser){
            reservService.updateUserMlg("plus", payDto.getUsed_mlg(), id);
        }

        long amount = payment_response.getResponse().getAmount().longValue();

        payDto.setPay_no(cancelViewDto.getNew_pay_no());
        payDto.setCnc_rsn(cancelViewDto.getCnc_rsn());
        payDto.setPay_prc(amount);
        payDto.setPay_date(new Date());
        payDto.setCmn_cd_pay_appr(PAY_APPV_SUCCESS);
        payDto.setCmn_cd_pay_stt(PAY_STT_CANCELLED);
        payDto.setDvd_mnt(0);
        payDto.setUsed_mlg(0);

        payService.savePayInfo(payDto);
        reservService.updateRsvtStt(RESERV_CANCEL, PAY_STT_CANCELLED, cancelViewDto.getRsvt_no());
        reservService.changeReservCount(payDto.getPrd_dtl_cd(), payDto.getRsvt_no(), "minus");
        payService.deleteTrvlrList(cancelViewDto.getRsvt_no());

        return jsonResult.toString();
    }

    //취소 완료 화면 진입
    @GetMapping("/cncConfirm")
    public String cancelConfirm(){
        return "pay/cancelConfirm.tiles";
    }

    private boolean loginCheck(HttpSession session){
        UserDto userDto =(UserDto) session.getAttribute("userDto");
        String gst_id =(String) session.getAttribute("guest");
        return (userDto != null || gst_id != null);
    }

    private String generateUid() {
        return UUID.randomUUID().toString();
    }

    private boolean verifyUser(HttpSession session) {
        return session.getAttribute("userDto") != null;
    }

    private String getUserOrGuestId(HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("userDto");

        if(userDto != null){
            return userDto.getUsr_id();
        }

        return (String) session.getAttribute("guest");
    }

    private boolean matchPaymentAmount(long amount, PayViewDto payViewDto) {
        int used_mlg = payViewDto.getUsed_mlg();
        long pay_ftr_prc = payViewDto.getPay_ftr_prc();

        double amountToBePaid = pay_ftr_prc - used_mlg;

        return amount == amountToBePaid;
    }

    private boolean isPaid(String rsvt_no, String id) throws Exception{
        PayDto payDto = payService.getPayInfo(rsvt_no, id);
        return payDto != null && payDto.getCmn_cd_pay_stt().equals(PAY_STT_COMPLETE);
    }

    private boolean isMatchPayment(Long cancleAmount, Long payPrc) {
        return Objects.equals(cancleAmount, payPrc);
    }
}




class PayForgeryException extends RuntimeException {
    PayForgeryException(String msg){
        super(msg);
    }
}

class MlgForgeryException extends RuntimeException {
    MlgForgeryException(String msg){
        super(msg);
    }
}

class savePayResultFailException extends RuntimeException {
    savePayResultFailException(String msg) {
        super(msg);}
}