package com.devcamp.eztour.controller.reserv;

import com.devcamp.eztour.domain.guest.GuestDto;
import com.devcamp.eztour.domain.pay.PayDto;
import com.devcamp.eztour.domain.product.TrvPrdPrcDto;
import com.devcamp.eztour.domain.reserv.*;
import com.devcamp.eztour.domain.user.UserDto;
import com.devcamp.eztour.service.guest.GuestService;
import com.devcamp.eztour.service.pay.PayService;
import com.devcamp.eztour.service.reserv.ReservService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Controller
@RequestMapping("/reserv")
public class ReservController {
    @Autowired
    ReservService reservService;
    @Autowired
    GuestService guestService;
    @Autowired
    PayService payService;

    //예약 상태
    static final String RESERV_ACCEPT= "6A";              //예약접수
    static final String RESERV_APPV= "6B";                //예약승인
    static final String RESERV_RETURNED= "6C";            //예약반려
    static final String RESERV_CANCEL= "6D";              //예약취소
    static final String RESERV_COMPELET= "6E";            //예약완료
    static final String RESERV_UNACCEPT= "6F";            //예약불가
    static final String RESERV_ETC= "6G";                 //예약기타상태

    //결제상태 사용자 측 상태코드
    static final String PAY_STT_READY= "7A";               //결제 대기
    static final String PAY_STT_CANCELLED= "7B";           //결제 취소
    static final String PAY_STT_COMPLETE= "7C";            //결제 성공
    static final String PAY_STT_FAILED= "7D";              //결제 실패
    static final String PAY_STT_PREPARE = "7E";            //결제 준비중
    static final String PAY_STT_FORGERY_PRC = "7F";        //결제 위조 시도 - 금액
    static final String PAY_STT_FORGERY_MLG = "7G";        //결제 위조 시도 - 마일리지


    static final String CMN_CD_ADT = "11A";
    static final String CMN_CD_CHD = "11B";
    static final String CMN_CD_BB = "11C";

    //예약 페이지 진입
    @GetMapping("/reserv")
    public String getReservPage(@RequestParam(defaultValue = "") String prd_dtl_cd, Model m, RedirectAttributes rattr, HttpSession session){
        //예약 상품 정보 가져오기
        try {
            ReservInfoDto reservInfo = reservService.getReservInfo(prd_dtl_cd);
            m.addAttribute("rid", reservInfo);
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "RSVT_FAILED");
            return "redirect:/product/list";
        }

        //예약자 기본 정보 불러오기
        if(isUser(session)){
            UserDto userDto = (UserDto) session.getAttribute("userDto");
            m.addAttribute("userDto", userDto);

            String email = userDto.getEmail();
            String[] emailStr = email.split("@");
            m.addAttribute("emailFirst", emailStr[0]);
            m.addAttribute("emailLast",  emailStr[1]);
        }

        return "reserv/reserv.tiles";
    }

    //예약 처리
    @PostMapping("/reserv")
    public String reserv(@Valid ReservDto reservDto, BindingResult result
            , String dstn_cd
            , String isUsrIncluded
            , HttpSession session, HttpServletRequest req, RedirectAttributes rattr){

        if(result.hasErrors()){
            rattr.addFlashAttribute("msg", "RSVT_FAILED");
            return "redirect:/reserv/reserv?prd_dtl_cd="+reservDto.getPrd_dtl_cd();
        }

        String id = getUserOrGuestId(session);

        if(isNewGuest(id)){
            id = generateGuestId();

            guestService.registerGuest(new GuestDto(id, reservDto.getMn_rsvt_nm(), reservDto.getPhn()));
            session.setAttribute("guest", id);
            session.setMaxInactiveInterval(60*30);
        } else if(isGuest(session)){
            if(haveReservHistory(id)){
                return "redirect:/reserv/reserv?prd_dtl_cd="+reservDto.getPrd_dtl_cd()+"&msg=GST_ONLY1PRD";
            }
        }

        String rsvt_no = generateRsvtNo(dstn_cd);
        reservDto.setRsvt_no(rsvt_no);
        reservDto.setUsr_id(id);
        reservDto.setCmn_cd_rsvt_stt(RESERV_ACCEPT);
        reservDto.setCmn_cd_pay_stt(PAY_STT_PREPARE);


        try {
            TrvPrdPrcDto trvPrdPrcDto = reservService.getOneProductPriceByPrdDtlCd(reservDto.getPrd_dtl_cd());

            int sumPrc = getTotalPrice(trvPrdPrcDto, reservDto);
            reservDto.setPay_ftr_prc(sumPrc);

            List<TravelerInfoDto> list = setTrvlrInfo(reservDto
                    , trvPrdPrcDto.getAdt_prc()
                    , trvPrdPrcDto.getChd_prc()
                    , trvPrdPrcDto.getBb_prc()
                    , isUsrIncluded);

            reservService.saveReservInfo(reservDto, list);
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "RSVT_FAILED");
            return "redirect:"+req.getHeader("Referer");
        }

        return "redirect:/reserv/conf?rsvt_no="+rsvt_no+"&prd_dtl_cd="+reservDto.getPrd_dtl_cd();
    }

    //예약 후 확인 페이지 진입
    @GetMapping("/conf")
    public String reservConf(@RequestParam(defaultValue = "") String rsvt_no
            , @RequestParam(defaultValue = "") String prd_dtl_cd
            , HttpServletRequest req
            , RedirectAttributes rattr
            , Model m) {

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
            rattr.addFlashAttribute("msg", "RSVT_WRONG");
            return "redirect:/reserv/reserv?prd_dtl_cd="+prd_dtl_cd;
        }
        return "reserv/reservConfirm.tiles";
    }

    //예약 목록 페이지 진입
    @GetMapping("/list")
    public String getReservList(Integer page, Integer pageSize, Model m, HttpSession session){
        if(page == null) page = 1;
        if(pageSize == null) pageSize = 10;

        String id = getUserOrGuestId(session);

        int totalReservCnt = reservService.getReservCnt(id);
        PageHandler pageHandler = new PageHandler(page, totalReservCnt);

        Map<String, Object> map = new HashMap<>();
        map.put("usr_id", id);
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

    //예약 내역 페이지 진입
    @GetMapping("/reservView")
    public String getRreserv(String rsvt_no, HttpServletRequest req, HttpSession session, Model m){
        if(!loginCheck(session)){
            return "redirect:/user/login?toURL="+req.getRequestURI();
        }

        List list;
        try {
            list = reservService.getReservView(rsvt_no);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:"+req.getHeader("referer");
        }

        ReservConfInfoDto rcid = null;
        PayDto payDto = null;
        List<TravelerInfoDto> trvlrInfoDtos = new ArrayList<>();

        //예약 확인 내역, 결제 정보, 여행자 정보 가져오기
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

    //관리자 예약 승인 페이지 진입
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

    //관리자 예약 처리
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
            rowCnt = reservService.updateRsvtStt(RESERV_APPV, PAY_STT_PREPARE, rsvt_no);
        } else if(RESERV_RETURNED.equals(reservDto.getCmn_cd_rsvt_stt())) {
            rowCnt = reservService.updateRsvtStt(RESERV_RETURNED, PAY_STT_PREPARE, rsvt_no);
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

    //예약 취소 처리
    @GetMapping("/cnc")
    public String reservCancel(String rsvt_no, RedirectAttributes rattr, HttpServletRequest req){
        ReservDto reservDto = new ReservDto(rsvt_no, RESERV_CANCEL, PAY_STT_CANCELLED);
        try{
            reservService.cancelReserv(reservDto);
        } catch (Exception e){
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "CNC_FAILED");
            return "redirect:/reserv/reservView?rsvt_no="+rsvt_no;
        }

        return "redirect:"+req.getHeader("referer");
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
            msg = URLEncoder.encode("입력하신 정보가 일치하지 않습니다.", StandardCharsets.UTF_8);
            String toURL = "toURL" + req.getRequestURL();
            m.addAttribute("msg", msg);
            m.addAttribute("toURL", toURL);
            return "redirect:/login/login?toURL=" + req.getRequestURL();
        }
        return "redirect:/reserv/list";
    }

    private boolean isUser(HttpSession session) {
        return session.getAttribute("userDto") != null;
    }

    private String generateRsvtNo(String dstn_cd) {
        return dstn_cd + makeRanNum();
    }

    private boolean haveReservHistory(String id) {
        return reservService.getReservCnt(id) >= 1;
    }

    private boolean isNewGuest(String id) {
        return id == null;
    }

    private boolean isGuest(HttpSession session) {
        return session.getAttribute("guest") != null;
    }

    private String getUserOrGuestId(HttpSession session) {
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        if(userDto != null){
            return userDto.getUsr_id();
        }
        return (String) session.getAttribute("guest");
    }

    private int getTotalPrice(TrvPrdPrcDto trvPrdPrcDto, ReservDto reservDto) {
        return (trvPrdPrcDto.getAdt_prc() * reservDto.getAdt_cnt())
                + (trvPrdPrcDto.getChd_prc() * reservDto.getChd_cnt())
                + (trvPrdPrcDto.getBb_prc() * reservDto.getBb_cnt());
    }

    private boolean isAdmin(HttpSession session){
        UserDto userDto = (UserDto)session.getAttribute("userDto");
        if(userDto!=null) {
            return "Admin".equals(userDto.getRl()) || "supAdmin".equals(userDto.getRl());
        }
        return false;
    }

    private List<TravelerInfoDto> setTrvlrInfo(ReservDto reservDto, int adt_prc, int chd_prc, int bb_prc, String isUsrIncluded){
        List<TravelerInfoDto> list = new ArrayList<>();
        String rsvtNo = reservDto.getRsvt_no();
        int count = 1;
        int adtCnt = reservDto.getAdt_cnt();

        if("true".equals(isUsrIncluded)){
            list.add(new TravelerInfoDto(rsvtNo, reservDto.getMn_rsvt_nm(), CMN_CD_ADT, adt_prc));
            adtCnt--;
            count++;
        }

        count = addTrvlrList(rsvtNo, CMN_CD_ADT, adt_prc, count, adtCnt, list);
        count = addTrvlrList(rsvtNo, CMN_CD_CHD, chd_prc, count, reservDto.getChd_cnt(), list);
        addTrvlrList(rsvtNo, CMN_CD_BB, bb_prc, count, reservDto.getBb_cnt(), list);

        return list;
    }

    private int addTrvlrList(String rsvtNo, String ageCode, long payFtrPrc, Integer count, int headCount, List<TravelerInfoDto> list){
        for(int i = 0; i < headCount; i++){
            list.add(new TravelerInfoDto(rsvtNo, "여행자" + count++, ageCode, payFtrPrc));
        }
        return count;
    }

    private String generateGuestId() {
        return "guest" + makeRanNum();
    }

    private String makeRanNum() {
        int ranNum = (int)(Math.random() * 9000 + 1000);
        return ranNum+System.currentTimeMillis()+"";
    }

    private boolean loginCheck(HttpSession session){
        UserDto userDto =(UserDto) session.getAttribute("userDto");
        String gst_id =(String) session.getAttribute("guest");
        return userDto != null || gst_id != null;
    }
}

