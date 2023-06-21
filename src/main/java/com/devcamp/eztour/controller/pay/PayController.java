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
    static final String PAY_APPV_READY= "8F"; //가상계좌 발급

    //결제상태 사용자 측 상태코드
    static final String PAY_STT_READY= "7A";             //결제 대기
    static final String PAY_STT_CANCELLED= "7B";         //결제 취소
    static final String PAY_STT_COMPLETE= "7C";          //결제 성공
    static final String PAY_STT_FAILED= "7D";            //결제 실패
    static final String PAY_STT_PREPARE = "7E";          //결제 준비중
    static final String PAY_STT_FORGERY_PRC = "7F";      //결제 위조 시도 - 금액
    static final String PAY_STT_FORGERY_MLG = "7G";      //결제 위조 시도 - 마일리지

    //예약 상태
    static final String RESERV_ACCEPT= "6A";              //예약접수
    static final String RESERV_APPV= "6B";                //예약승인
    static final String RESERV_RETURNED= "6C";            //예약반려
    static final String RESERV_CANCEL= "6D";              //예약취소
    static final String RESERV_COMPELET= "6E";            //예약완료
    static final String RESERV_UNACCEPT= "6F";            //예약불가
    static final String RESERV_ETC= "6G";                 //예약기타상태


    @GetMapping("/pay")
    public String getPayView(String rsvt_no, String prd_nm, HttpServletRequest req, HttpSession session, RedirectAttributes rttr, Model m){
        ReservDto reservDto=null;
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        String gst_id = (String) session.getAttribute("guest");

        UserDto userInfo = new UserDto();
        String usr_id;
        if(userDto!=null){
            usr_id = userDto.getUsr_id();
        } else {
            usr_id = gst_id;
        }
        userInfo.setUsr_id(usr_id);

        try {
            Map<String, String> map = new HashMap<>();
            map.put("rsvt_no", rsvt_no);
            map.put("usr_id", usr_id);
            reservDto = reservService.checkReservInfo(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (reservDto==null || !Objects.equals(reservDto.getCmn_cd_rsvt_stt(), RESERV_APPV)) {
            rttr.addFlashAttribute("msg", "INVALID_ACCESS");
            return "redirect:"+req.getHeader("Referer");
        }

        long pay_ftr_prc;
        GuestDto guestDto = null;
        try {
            if(userDto!=null){
                userInfo.setUsr_nm(userDto.getUsr_nm());
                userInfo.setEmail(userDto.getEmail());
                userInfo.setPhn(userDto.getPhn());
                userInfo.setMlg(reservService.getUserMlg(usr_id));
            } else {
                guestDto = payService.getGuestInfo(gst_id);
                userInfo.setUsr_nm(guestDto.getGst_nm());
                userInfo.setPhn(guestDto.getPhn());
                userInfo.setMlg(0);
            }

            pay_ftr_prc = reservService.getPayFtrPrc(rsvt_no);
            m.addAttribute("pay_ftr_prc", pay_ftr_prc);
            m.addAttribute("prd_nm", prd_nm);

            if(userDto!=null){
                m.addAttribute("userDto", userDto);
            } else {
                m.addAttribute("userDto", guestDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", URLEncoder.encode("login please 로그인페이지로 갑니다", StandardCharsets.UTF_8));
            return "/reserv/reservView.tiles";
        }

        return "pay/payView.tiles";
    }

    @ResponseBody
    @PostMapping("/prepare")
    public String preparePay(@RequestBody PayDto payDto, HttpSession session){
        JsonObject jsonResult = new JsonObject();
        String merchant_uid = UUID.randomUUID().toString();
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        String gst_id = (String) session.getAttribute("guest");

        String usr_id;
        if(userDto!=null){
            usr_id = userDto.getUsr_id();
        } else {
            usr_id = gst_id;
        }

        try {
            long pay_ftr_prc = reservService.getPayFtrPrc(payDto.getRsvt_no());
            int used_mlg = payDto.getUsed_mlg();
            long amount = pay_ftr_prc - used_mlg;

            PrepareData prepareData = new PrepareData(merchant_uid, new BigDecimal(amount));
            client.postPrepare(prepareData);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.addProperty("status", "fail");

            return jsonResult.toString();
        }

        payDto.setUsr_id(usr_id);
        payDto.setPay_no(merchant_uid);
        payDto.setCmn_cd_pay_stt(PAY_STT_READY);
        payDto.setCmn_cd_pay_appr(PAY_APPV_BEING_PROCESSED);
        payService.savePayInfo(payDto);

        jsonResult.addProperty("status", "true");
        jsonResult.addProperty("merchant_uid", merchant_uid);

        return jsonResult.toString();
    }

    @PostMapping("/test")
    public void test(){
        System.out.println("test success!");
    }
    //웹훅 있는 버전
    @ResponseBody
    @PostMapping("/webhookTest")
    public String saveWebhookResult(String imp_uid, String merchant_uid, HttpSession session){
        JsonObject jsonResult = new JsonObject();
        PayResultDto payResultDto;
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        long amount = 0;

        try {
            IamportResponse<Payment> payment_response = client.paymentByImpUid(imp_uid);
            amount = payment_response.getResponse().getAmount().longValue();

            PayViewDto payViewDto = payService.getMlgAndPrdInfo(merchant_uid);
            int used_mlg = payViewDto.getUsed_mlg();
            long pay_ftr_prc = payViewDto.getPay_ftr_prc();
            double amountToBePaid = pay_ftr_prc - used_mlg;

            if(amount==amountToBePaid){
                payResultDto = new PayResultDto(merchant_uid,
                        amount,
                        new Date(),
                        "card",
                        PAY_STT_COMPLETE,
                        PAY_APPV_SUCCESS,
                        RESERV_COMPELET,
                        imp_uid);

                if(userDto!=null){
                    reservService.updateUserMlg("minus", used_mlg, userDto.getUsr_id());
                }

                payService.savePayResult(payResultDto);//
                reservService.changeReservCount(payViewDto.getPrd_dtl_cd(), payViewDto.getRsvt_no(), "plus");

                jsonResult.addProperty("status", "success");
            } else {
                throw new PayForgeryException("결제금액 불일치");
            }
        }  catch (PayForgeryException e){
            try{
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
            }

            jsonResult.addProperty("status", "forgery");
        } catch (Exception e){
            //결제저장 안 된 경우
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

    //웹훅 없는 버전
    @ResponseBody
    @PostMapping("/saveResult")
    public String savePayResult(@RequestBody PayDto payDto, HttpSession session){
        JsonObject jsonResult = new JsonObject();
        String imp_uid = payDto.getImp_uid();
        PayResultDto payResultDto;
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        long amount = 0;

        try {
            IamportResponse<Payment> payment_response = client.paymentByImpUid(imp_uid);
            amount = payment_response.getResponse().getAmount().longValue();

            PayViewDto payViewDto = payService.getMlgAndPrdInfo(payDto.getPay_no());
            int used_mlg = payViewDto.getUsed_mlg();
            long pay_ftr_prc = payViewDto.getPay_ftr_prc();
            double amountToBePaid = pay_ftr_prc - used_mlg;

            if(amount==amountToBePaid){
                payResultDto = new PayResultDto(payDto.getPay_no(),
                        amount,
                        new Date(),
                        "card",
                        PAY_STT_COMPLETE,
                        PAY_APPV_SUCCESS,
                        RESERV_COMPELET,
                        payDto.getImp_uid());

                if(userDto!=null){
                    reservService.updateUserMlg("minus", used_mlg, userDto.getUsr_id());
                }

                payService.savePayResult(payResultDto);
                reservService.changeReservCount(payViewDto.getPrd_dtl_cd(), payDto.getRsvt_no(), "plus");

                jsonResult.addProperty("status", "success");
            } else {
                throw new PayForgeryException("결제금액 불일치");
            }
        }  catch (PayForgeryException e){
            try{
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

    @GetMapping("/confirm")
    public String getPayConfirmInfo(String rsvt_no, String prd_dtl_cd, Model m, HttpSession session, HttpServletRequest req){

        List list = reservService.getReservView(rsvt_no);

        ReservConfInfoDto rcid = null;
        PayDto payDto = null;
        List<TravelerInfoDto> trvlrInfoDtos = new ArrayList<>();

        for(Object obj: list){
            if(obj instanceof ReservConfInfoDto){
                rcid = (ReservConfInfoDto) obj;
                continue;
            }
            if(obj instanceof PayDto || obj == null){
                payDto = (PayDto) obj;
                continue;
            }
            trvlrInfoDtos.add((TravelerInfoDto) obj);
        }

        m.addAttribute("payDto", payDto);
        m.addAttribute("rcid", rcid);
        m.addAttribute("tid", trvlrInfoDtos);

        return "pay/payConfirm.tiles";
    }

    @GetMapping("/cnc")
    public String cancel(String rsvt_no, HttpSession session, Model m, HttpServletRequest req){
        try {
            UserDto userDto = (UserDto) session.getAttribute("userDto");
            String gst_id = (String) session.getAttribute("guest");

            String usr_id = "";
            if(userDto!=null){
                usr_id = userDto.getUsr_id();
            } else {
                usr_id = gst_id;
            }

            String status = payService.getPayStatus(rsvt_no, usr_id);
            if(status == null){
                ReservDto reservDto = new ReservDto(rsvt_no, RESERV_CANCEL, PAY_STT_CANCELLED);
                reservService.changeReservSttNCnt(reservDto);
                payService.deleteTrvlrList(rsvt_no);
                return "redirect:"+req.getHeader("referer");
            }

            CancelViewDto cancelViewDto = payService.getCancelInfo(rsvt_no);
            m.addAttribute("cncViewDto", cancelViewDto);

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:"+req.getHeader("referer");
        }
        return "pay/cancel.tiles";
    }

    @ResponseBody
    @PostMapping("/cnc")
    public String processCancel(@RequestBody CancelViewDto cancelViewDto, HttpSession session){
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        String gst_id = (String) session.getAttribute("guest");
        JsonObject jsonResult = new JsonObject();
        IamportResponse<Payment> payment_response;
        PayDto payDto;

        String usr_id = "";
        if(userDto!=null){
            usr_id = userDto.getUsr_id();
        } else {
            usr_id = gst_id;
        }


        try {
            payDto = payService.getPayInfo(cancelViewDto.getRsvt_no(), usr_id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.addProperty("status", "ACCESS_DENIED");
            return jsonResult.toString();
        }

        String status = payDto.getCmn_cd_pay_stt();

        if(PAY_STT_READY.equals(status) || PAY_STT_CANCELLED.equals(status)
            ||PAY_STT_FAILED.equals(status)||PAY_STT_PREPARE.equals(status)){

            jsonResult.addProperty("status", "ACCESS_DENIED");
            return jsonResult.toString();
        }

        if(!cancelViewDto.getPay_prc().equals(payDto.getPay_prc())){
            jsonResult.addProperty("status", "ACCESS_DENIED");
            return jsonResult.toString();
        }

        payDto.setCnc_rsn(cancelViewDto.getCnc_rsn());

        try {
            payment_response = client.paymentByImpUid(payDto.getImp_uid());
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.addProperty("status", "CANCEL_FAILED");
            return jsonResult.toString();
        }

        if(userDto!=null){
            reservService.updateUserMlg("plus", payDto.getUsed_mlg(), usr_id);
        }

        payDto.setPay_no(cancelViewDto.getNew_pay_no());
        long amount = payment_response.getResponse().getAmount().longValue();
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


    @GetMapping("/cncConfirm")
    public String cancelConfirm(){
        return "pay/cancelConfirm.tiles";
    }

    private boolean loginCheck(HttpSession session){
        UserDto userDto =(UserDto) session.getAttribute("userDto");
        String gst_id =(String) session.getAttribute("guest");
        return (userDto != null || gst_id != null);
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