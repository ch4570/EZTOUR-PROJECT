package com.devcamp.eztour.controller;

import com.devcamp.eztour.domain.product.TrvPrdReadDto;
import com.devcamp.eztour.domain.reserv.*;
import com.devcamp.eztour.domain.user.UserDto;
import com.devcamp.eztour.service.reserv.GuestService;
import com.devcamp.eztour.service.reserv.ReservService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/reserv")
public class ReservController {
    @Autowired
    ReservService reservService;
    @Autowired
    GuestService guestService;

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

    static final String CMN_CD_ADT = "11A";
    static final String CMN_CD_CHD = "11B";
    static final String CMN_CD_BB = "11C";
    static final String ARL_STT_GO = "go";
    static final String RSVT_STT_PRPR = "6A"; //예약접수상태코드
    static final String PAY_STT_PRPR = "7E"; //결제 중비 중 코드
    int num = 1;

    @GetMapping("/reserv")
    public String getReservPage(String prd_dtl_cd, Model m, HttpSession session){
        try {
            ReservInfoDto reservInfoDto = null;

            List reservInfo = reservService.getReservInfo(prd_dtl_cd);
            List<AirlineReqDto> arlReqList = new ArrayList<>();

            for(Object obj : reservInfo){
                if(obj instanceof ReservInfoDto){
                    reservInfoDto = (ReservInfoDto) obj;
                    continue;
                }
                if(obj instanceof AirlineReqDto){
                    arlReqList.add((AirlineReqDto) obj);
                    continue;
                }
            }

            sortArlReq(arlReqList, reservInfoDto);

            UserDto userDto = (UserDto) session.getAttribute("userDto");
            if(userDto!=null){
                m.addAttribute("userDto", userDto);

                String emailStr[] = userDto.getEmail().split("@");
                m.addAttribute("emailFirst", emailStr[0]);
                m.addAttribute("emailLast",  emailStr[1]);
            }

            m.addAttribute("rid", reservInfoDto);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/";
        }

        return "reserv/reserv.tiles";
    }


    @PostMapping("/reserv")
    public String reserv(ReservDto reservDto, String emailFirst, String emailLast, String dstn_cd, String isUsrIncluded
        , @RequestParam(required = true) int adt_prc
        , @RequestParam(required = true, defaultValue ="0") int chd_prc
        , @RequestParam(required = true, defaultValue ="0") int bb_prc
        , HttpSession session, HttpServletRequest req, Model m){
        //validator 필요!!!!
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        UserDto guest = (UserDto) session.getAttribute("guest");
        //로그인 안했으면
        if(userDto==null && guest == null){
            //게스트를 등록하고 session에 guest로 등록하고
            String gst_id = "guest" + makeRanNum();

            guestService.registerGuest(new GuestDto(gst_id, reservDto.getMn_rsvt_nm(), reservDto.getPhn()));
            //유효성 검사 validator 추가
            reservDto.setUsr_id(gst_id);

            //guest session 추가
            UserDto guestDto = new UserDto(gst_id, reservDto.getMn_rsvt_nm(),
                    emailFirst+"@"+emailLast, null, reservDto.getPhn(), 0, null);
            session.setAttribute("guest", guestDto);
        } else {
            if(guest!=null){
                //게스트 로그인이 되어있으면
                //session을 사용중인 게스트가 하나 이상의 예약을 못하게 막음
                if(reservService.getReservCnt(guest.getUsr_id())>=1) return "redirect:/";
            }
            //회원로그인시
            reservDto.setUsr_id(userDto.getUsr_id());
        }

        String email = emailFirst+"@"+emailLast;
        reservDto.setEmail(email);
        reservDto.setPay_ftr_prc(reservDto.getSum_prc());
        reservDto.setCmn_cd_rsvt_stt(RSVT_STT_PRPR);
        reservDto.setCmn_cd_pay_stt(PAY_STT_PRPR);

        //예약코드생성
        String rsvt_no = dstn_cd + makeRanNum();
        reservDto.setRsvt_no(rsvt_no);

        //여행자정보 넣기
        List<TravelerInfoDto> list = new ArrayList<>();
        List<TravelerInfoDto> infoList = new ArrayList<>();

        //adult
        list.addAll(setTrvlrInfo(CMN_CD_ADT, adt_prc, reservDto.getAdt_cnt(),reservDto,isUsrIncluded));
        //child
        list.addAll(setTrvlrInfo(CMN_CD_CHD, chd_prc, reservDto.getChd_cnt(), reservDto));
        //baby
        list.addAll(setTrvlrInfo(CMN_CD_BB, bb_prc, reservDto.getBb_cnt(), reservDto));

        num = 1;

        try {
//            int rowCnt = reservService.reserv(reservDto);
//            int rowCnt2 = reservService.saveTrvlrInfo(list);
            reservService.saveReservInfo(reservDto, list);

//            if(rowCnt != 1) {
//                throw new Exception("reserv Exception");
//            }
//            return "reservConfirm";
        } catch (Exception e) {
            e.printStackTrace();
            //예약에 실패
            m.addAttribute("msg", "RSVT_FAILED");
            return "redirect:"+req.getHeader("Referer");
        }
        m.addAttribute("rsvt_no", rsvt_no);
        m.addAttribute("prd_dtl_cd",  reservDto.getPrd_dtl_cd());
        return "redirect:/reserv/conf";
    }

    @GetMapping("/conf")
    public String reservConf(String rsvt_no, String prd_dtl_cd, HttpServletRequest req, Model m) {
        //guest session 추가시 유저 확인 필요
        //rsvt_no, prd_dtl_cd 값 null check
        if(rsvt_no == null || prd_dtl_cd == null){
            return "redirect:/";
        }

        try {
            List list = reservService.getReservConfInfo(rsvt_no,prd_dtl_cd);
            ReservConfInfoDto rcid = null;
            List<TravelerInfoDto> trvlrInfoDtos = new ArrayList<>();
            List<AirlineReqDto> airlineReqDtos = new ArrayList<>();


            for(Object obj: list){
                if(obj instanceof ReservConfInfoDto){
                    rcid = (ReservConfInfoDto) obj;
                    continue;
                }
                if(obj instanceof AirlineReqDto){
                    airlineReqDtos.add((AirlineReqDto) obj);
                    continue;
                }
                trvlrInfoDtos.add((TravelerInfoDto) obj);
            }

            ////////비행기 시간 넣기//////////
            AirlineReqDto goAirInfo = null;
            AirlineReqDto backAirInfo = null;

            if(airlineReqDtos.get(0).getArl_stt()==ARL_STT_GO){
                goAirInfo = airlineReqDtos.get(0);
                backAirInfo = airlineReqDtos.get(1);
            } else {
                goAirInfo = airlineReqDtos.get(1);
                backAirInfo = airlineReqDtos.get(0);
            }

            rcid.setGo_dpr_arl_id(goAirInfo.getDpr_arl_id());
            rcid.setGo_dpr_tm(goAirInfo.getDpr_tm());
            rcid.setCb_arr_arl_id(backAirInfo.getArr_arl_id());
            rcid.setCb_arr_tm(backAirInfo.getArr_tm());
            ///////////////////////////////////
            m.addAttribute("rcid", rcid);
            m.addAttribute("tid", trvlrInfoDtos);
            m.addAttribute("alrd", airlineReqDtos);
        } catch (Exception e) {
            e.printStackTrace();
//            m.addAttribute("msg", "WRONG_RESERV");
//            m.addAttribute("prd_dtl_cd", prd_dtl_cd);
//            return "reserv/reserv";
            return "redirect:/";
        }
        return "reserv/reservConfirm";
    }

    @GetMapping("/list")
    public String getReservList(HttpServletRequest req, Integer page, Integer pageSize, Model m, HttpSession session){
        if(loginCheck(session)){
            return "redirect:/user/login?toURL="+req.getRequestURI(); //로그인 화면으로
        }

        if(page == null) page = 1;
        if(pageSize == null) pageSize = 10;

        UserDto userDto = (UserDto) session.getAttribute("userDto");
        UserDto guest = (UserDto) session.getAttribute("guest");

        String usr_id = "";
        if(userDto!=null){
            //회원
            usr_id = userDto.getUsr_id();
        } else {
            usr_id = guest.getUsr_id();
            System.out.println(usr_id);
        }


        int totalReservCnt = reservService.getReservCnt(usr_id);
        PageHandler pageHandler = new PageHandler(page, totalReservCnt);

        Map<String, Object> map = new HashMap<>();
        map.put("usr_id", usr_id);
        map.put("offset", pageHandler.getBeginPage()-1);
        map.put("pageSize", pageSize);

        try {
            List list = reservService.getReservListPage(map);
            m.addAttribute("ph", pageHandler);
            m.addAttribute("reservList", list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "reserv/reservList";
    }



    @GetMapping("/reservView")
    public String getRreserv(String rsvt_no, String prd_dtl_cd, HttpServletRequest req, HttpSession session, Model m){
        //rsvt_no=it1660418896171&prd_dtl_cd=a001001
        //prd_dtl_cd=a001001
        /////중복 처리할 것///////
//        HttpSession session = req.getSession();
//        Object id = session.getAttribute("id");
//
//        if(id == null){
//            String toURL = req.getRequestURI();
//            return "redirect:/user/login?toURL="+toURL; //로그인 화면으로
//        }
        /////중복 처리할 것///////
        if(loginCheck(session)){
            return "redirect:/user/login?toURL="+req.getRequestURI(); //로그인 화면으로
        }


        List list = reservService.getReservView(rsvt_no, prd_dtl_cd);

        ReservConfInfoDto rcid = null; //ReservCo
        PayDto payDto = null;
        List<TravelerInfoDto> trvlrInfoDtos = new ArrayList<>();
        List<AirlineReqDto> airlineReqDtos = new ArrayList<>();


        for(Object obj: list){
            if(obj instanceof ReservConfInfoDto){
                rcid = (ReservConfInfoDto) obj;
                continue;
            }
            if(obj instanceof AirlineReqDto){
                airlineReqDtos.add((AirlineReqDto) obj);
                continue;
            }
            if(obj instanceof PayDto || obj == null){
                payDto = (PayDto) obj;
                continue;
            }
            trvlrInfoDtos.add((TravelerInfoDto) obj);
        }

        ////////비행기 시간 넣기//////////
        AirlineReqDto goAirInfo = null;
        AirlineReqDto backAirInfo = null;

        if(airlineReqDtos.get(0).getArl_stt()==ARL_STT_GO){
            goAirInfo = airlineReqDtos.get(0);
            backAirInfo = airlineReqDtos.get(1);
        } else {
            goAirInfo = airlineReqDtos.get(1);
            backAirInfo = airlineReqDtos.get(0);
        }

        rcid.setGo_dpr_arl_id(goAirInfo.getDpr_arl_id());
        rcid.setGo_dpr_tm(goAirInfo.getDpr_tm());
        rcid.setCb_arr_arl_id(backAirInfo.getArr_arl_id());
        rcid.setCb_arr_tm(backAirInfo.getArr_tm());

        m.addAttribute("payDto", payDto);
        m.addAttribute("rcid", rcid);
        m.addAttribute("tid", trvlrInfoDtos);

        return "reserv/reservView";
    }

    @GetMapping("/admin")
    public String adminReservAppr(Integer page, Integer pageSize, HttpSession session,  Model m){
        boolean isAdmin = isAdmin(session);
        if(!isAdmin){
            return "redirect:/";
        }

        if(page == null) page = 1;
        if(pageSize == null) pageSize = 10;


        Map<String, Object> map = reservService.getTheUnAppredList(page, pageSize);
        List<ReservDto> list = (List<ReservDto>) map.get("unAppredList");
        m.addAttribute("list", list);
        m.addAttribute("ph", map.get("pageHandler"));

        return "product/reservApprList.tiles";
    }

    @PostMapping("/updateStt")
    @ResponseBody
    public String updateReservStt(@RequestBody ReservDto reservDto, HttpSession session){
        //관리자 확인
        //상태 업데이트
        //업데이트가 안되는건? 예약번호가 잘못되었기 때문인데
        boolean isAdmin = isAdmin(session);
        if(!isAdmin){
            return "redirect:/";
        }

        String rsvt_no = reservDto.getRsvt_no();
        JsonObject jsonObject = new JsonObject();
        int rowCnt = 0;

        if(RESERV_APPV.equals(reservDto.getCmn_cd_rsvt_stt())){
            rowCnt = reservService.updateRsvtStt(RESERV_APPV, PAY_STT_PRPR, rsvt_no);
        } else if(RESERV_RETURNED.equals(reservDto.getCmn_cd_rsvt_stt())) {
            rowCnt = reservService.updateRsvtStt(RESERV_RETURNED, PAY_STT_PRPR, rsvt_no);
        }

        if(rowCnt!=1){
            jsonObject.addProperty("status", "FAILED");
        } else {
            jsonObject.addProperty("status", "SUCCESS");
        }

        return jsonObject.toString();
    }



    private boolean checkId(HttpSession session) {
        return session.getAttribute("userDto") != null;
//        return true;
    }

    private boolean isAdmin(HttpSession session){
        UserDto userDto = (UserDto)session.getAttribute("userDto");
        if(userDto.getRl().equals("Admin") || userDto.getRl().equals("supAdmin")){
            return true;
        }else{
            return false;
        }
    }

    private void sortArlReq(List<AirlineReqDto> arlReqList, ReservInfoDto rid) {
        AirlineReqDto goAirInfo = null;
        AirlineReqDto backAirInfo = null;

        if(arlReqList.get(0).getArl_stt()==ARL_STT_GO){
            goAirInfo = arlReqList.get(0);
            backAirInfo = arlReqList.get(1);
        } else {
            goAirInfo = arlReqList.get(1);
            backAirInfo = arlReqList.get(0);
        }

        rid.setGo_dpr_arl_id(goAirInfo.getDpr_arl_id());
        rid.setGo_dpr_tm(goAirInfo.getDpr_tm());
        rid.setGo_arr_arl_id(goAirInfo.getArr_arl_id());
        rid.setGo_arr_tm(goAirInfo.getArr_tm());
        rid.setCb_dpr_arl_id(backAirInfo.getDpr_arl_id());
        rid.setCb_dpr_tm(backAirInfo.getDpr_tm());
        rid.setCb_arr_arl_id(backAirInfo.getArr_arl_id());
        rid.setCb_arr_tm(backAirInfo.getArr_tm());
    }

    private List<TravelerInfoDto> setTrvlrInfo(String ageStatus, int price, Integer count, ReservDto reservDto) {
        return setTrvlrInfo(ageStatus, price, count, reservDto, "n");
    }

    private List<TravelerInfoDto> setTrvlrInfo(String ageStatus, int price, Integer count, ReservDto reservDto, String isUsrIncluded) {
        List<TravelerInfoDto> list = new ArrayList<>();

        for(int i=0; i<count; i++){
            TravelerInfoDto tid = new TravelerInfoDto();
            tid.setRsvt_no(reservDto.getRsvt_no());


            if("y".equals(isUsrIncluded) && i==0){ //회원이 포함된 여행객정보 && 첫번쨰
                tid.setTrvlr_nm(reservDto.getMn_rsvt_nm());
                tid.setCmn_cd_age(ageStatus);
                tid.setPay_ftr_prc(price);
                list.add(tid);
                num++;
                continue;
            }

            tid.setTrvlr_nm("여행자" + num++);
            tid.setCmn_cd_age(ageStatus);
            tid.setPay_ftr_prc(price);
            list.add(tid);
        }
        return list;
    }

    private String makeRanNum() {
        int ranNum = (int)(Math.random() * 9000 + 1000);
        return ranNum+System.currentTimeMillis()+"";
    }

    private boolean loginCheck(HttpSession session){
        return !(session.getAttribute("userDto") == null || session.getAttribute("guest")==null);
    }

}

