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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
    static final String RSVT_STT_PRPR = "6A"; //예약접수상태코드
    static final String PAY_STT_PRPR = "7E"; //결제 중비 중 코드
    int travlr_cnt_num = 1;

    @GetMapping("/reserv")
    public String getReservPage(String prd_dtl_cd, Model m, RedirectAttributes rattr, HttpSession session){
        try {
            ReservInfoDto reservInfo = reservService.getReservInfo(prd_dtl_cd);

            UserDto userDto = (UserDto) session.getAttribute("userDto");

            if(userDto!=null){
                m.addAttribute("userDto", userDto);

                String[] emailStr = userDto.getEmail().split("@");
                m.addAttribute("emailFirst", emailStr[0]);
                m.addAttribute("emailLast",  emailStr[1]);
            }

            m.addAttribute("rid", reservInfo);
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "RSVT_FAILED");
            return "redirect:/product/list";
        }

        return "reserv/reserv.tiles";
    }


    @PostMapping("/reserv")
    public String reserv(ReservDto reservDto, String emailFirst, String emailLast, String dstn_cd, String isUsrIncluded
        , @RequestParam(required = true) int adt_prc
        , @RequestParam(required = true, defaultValue ="0") int chd_prc
        , @RequestParam(required = true, defaultValue ="0") int bb_prc
        , HttpSession session, HttpServletRequest req, RedirectAttributes rattr, Model m){

        UserDto userDto = (UserDto) session.getAttribute("userDto");
        String gst_id = (String) session.getAttribute("guest");

        if(userDto != null){
            reservDto.setUsr_id(userDto.getUsr_id());
        } else if(gst_id != null){
            reservDto.setUsr_id(gst_id);
            if(reservService.getReservCnt(gst_id) >= 1) {
                return "redirect:/reserv/reserv?prd_dtl_cd="+reservDto.getPrd_dtl_cd()+"&msg=GST_ONLY1PRD";
            }
        } else {
            gst_id = "guest" + makeRanNum();

            guestService.registerGuest(new GuestDto(gst_id, reservDto.getMn_rsvt_nm(), reservDto.getPhn()));

            session.setAttribute("guest", gst_id);
            session.setMaxInactiveInterval(60*30);

            reservDto.setUsr_id(gst_id);
        }

        reservDto.setEmail(emailFirst+"@"+emailLast);
        reservDto.setPay_ftr_prc(reservDto.getSum_prc());
        reservDto.setCmn_cd_rsvt_stt(RSVT_STT_PRPR);
        reservDto.setCmn_cd_pay_stt(PAY_STT_PRPR);

        String rsvt_no = dstn_cd + makeRanNum();
        reservDto.setRsvt_no(rsvt_no);

        List<TravelerInfoDto> list = new ArrayList<>();
        List<TravelerInfoDto> infoList = new ArrayList<>();

        list.addAll(setTrvlrInfo(CMN_CD_ADT, adt_prc, reservDto.getAdt_cnt(),reservDto,isUsrIncluded));
        list.addAll(setTrvlrInfo(CMN_CD_CHD, chd_prc, reservDto.getChd_cnt(), reservDto));
        list.addAll(setTrvlrInfo(CMN_CD_BB, bb_prc, reservDto.getBb_cnt(), reservDto));

        travlr_cnt_num = 1;

        try {
            reservService.saveReservInfo(reservDto, list);
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "RSVT_FAILED");
            return "redirect:"+req.getHeader("Referer");
        }

        return "redirect:/reserv/conf?rsvt_no="+rsvt_no+"&prd_dtl_cd="+reservDto.getPrd_dtl_cd();
    }

    @GetMapping("/conf")
    public String reservConf(String rsvt_no, String prd_dtl_cd, HttpServletRequest req, Model m) {
        if(rsvt_no == null || prd_dtl_cd == null){
            return "redirect:"+req.getHeader("Referer");
        }

        try {
            List list = reservService.getReservConfInfo(rsvt_no,prd_dtl_cd);
            ReservConfInfoDto rcid = null;
            List<TravelerInfoDto> trvlrInfoDtos = new ArrayList<>();


            for(Object obj: list){
                if(obj instanceof ReservConfInfoDto){
                    rcid = (ReservConfInfoDto) obj;
                    continue;
                }
                trvlrInfoDtos.add((TravelerInfoDto) obj);
            }

            m.addAttribute("rcid", rcid);
            m.addAttribute("tid", trvlrInfoDtos);
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "RSVT_WRONG");
            m.addAttribute("prd_dtl_cd", prd_dtl_cd);
            return "redirect:/reserv/reserv";
        }
        return "reserv/reservConfirm.tiles";
    }

    @GetMapping("/list")
    public String getReservList(HttpServletRequest req, Integer page, Integer pageSize, Model m, HttpSession session){
        if(page == null) page = 1;
        if(pageSize == null) pageSize = 10;

        UserDto userDto = (UserDto) session.getAttribute("userDto");
        String gst_id = (String) session.getAttribute("guest");

        String usr_id = "";
        if(userDto!=null){
            usr_id = userDto.getUsr_id();
        } else {
            usr_id = gst_id;
        }

        int totalReservCnt = reservService.getReservCnt(usr_id);
        PageHandler pageHandler = new PageHandler(page, totalReservCnt);

        Map<String, Object> map = new HashMap<>();
        map.put("usr_id", usr_id);
        map.put("offset", (page - 1) * pageSize);
        map.put("pageSize", pageSize);

        try {
            List list = reservService.getReservListPage(map);
            m.addAttribute("totalCnt", totalReservCnt);
            m.addAttribute("ph", pageHandler);
            m.addAttribute("reservList", list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "reserv/reservList.tiles";
    }



    @GetMapping("/reservView")
    public String getRreserv(String rsvt_no, String prd_dtl_cd, HttpServletRequest req, HttpSession session, Model m){
        if(!loginCheck(session)){
            return "redirect:/user/login?toURL="+req.getRequestURI();
        }

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

        return "reserv/reservView.tiles";
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

        jsonObject.addProperty("page", reservDto.getPage());
        jsonObject.addProperty("pageSize", reservDto.getPageSize());

        return jsonObject.toString();
    }

    @PostMapping("/gstLogin")
    public String guestLogin(String rsvt_no, String mn_rsvt_nm, String phn, HttpServletRequest req, Model m, HttpSession session){
        if(rsvt_no == null || mn_rsvt_nm == null || phn == null){
            return "redirect:"+ req.getRequestURL();
        }

        try {
            String gst_id= reservService.guestReservCheck(rsvt_no, mn_rsvt_nm, phn);
            session.setAttribute("guest", gst_id);
            session.setMaxInactiveInterval(60*30);
        } catch (Exception e) {
            e.printStackTrace();

            String msg = null;
            try {
                msg = URLEncoder.encode("입력하신 정보가 일치하지 않습니다.", "utf-8");
                String toURL = "toURL" + req.getRequestURL();
                m.addAttribute("msg", msg);
                m.addAttribute("toURL", toURL);
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            return "redirect:/login/login?toURL=" + req.getRequestURL();
        }
        return "redirect:/reserv/list";
    }

    private boolean isAdmin(HttpSession session){
        UserDto userDto = (UserDto)session.getAttribute("userDto");
        if(userDto!=null) {
            if ("Admin".equals(userDto.getRl()) || "supAdmin".equals(userDto.getRl())) {
                return true;
            }
        }
        return false;
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
                travlr_cnt_num++;
                continue;
            }

            tid.setTrvlr_nm("여행자" + travlr_cnt_num++);
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
        UserDto userDto =(UserDto) session.getAttribute("userDto");
        String gst_id =(String) session.getAttribute("guest");
        boolean result = (userDto != null || gst_id != null);
        return result;
    }

}

