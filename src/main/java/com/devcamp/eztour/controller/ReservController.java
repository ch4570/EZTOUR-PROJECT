package com.devcamp.eztour.controller;

import com.devcamp.eztour.domain.reserv.*;
import com.devcamp.eztour.domain.user.UserDto;
import com.devcamp.eztour.service.reserv.ReservService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/reserv")
public class ReservController {
    @Autowired
    ReservService reservService;

    static final String CMN_CD_ADT = "11A";
    static final String CMN_CD_CHD = "11B";
    static final String CMN_CD_BB = "11C";
    static final String ARL_STT_GO = "go";
    static final String RSVT_STT_PRPR = "6A"; //예약접수상태코드
    static final String PAY_STT_PRPR = "7E"; //결제 중비 중 코드
    int num = 1;

    @GetMapping("/reserv")
    public String getReservPage(String prd_dtl_cd, Model m, HttpSession session){
        if(!checkId(session) || prd_dtl_cd == null){
            //로그인하시겠습니까?
            //y --> 로그인페이지 --> 예약페이지
            //n --> 여행상품상세페이지로
            return "/"; //여행상품상세페이지로
        }

        try {
            List list = reservService.getReservInfo(prd_dtl_cd);

            ReservInfoDto rid = null;
            List<AirlineReqDto> arlReqList = new ArrayList<>();

            for(Object obj : list){
                if(obj instanceof ReservInfoDto){
                    rid = (ReservInfoDto) obj;
                    continue;
                }
                if(obj instanceof AirlineReqDto){
                    arlReqList.add((AirlineReqDto) obj);
                    continue;
                }
            }

            sortArlReq(arlReqList, rid);

            UserDto userDto = (UserDto) session.getAttribute("userDto");
            m.addAttribute("userDto", userDto);

            String emailStr[] = userDto.getEmail().split("@");
            m.addAttribute("emailFirst", emailStr[0]);
            m.addAttribute("emailLast",  emailStr[1]);

            m.addAttribute("rid", rid);
        } catch (Exception e) {
            e.printStackTrace();
            return "/";
        }

        return "reserv/reserv";
    }


    @PostMapping("/reserv")
    public String reserv(ReservDto reservDto, String emailFirst, String emailLast, String dstn_cd, String isUsrIncluded
        , int adt_prc, int chd_prc, int bb_prc, HttpServletRequest req, Model m){
        //유저아이디
//        HttpSession session = req.getSession();
//        String id = (String) session.getAttribute("isd");
        reservDto.setUsr_id("asdf"); //하드코딩 수정 필요

        String email = emailFirst+"@"+emailLast;
        reservDto.setEmail(email);
        reservDto.setPay_ftr_prc(reservDto.getSum_prc());
        reservDto.setCmn_cd_rsvt_stt(RSVT_STT_PRPR); //예약접수 상태 코드 넣어주기
        reservDto.setCmn_cd_pay_stt(PAY_STT_PRPR);
        //예약코드생성
        //여행지 코드 + 난수
        String rsvt_no = dstn_cd + makeRanNum();
        System.out.println("rsvtNo = " + rsvt_no);
        reservDto.setRsvt_no(rsvt_no);

        System.out.println("reservDto = " + reservDto);
        //여행자정보 넣기
        List<TravelerInfoDto> list = new ArrayList<>();
        List<TravelerInfoDto> infoList = new ArrayList<>();
        //adult
        infoList = setTrvlrInfo(CMN_CD_ADT, adt_prc, reservDto.getAdt_cnt(),reservDto,isUsrIncluded);
        list.addAll(infoList);
        //child
        infoList = setTrvlrInfo(CMN_CD_CHD, chd_prc, reservDto.getChd_cnt(), reservDto);
        list.addAll(infoList);
        //baby
        infoList = setTrvlrInfo(CMN_CD_BB, bb_prc, reservDto.getBb_cnt(), reservDto);
        list.addAll(infoList);
        num = 1;

        try {
            int rowCnt = reservService.reserv(reservDto);
            int rowCnt2 = reservService.saveTrvlrInfo(list);

//            if(rowCnt != 1) {
//                throw new Exception("reserv Exception");
//            }
//            return "reservConfirm";
        } catch (Exception e) {
            e.printStackTrace();
        }
        m.addAttribute("rsvt_no", rsvt_no);
        m.addAttribute("prd_dtl_cd",  reservDto.getPrd_dtl_cd());
        return "redirect:/reserv/conf";
    }

    @GetMapping("/conf")
    public String reservConf(String rsvt_no, String prd_dtl_cd, Model m) {
        //유저 확인 필요
        //rsvt_no, prd_dtl_cd 값 null check

        List list = reservService.getReservConfInfo(rsvt_no,prd_dtl_cd);
        ReservConfInfoDto rcid = null;
        List<TravelerInfoDto> trvlrInfoDtos = new ArrayList<>();
        List<AirlineReqDto> airlineReqDtos = new ArrayList<>();



        for(Object obj: list){
            if(obj instanceof ReservConfInfoDto){
                rcid = (ReservConfInfoDto) obj;
                System.out.println("rcid = " + rcid.getCmn_cd_rsvt_stt());
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

        //예외발생시
        //유저 아이디로 예약을 안했거나, 비행기 정보가 없어졌거나


        return "reserv/reservConfirm";
    }

    @GetMapping("/list")
    public String getReservList(HttpServletRequest req, Integer page, Integer pageSize, Model m){
        /////중복 처리할 것///////
//        HttpSession session = req.getSession();
//        Object id = session.getAttribute("id");
//
//        if(id == null){
//            String toURL = req.getRequestURI();
//            return "redirect:/user/login?toURL="+toURL; //로그인 화면으로
//        }
        /////중복 처리할 것///////
        if(page == null) page = 1;
        if(pageSize == null) pageSize = 10;


        String usr_id = (String)"asdf";
        Map<String, Object> map = new HashMap<>();
        map.put("usr_id", usr_id);
        map.put("offset", (page-1)/pageSize );
        map.put("pageSize", pageSize);

        try {
            List list = reservService.getReservListPage(map);
//            System.out.println("list = " + list);
            m.addAttribute("reservList", list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "reserv/reservList";
    }

    @GetMapping("/reservView")
    public String getRreserv(String rsvt_no, String prd_dtl_cd, HttpServletRequest req, Model m){
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



    private boolean checkId(HttpSession session) {
        return session.getAttribute("userDto") != null;
//        return true;
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

}

