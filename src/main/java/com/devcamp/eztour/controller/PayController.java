package com.devcamp.eztour.controller;

import com.devcamp.eztour.domain.reserv.*;
import com.devcamp.eztour.domain.user.UserDto;
import com.devcamp.eztour.service.reserv.PayService;
import com.devcamp.eztour.service.reserv.ReservService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import kotlinx.serialization.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/pay")
public class PayController {
    @Autowired
    ReservService reservService;
    @Autowired
    PayService payService;

    //결제승인상태
//    static final String PAY_APPV_BEING_PROCESSED = "8A";   //결제중
//    static final String PAY_APPV_FAIL = "8B";              //결제 실패
    static final String PAY_APPV_SUCCESS= "8C";            //결제 완료
//    static final String PAY_APPV_REFUND_FAIL= "8D";        //환불 실패
//    static final String PAY_APPV_REFUND_SUCCESS= "8E";     //환불 완료

    //결제상태
    static final String PAY_STT_READY= "7A";             //결제 대기 //환불안됨
    static final String PAY_STT_CANCELLED= "7B";         //결제 취소 //환불안됨
    static final String PAY_STT_COMPLETE= "7C";          //결제 완료
    static final String PAY_STT_FAILED= "7D";            //결제 실패 //환불안됨 //안씀
    static final String PAY_STT_PREPARE = "7E";          //결제 준비중 //환불안됨
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

    private static final String IMP_KEY = "0896863910828990";
    private static final String IMP_SECRET = "cDWs1IcH29C6H5fLdVcTwbfPrcrWHKKN3BEFTn3r55bR97ULVeBZxAPiuLWPG3RUKxcGAkV1p1wDRyqd";

    @GetMapping("/pay")
        public String getPayView(String rsvt_no, String prd_dtl_cd, String prd_nm, HttpServletRequest req, Model m, HttpSession session){

        UserDto userDto = (UserDto) session.getAttribute("userDto");
        String gst_id = (String) session.getAttribute("guest");

        UserDto userInfo = new UserDto();
        String usr_id = "";
        if(userDto!=null){
            usr_id = userDto.getUsr_id();
        } else {
            usr_id = gst_id;
        }
        userInfo.setUsr_id(usr_id);
        ////해당 예약에 대한 결제가 이미 진행되었나?////
        String payStatus = payService.getPayStatus(rsvt_no, usr_id);
        if (payStatus!=null) { //결제 이력이 이미 있다면(결제완료 or 결제 위변조 시도 둘 밖에 없음)
            return "redirect:"+req.getHeader("Referer");
            //msg를 어떻게 보낼까?
            //이미 결제완료 되었거나 취소된 상품입니다.
        }

        //결제할 금액
        long pay_ftr_prc = 0;
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
            try {
                m.addAttribute("msg", URLEncoder.encode("login please 로그인페이지로 갑니다", "utf-8"));
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            return "/reserv/reservView.tiles";
        }

        return "pay/payView.tiles";
    }

    @ResponseBody
    @PostMapping("/saveResult")
    public String savePayResult(@RequestBody PayDto payDto, HttpSession session, HttpServletResponse response){
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        String gst_id = (String) session.getAttribute("guest");

        String usr_id = "";
        if(userDto!=null){
            usr_id = userDto.getUsr_id();
        } else {
            usr_id = gst_id;
        }

        String imp_uid = payDto.getImp_uid();
        String merchant_uid = payDto.getPay_no();

        Map<String, Object> map = null;
        JsonObject jsonResult = new JsonObject();

        Double amount = 0d;
        ///////////////마일리지////////////////////////
        int realUserMlg = 0;

        try {
            if(userDto!=null){
                //비회원이 아닐 때
                realUserMlg = reservService.getUserMlg(usr_id);
            }
            if(realUserMlg < payDto.getUsed_mlg()){
                //이미 결제 후라서
                //결제테이블에 상태값을 다르게 저장해서 관리자에게 알려서 관리자가 취소하게해야한다
                //예외발생시킴
                payDto.setCmn_cd_pay_stt(PAY_STT_FORGERY_MLG);
                payDto.setCmn_cd_pay_appr(PAY_APPV_SUCCESS);
                throw new MlgForgeryException("마일리지 범위 초과");
            }
        /////////////////마일리지 확인////////////////

            String access_token = payService.getToken(IMP_KEY, IMP_SECRET);
            map = payService.getPaymentData(imp_uid, access_token);

            amount = (Double) map.get("amount");     //결제한 금액
            Integer used_mlg = payDto.getUsed_mlg();    //사용한 마일리지
            String status = (String) map.get("status");     //결제 상태

            Long pay_ftr_prc_DB = reservService.getPayFtrPrc(payDto.getRsvt_no()); //DB에서 가져온 결제 예정 금액

            //결제금액 비교
//            if(pay_ftr_prc_DB==(amount + used_mlg) && pay_ftr_prc_DB.equals(payDto.getPay_ftr_prc())){
            if(true){
                payDto.setCmn_cd_pay_stt(PAY_STT_COMPLETE);
                payDto.setCmn_cd_pay_appr(PAY_APPV_SUCCESS);
                //결제완료 상태 메시지 보내기 & 결제완료 상태코드 pay 테이블에 저장
//                res.send({ status: "success", message: "일반 결제 성공" });

                //마일리지 차감 회원인경우에만
                if(userDto!=null){
                    //비회원이 아닐 때
                    //예외발생하면???
                    reservService.updateUserMlg("minus", used_mlg, usr_id);
                }
                //예약상태 완료로 변경
                reservService.updateRsvtStt(RESERV_COMPELET, PAY_STT_COMPLETE, payDto.getRsvt_no());
                String msg = "결제가 정상적으로 처리되었습니다";
                jsonResult.addProperty("status", "success");
            } else {
                payDto.setCmn_cd_pay_stt(PAY_STT_FORGERY_PRC);
                payDto.setCmn_cd_pay_appr(PAY_APPV_SUCCESS);
                // 결제금액 불일치. 위/변조 된 결제 & 결제위변조시도 상태코드 pay테이블에 저장
//                throw { status: "forgery", message: "위조된 결제시도" };
                throw new PayForgeryException("결제금액 불일치");
            }
        } catch (PayForgeryException e){
            String msg = "위조된 결제시도가 있습니다. 담당자 확인 후 결제가 처리됩니다.";
            jsonResult.addProperty("status", "failed");
            //위조시 예약상태 예약기타 불가 6G로
            reservService.updateRsvtStt(RESERV_ETC, PAY_STT_FORGERY_PRC, payDto.getRsvt_no());
        } catch (MlgForgeryException e){
            String msg = "위조된 결제시도가 있습니다. 담당자 확인 후 결제가 처리됩니다.";
            jsonResult.addProperty("status", "failed");
            //위조시 예약상태 예약기타 불가 6G로
            reservService.updateRsvtStt(RESERV_ETC, PAY_STT_FORGERY_PRC, payDto.getRsvt_no());
        } catch (Exception e) {
            e.printStackTrace();
            //db에서 결제 예정금액 못가져오면 이곳으로 옴
        }
        //db에 정보저장

        payDto.setUsr_id(usr_id);
        payDto.setPay_prc(amount.longValue());
        payDto.setPay_date(new Date()); //import에서 주는 값 확인하기
        payDto.setPay_mthd("card"); //(String)map.get("pay_method")

        payService.savePayInfo(payDto);
        //예외 어떻게 처리행.. 앞에 validation처리를 하자
        reservService.changeReservCount(payDto.getPrd_dtl_cd(), payDto.getRsvt_no(), "plus");

        String result = jsonResult.toString();

        return result;
    }

    @GetMapping("/confirm")
    public String getPayConfirmInfo(String rsvt_no, String prd_dtl_cd, Model m, HttpSession session, HttpServletRequest req){

        List list = reservService.getReservView(rsvt_no);

        ReservConfInfoDto rcid = null; //ReservCo
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
                //회원
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

        String usr_id = "";
        if(userDto!=null){
            //회원
            usr_id = userDto.getUsr_id();
        } else {
            usr_id = gst_id;
        }

        JsonObject jsonResult = new JsonObject();

        String access_token = payService.getToken(IMP_KEY, IMP_SECRET);

        PayDto payDto = null;
        try {
            //유저가 다른 고객의 예약번호로 들어옴
            payDto = payService.getPayInfo(cancelViewDto.getRsvt_no(), usr_id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.addProperty("status", "ACCESS_DENIED");
            return jsonResult.toString();
        }

        String status = payDto.getCmn_cd_pay_stt();

        if(PAY_STT_READY.equals(status) || PAY_STT_CANCELLED.equals(status)
            ||PAY_STT_FAILED.equals(status)||PAY_STT_PREPARE.equals(status)){
            //환불이 안되는 상태코드 취소금액 컬럼 추가해서 금액으로 비교?
            jsonResult.addProperty("status", "ACCESS_DENIED");
            return jsonResult.toString();
        }

        if(!cancelViewDto.getPay_prc().equals(payDto.getPay_prc())){
            jsonResult.addProperty("status", "ACCESS_DENIED");
            return jsonResult.toString();
        }

        payDto.setCnc_rsn(cancelViewDto.getCnc_rsn());

        Map<String, Object> response = null;
        try {
            response = payService.cancelPay(payDto, access_token);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.addProperty("status", "CANCEL_FAILED");
            //reserv/reservView로 돌아감
            return jsonResult.toString();
        }

        if(userDto!=null){
            //마일리지 다시 돌려줌
            //회원인 경우에만
            reservService.updateUserMlg("plus", payDto.getUsed_mlg(), usr_id);
        }

        payDto.setPay_no(cancelViewDto.getNew_pay_no());
//        payDto.setPay_prc((Double.valueOf(response.get("amount"))).longValue());
        Double amount = (Double) response.get("amount");
        payDto.setPay_prc(amount.longValue());
        payDto.setPay_date(new Date());
        payDto.setCmn_cd_pay_appr(PAY_APPV_SUCCESS); //의미 없음
        payDto.setCmn_cd_pay_stt(PAY_STT_CANCELLED); //결제취소 성공
        payDto.setDvd_mnt(0);
        payDto.setUsed_mlg(0);

        //취소내역 pay에 저장
        payService.savePayInfo(payDto);
        //예약테이블에 상태 업데이트
        reservService.updateRsvtStt(RESERV_CANCEL, PAY_STT_CANCELLED, cancelViewDto.getRsvt_no());
        //상품상세 예약 인원 빼기
        reservService.changeReservCount(payDto.getPrd_dtl_cd(), payDto.getRsvt_no(), "minus");

        String result = jsonResult.toString();
        return result;
    }


    @GetMapping("/cncConfirm")
    public String cancelConfirm(){
        return "pay/cancelConfirm.tiles";
    }

    private boolean loginCheck(HttpSession session){
        UserDto userDto =(UserDto) session.getAttribute("userDto");
        String gst_id =(String) session.getAttribute("guest");
        boolean result = (userDto != null || gst_id != null);
        return result;
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