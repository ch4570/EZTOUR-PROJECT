//package com.devcamp.eztour.controller;
//
//import com.devcamp.eztour.domain.reserv.*;
//import com.devcamp.eztour.domain.user.UserDto;
//import com.devcamp.eztour.service.reserv.PayService;
//import com.devcamp.eztour.service.reserv.ReservService;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.*;
//
//@Controller
//@RequestMapping("/pay")
//public class PayController {
//    @Autowired
//    ReservService reservService;
//    @Autowired
//    PayService payService;
//
//    //결제승인상태
////    static final String PAY_APPV_BEING_PROCESSED = "8A";   //결제중
////    static final String PAY_APPV_FAIL = "8B";              //결제 실패
//    static final String PAY_APPV_SUCCESS= "8C";            //결제 완료
////    static final String PAY_APPV_REFUND_FAIL= "8D";        //환불 실패
////    static final String PAY_APPV_REFUND_SUCCESS= "8E";     //환불 완료
//
//    //결제상태
//    static final String PAY_STT_READY= "7A";             //결제 대기
//    static final String PAY_STT_CANCELLED= "7B";         //결제 취소
//    static final String PAY_STT_COMPLETE= "7C";          //결제 완료
//    static final String PAY_STT_FAILED= "7D";            //결제 실패
//    static final String PAY_STT_PREPARE = "7E";          //결제 준비중
//    static final String PAY_STT_FORGERY_PRC = "7F";      //결제 위조 시도 - 금액
//    static final String PAY_STT_FORGERY_MLG = "7G";      //결제 위조 시도 - 마일리지
//    //예약 상태
//    static final String RESERV_ACCEPT= "6A";              //예약접수
//    static final String RESERV_APPV= "6B";                //예약승인
//    static final String RESERV_RETURNED= "6C";            //예약반려
//    static final String RESERV_CANCEL= "6D";              //예약취소
//    static final String RESERV_COMPELET= "6E";            //예약완료
//    static final String RESERV_UNACCEPT= "6F";            //예약불가
//    static final String RESERV_ETC= "6G";                 //예약기타상태
//
//    private static final String IMP_KEY = "0896863910828990";
//    private static final String IMP_SECRET = "cDWs1IcH29C6H5fLdVcTwbfPrcrWHKKN3BEFTn3r55bR97ULVeBZxAPiuLWPG3RUKxcGAkV1p1wDRyqd";
//
//    static final String ARL_STT_GO = "go";
////    @ExceptionHandler(Exception.class)
////    public String catcher(Exception e, RedirectAttributes rattr, HttpServletRequest req){
////        rattr.addFlashAttribute("msg", "PAY_FAIL");
////        System.out.println("exception handled");
////        return "redirect:"+req.getHeader("Referer");
////    }
////hibernate
////    @InitBinder
////    public void validate(WebDataBinder binder){
////        binder.setValidator(new PayValidator());
////    }
//
//    @GetMapping("/pay")
//    public String getPayView(String rsvt_no, String prd_dtl_cd, HttpServletRequest req, Model m, HttpSession session){
////        /pay/pay?rsvt_no=12312333312&ord_dtl_cd=a001001
////        로그인 체크
////        if(!checkId(session)){
////            return "redirect:/user/login?toURL="+req.getRequestURL();
////        }
//
//        UserDto userDto = (UserDto) session.getAttribute("userDto");
//
//        UserDto userInfo = new UserDto();
//        String usr_id = userDto.getUsr_id();
//        userInfo.setUsr_id(usr_id);
//
//        ////해당 예약에 대한 결제가 이미 진행되었나?////
//        String payStatus = payService.getPayStatus(rsvt_no, usr_id);
//        if (payStatus!=null) { //결제 이력이 이미 있다면(결제완료 or 결제 위변조 시도 둘 밖에 없음)
//            return "redirect:"+req.getHeader("Referer");
//            //msg를 어떻게 보낼까?
//            //이미 결제완료 되었거나 취소된 상품입니다.
//        }
//
//        //결제할 금액
//        long pay_ftr_prc = 0;
//
//        try {
//            //비회원
//            //mlg = 0, 이름만 가져옴, 휴대폰 번호
//            if(usr_id.toLowerCase().contains("guest")){
//                GuestDto guestDto = payService.getGuestInfo(usr_id);
//                userInfo.setUsr_nm(guestDto.getGst_nm());
//                userInfo.setPhn(guestDto.getPhn());
//                userInfo.setMlg(0);
//            } else {
//                //회원
//                //이름, 이메일, 마일리지, 휴대폰 번호
//                userInfo.setUsr_nm(userDto.getUsr_nm());
//                userInfo.setEmail(userDto.getEmail());
//                userInfo.setPhn(userDto.getPhn());
//                userInfo.setMlg(reservService.getUserMlg(usr_id));
//            }
//
//            pay_ftr_prc = reservService.getPayFtrPrc(rsvt_no);
//            m.addAttribute("pay_ftr_prc", pay_ftr_prc);
//            m.addAttribute("userDto", userDto);
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                m.addAttribute("msg", URLEncoder.encode("login please 로그인페이지로 갑니다", "utf-8"));
//            } catch (UnsupportedEncodingException ex) {
//                ex.printStackTrace();
//            }
//            return "/reserv/reservView";
//        }
//
//        return "pay/payView";
//    }
//
////    @PostMapping("/pay")
////    @ResponseBody
//    //param required = false 예외있으면
//    //WebDataBind 검사하고 에러 있으면
//    //error msg 띄워주고
//    // /pay/pay get 전페이지로 돌아감
//    //rsvt_no, prd_dtl_cd이거 가지고
////    public String savePayInfo(@RequestBody @Valid PayViewDto payViewDto, BindingResult result
////            , RedirectAttributes rattr, HttpServletRequest req){
////    public String savePayInfo(@RequestBody PayViewDto payViewDto, RedirectAttributes rattr, HttpServletRequest req){
//
////        if(payViewDto.getRsvt_no()==null|payViewDto.getPrd_dtl_cd()==null){
////            rattr.addFlashAttribute("msg", "PAY_FAIL");
////            return "fail";
////            //msg를어떻게 보낼까?
////            //유효하지 않은 예약 또는 상품입니다 문의전화해주세요
////        }
////
////        HttpSession session = req.getSession();
////        UserDto userDto = (UserDto) session.getAttribute("userDto");
////        String usr_id = userDto.getUsr_id();
////
////        String payStatus = payService.getPayStatus(payViewDto.getRsvt_no(), userDto.getUsr_id());
////        if (!(payStatus==null || PAY_STT_WAITING.equals(payStatus) || PAY_STT_PREPARE.equals(payStatus))) {
////            //미결제 상태? 이미 취소되었나? 예약만 한 상태인가?(=null)
////            return "redirect:"+req.getHeader("Referer");
////            //msg를 어떻게 보낼까?
////            //이미 결제완료 되었거나 취소된 상품입니다.
////        }
////
////        ///////////////마일리지////////////////////////
////        int realUserMlg = 0;
////        if(!usr_id.toLowerCase().contains("guest")){
////            //비회원이 아닐 때
////            try {
////                realUserMlg = reservService.getUserMlg(userDto.getUsr_id());
////            } catch (Exception e) {
////                e.printStackTrace();
////                //로그인페이지로 rsvt_no, prd_dtl_cd -> reserv/reservView
////            }
////        }
////
////        if(realUserMlg < payViewDto.getUsed_mlg()){
////            return "no";
////            //전 페이지로 돌아감
////            //msg 사용할 수 있는 마일리지의 범위를 초과하였습니다
////        }
////        /////////////////마일리지 확인////////////////
////        /////////////////물건 값 확인////////////////
////        try {
//////            long pay_ftr_prc = reservService.getPayFtrPrc(payViewDto.getPay_ftr_prc());
//////            //예약테이블의 pay_ftr_prc = payViewDto의 pay_ftr_prc + used_mlg
//////            long reserv_pay_ftr_prc = Long.parseLong(payViewDto.getPay_ftr_prc());
//////
//////            if(reserv_pay_ftr_prc != (pay_ftr_prc + payViewDto.getUsed_mlg())){
//////                return "wrong";
//////            }
////
////        } catch (Exception e) {
////            e.printStackTrace();
////            //catch문 다 모아놔야할 듯
////        }
////        /////////////////물건 값 확인////////////////
////        ////////////////결제번호 생성 및 결제정보 저장//////////////////////
////        String pay_no = String.valueOf(UUID.randomUUID());
////        PayDto payDto = new PayDto(pay_no, payViewDto.getRsvt_no(), payViewDto.getPrd_dtl_cd(), usr_id, payViewDto.getPay_prc()
////            ,new Date(), PAY_APPV_BEING_PROCESSED ,PAY_STT_WAITING, payViewDto.getPay_mthd(), payViewDto.getUsed_mlg());
////
////        try {
////            payService.savePayInfo(payDto);
////        } catch (Exception e) {
////            e.printStackTrace();
////            //전 페이지로 돌아감
////            //msg 다 입력해주세요
////        }
//        ////////////////결제번호 생성 및 결제정보 저장//////////////////////
//
//
//
////        if(result.hasErrors()){
////            System.out.println("실패!!!");
////            return "redirect:"+req.getHeader("Referer");
////        }
//
//        //null값을 validator로 잡아야할까?
//        //validator는 값의 유효성을 검사하기 위함
//        //controller에서 입력값의 유효성검사라는 관심사를 분리한 것이다.
//        //payDto에는 pay_prc, used_mlg, rsvt_no, pay_mthd, prd_dtl_cd
//
//
//        //usrDto에는 usr_nm,email
//        //넣어야하는 값 : pay_no(생성), usr_id, cmn_cd_pay_appr, cmn_cd_rsvt_stt
//
//        //usr_id가 두개의 참조변수에 들어가야되는데, 두개 다 들어갈까?
//
//
//        //마일리지 검사
//        // payDto
//        //userDto =
////        return pay_no;
////    }
//
//    @ResponseBody
//    @PostMapping("/saveResult")
//    public String savePayResult(@RequestBody PayDto payDto, HttpSession session, HttpServletResponse response){
//        UserDto userDto = (UserDto) session.getAttribute("userDto");
//        String usr_id = userDto.getUsr_id();
//
//        String imp_uid = payDto.getImp_uid();
//        String merchant_uid = payDto.getPay_no();
//
//        Map<String, Object> map = null;
//        JsonObject jsonResult = new JsonObject();
//
//        Double amount = 0d;
//        ///////////////마일리지////////////////////////
//        int realUserMlg = 0;
//
//        try {
//            if(!usr_id.toLowerCase().contains("guest")){
//                //비회원이 아닐 때
//                realUserMlg = reservService.getUserMlg(usr_id);
//            }
//            if(realUserMlg < payDto.getUsed_mlg()){
//                //이미 결제 후라서
//                //결제테이블에 상태값을 다르게 저장해서 관리자에게 알려서 관리자가 취소하게해야한다
//                //예외발생시킴
//                payDto.setCmn_cd_pay_stt(PAY_STT_FORGERY_MLG);
//                payDto.setCmn_cd_pay_appr(PAY_APPV_SUCCESS);
//                throw new MlgForgeryException("마일리지 범위 초과");
//            }
//        /////////////////마일리지 확인////////////////
//
//            String access_token = payService.getToken(IMP_KEY, IMP_SECRET);
//            map = payService.getPaymentData(imp_uid, access_token);
//
//            amount = (Double) map.get("amount");     //결제한 금액
//            Integer used_mlg = payDto.getUsed_mlg();    //사용한 마일리지
//            String status = (String) map.get("status");     //결제 상태
//
//            Long pay_ftr_prc_DB = reservService.getPayFtrPrc(payDto.getRsvt_no()); //DB에서 가져온 결제 예정 금액
//
//            //결제금액 비교
////            if(pay_ftr_prc_DB==(amount + used_mlg) && pay_ftr_prc_DB.equals(payDto.getPay_ftr_prc())){
//            if(true){
//                payDto.setCmn_cd_pay_stt(PAY_STT_COMPLETE);
//                payDto.setCmn_cd_pay_appr(PAY_APPV_SUCCESS);
//                //결제완료 상태 메시지 보내기 & 결제완료 상태코드 pay 테이블에 저장
////                res.send({ status: "success", message: "일반 결제 성공" });
//
//                //마일리지 차감 회원인경우에만
//                if(!usr_id.toLowerCase().contains("guest")){
//                    //비회원이 아닐 때
//                    //예외발생하면???
//                    reservService.updateUserMlg("minus", used_mlg, usr_id);
//                }
//                //예약상태 완료로 변경
//                reservService.updateRsvtStt(RESERV_COMPELET, PAY_STT_COMPLETE, payDto.getRsvt_no());
//                String msg = "결제가 정상적으로 처리되었습니다";
//                jsonResult.addProperty("status", "success");
//            } else {
//                payDto.setCmn_cd_pay_stt(PAY_STT_FORGERY_PRC);
//                payDto.setCmn_cd_pay_appr(PAY_APPV_SUCCESS);
//                // 결제금액 불일치. 위/변조 된 결제 & 결제위변조시도 상태코드 pay테이블에 저장
////                throw { status: "forgery", message: "위조된 결제시도" };
//                throw new PayForgeryException("결제금액 불일치");
//            }
//        } catch (PayForgeryException e){
//            String msg = "위조된 결제시도가 있습니다. 담당자 확인 후 결제가 처리됩니다.";
//            jsonResult.addProperty("status", "failed");
//            //위조시 예약상태 예약기타 불가 6G로
//            reservService.updateRsvtStt(RESERV_ETC, PAY_STT_FORGERY_PRC, payDto.getRsvt_no());
//        } catch (MlgForgeryException e){
//            String msg = "위조된 결제시도가 있습니다. 담당자 확인 후 결제가 처리됩니다.";
//            jsonResult.addProperty("status", "failed");
//            //위조시 예약상태 예약기타 불가 6G로
//            reservService.updateRsvtStt(RESERV_ETC, PAY_STT_FORGERY_PRC, payDto.getRsvt_no());
//        } catch (Exception e) {
//            e.printStackTrace();
//            //db에서 결제 예정금액 못가져오면 이곳으로 옴
//        }
//        //db에 정보저장
//
//        payDto.setUsr_id(usr_id);
//        payDto.setPay_prc(amount.longValue());
//        payDto.setPay_date(new Date()); //import에서 주는 값 확인하기
//        payDto.setPay_mthd("card"); //(String)map.get("pay_method")
//
//        payService.savePayInfo(payDto);
//        //예외 어떻게 처리행.. 앞에 validation처리를 하자
//
//        String result = jsonResult.toString();
//
//        return result;
//    }
//
//    @GetMapping("/confirm")
//    public String getPayConfirmInfo(String rsvt_no, String prd_dtl_cd, Model m){
//        List list = reservService.getReservView(rsvt_no, prd_dtl_cd);
//
//        ReservConfInfoDto rcid = null; //ReservCo
//        PayDto payDto = null;
//        List<TravelerInfoDto> trvlrInfoDtos = new ArrayList<>();
//        List<AirlineReqDto> airlineReqDtos = new ArrayList<>();
//
//
//        for(Object obj: list){
//            if(obj instanceof ReservConfInfoDto){
//                rcid = (ReservConfInfoDto) obj;
//                continue;
//            }
//            if(obj instanceof AirlineReqDto){
//                airlineReqDtos.add((AirlineReqDto) obj);
//                continue;
//            }
//            if(obj instanceof PayDto || obj == null){
//                payDto = (PayDto) obj;
//                continue;
//            }
//            trvlrInfoDtos.add((TravelerInfoDto) obj);
//        }
//
//        ////////비행기 시간 넣기//////////
//        AirlineReqDto goAirInfo = null;
//        AirlineReqDto backAirInfo = null;
//
//        if(airlineReqDtos.get(0).getArl_stt()==ARL_STT_GO){
//            goAirInfo = airlineReqDtos.get(0);
//            backAirInfo = airlineReqDtos.get(1);
//        } else {
//            goAirInfo = airlineReqDtos.get(1);
//            backAirInfo = airlineReqDtos.get(0);
//        }
//
//        rcid.setGo_dpr_arl_id(goAirInfo.getDpr_arl_id());
//        rcid.setGo_dpr_tm(goAirInfo.getDpr_tm());
//        rcid.setCb_arr_arl_id(backAirInfo.getArr_arl_id());
//        rcid.setCb_arr_tm(backAirInfo.getArr_tm());
//
//        m.addAttribute("payDto", payDto);
//        m.addAttribute("rcid", rcid);
//        m.addAttribute("tid", trvlrInfoDtos);
//
//        return "pay/payConfirm";
//    }
//
//
//}
//
//
//class PayForgeryException extends RuntimeException {
//    PayForgeryException(String msg){
//        super(msg);
//    }
//}
//
//class MlgForgeryException extends RuntimeException {
//    MlgForgeryException(String msg){
//        super(msg);
//    }
//}