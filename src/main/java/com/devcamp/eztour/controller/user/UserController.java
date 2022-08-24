package com.devcamp.eztour.controller.user;

import com.devcamp.eztour.domain.user.NaverLoginBO;
import com.devcamp.eztour.domain.user.UserDto;
import com.devcamp.eztour.service.reserv.ReservService;
import com.devcamp.eztour.service.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    NaverLoginBO naverloginbo;
    @Autowired
    ReservService reservService;

    @GetMapping("/selectJoin")
    public String selectJoin(HttpSession session, Model m, RedirectAttributes rattr) {
        if(session.getAttribute("userDto")==null) {
            String naverAuthUrl = naverloginbo.getAuthorizationUrl(session);
            m.addAttribute("naverUrl", naverAuthUrl);
            return "user/selectJoin.tiles";
        }else {
            rattr.addFlashAttribute("msg", "ACC_ERR");
            return "redirect:/";
        }
    }

    @GetMapping("/auth")
    public String auth() {
        return "user/auth.tiles";
    }

    // 인증번호 맞을시 이름과 폰번호를 들고 redirect
    @PostMapping("/authOk")
    public String authOk(String usr_nm, String phn) throws Exception {
        usr_nm = URLEncoder.encode(usr_nm, "utf-8");
        return "redirect:/user/join?usr_nm="+usr_nm+"&phn="+phn;
    }

    @GetMapping("/join")
    public String join(HttpSession session, RedirectAttributes rattr, String usr_nm, String phn, Model m, HttpServletResponse response) {
        if(session.getAttribute("userDto")==null && usr_nm != null && phn != null) {
            m.addAttribute("usr_nm", usr_nm);
            m.addAttribute("phn", phn);
            return "user/join.tiles";
        }
        rattr.addFlashAttribute("msg", "ACC_ERR");
        return "redirect:/";
    }

    @PostMapping("/join")
    public String join(UserDto user, RedirectAttributes rattr, HttpSession session) {

        String gndr = user.getGndr();
        if(user.getGndr()!=null && user.getGndr().equals("F")||user.getGndr().equals("female")){
            gndr = "여성";
        }else if(user.getGndr()!=null && user.getGndr().equals("M")||user.getGndr().equals("male")){
            gndr = "남성";
        }
        user.setGndr(gndr);
        System.out.println("user = " + user);

        try {
            int rowCnt = userService.insertUsr(user);
            if (rowCnt != 1)
                throw new Exception("user insert error");
            rattr.addFlashAttribute("msg", "REG_OK");
            return "redirect:/";
        } catch (Exception e) {

            e.printStackTrace();
            rattr.addFlashAttribute("msg", "REG_ERR");
            return "redirect:/user/join";
        }
    }

    @GetMapping("/login")
    public String loginView(HttpSession session, Model m, RedirectAttributes rattr) {
        if(session.getAttribute("userDto")==null) {
            String naverAuthUrl = naverloginbo.getAuthorizationUrl(session);
            m.addAttribute("naverUrl", naverAuthUrl);
            return "user/login.tiles";
        }else {
            rattr.addFlashAttribute("msg", "ACC_ERR");
            return "redirect:/";
        }
    }

    @PostMapping("/login")
    public String login(String usr_id, String pwd, boolean rememberId,
                        HttpSession session, HttpServletResponse response) throws Exception {

        UserDto userDto = userService.selectUsr(usr_id);
        System.out.println(usr_id);

        if(!(userDto!=null && userDto.getPwd().equals(pwd))) {
            String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");

            return "redirect:/user/login?msg="+msg;
        }

        userService.updateHstForLogin(usr_id); // 예외처리 예정 (에러 발생시 세션 안넘기고 에러메세지 + 메인 이동)
        UserDto loginUser = new UserDto(userDto.getUsr_id(), userDto.getUsr_nm(),
                                                 userDto.getEmail(), userDto.getRl(), userDto.getPhn(), userDto.getMlg(), userDto.getCmn_cd_prf_img());
        session.setAttribute("userDto", loginUser);

        if(rememberId) {
            Cookie cookie = new Cookie("id", usr_id);
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("id", usr_id);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        String toURL = (String) session.getAttribute("toURL");
        toURL = toURL==null || toURL.equals("") ? "/" : toURL;
        return "redirect:"+toURL;

    }

    @GetMapping("/logout")
    public String logout(HttpSession session, String msg, RedirectAttributes rattr) {
        session.invalidate();
        if(msg!=null)
            rattr.addFlashAttribute("msg",msg);

        return "redirect:/";
    }

    @GetMapping("/mypage")
    public String mypage(HttpSession session, RedirectAttributes rattr, Model m) {
//        UserDto userDto = new UserDto();
//        if(session.getAttribute("userDto")!= null) {
//            userDto = (UserDto) session.getAttribute("userDto");
//        }else{
//            rattr.addFlashAttribute("msg", "ACC_ERR");
//            return "redirect:/";
//        }

        UserDto userDto = new UserDto();
        userDto = (UserDto) session.getAttribute("userDto");

        Map map = new HashMap<>();
        map.put("usr_id",userDto.getUsr_id());
        map.put("offset",0);
        map.put("pageSize",2);

        try {
            List rsrvlist =  reservService.getReservListPage(map);
            System.out.println(rsrvlist);
            m.addAttribute("rsrvlist", rsrvlist);

            userDto = userService.selectUsr(userDto.getUsr_id());
            m.addAttribute("prfImg",userDto.getCmn_cd_prf_img());
            m.addAttribute("mlg",userDto.getMlg());

            List<Map> paylist = userService.selectPaylogForMypage(userDto.getUsr_id());
            m.addAttribute("paylist", paylist);

            return "user/mypage.tiles";
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "ACC_ERR");
            return "redirect:/user/login";
        }
    }

    @GetMapping("/usrMod")
    public String usrModView(HttpSession session, RedirectAttributes rattr, Model model){
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        try {
            userDto = userService.selectUsr(userDto.getUsr_id());
            model.addAttribute(userDto);
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg","GET_ERR");
            return "redirect:/";
        }
        return "user/usrMod.tiles";
    }

    @PostMapping ("/usrMod")
    public String usrMod(UserDto userDto, HttpSession session, RedirectAttributes rattr){

        try {
            int rowCnt = userService.updateUsr(userDto);
            if(rowCnt != 1)
                throw new Exception("user update error");

            rattr.addFlashAttribute("msg","MOD_OK");
            return "redirect:/user/mypage";
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg","MOD_ERR");
            return "redirect:/user/usrMod";
        }
    }

    @PostMapping ("/usrDel")
    public String usrDel(HttpSession session, String cmn_cd_drp, RedirectAttributes rattr){
        UserDto userDto = (UserDto)session.getAttribute("userDto");
        try {
            int rowCnt = userService.deleteUsr(userDto.getUsr_id(), cmn_cd_drp);

            if(rowCnt != 1)
                throw new Exception("user delete error");

            String msg = "DEL_OK";
            return "redirect:/user/logout?msg="+msg;
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg","DEL_ERR");
            return "redirect:/user/usrMod";
        }
    }

    @PostMapping("/usrPwdCheck")
    public String usrPwdCheck(HttpSession session, String pwd, String cmn_cd_drp) throws Exception {
        UserDto userDto = (UserDto)session.getAttribute("userDto");

        userDto = userService.selectUsr(userDto.getUsr_id()); // 예외처리 예정

        if(!(userDto!=null && userDto.getPwd().equals(pwd))) {
            String pwCheckErr = URLEncoder.encode("pwd가 일치하지 않습니다.", "utf-8");
            return "redirect:/user/usrMod?pwCheckErr="+pwCheckErr;
        }
        return "forward:/user/usrDel";
    }

    @GetMapping("/findIdPwd")
    public String findIdPwd(){
        return "user/findIdPwd.tiles";
    }


    @RequestMapping(value="/userNaverLoginPro",  method = {RequestMethod.GET,RequestMethod.POST})
    public String userNaverLoginPro(Model model, @RequestParam Map<String,Object> paramMap, @RequestParam String code, @RequestParam String state, HttpSession session) throws SQLException, Exception {
        System.out.println("paramMap:" + paramMap);
        Map <String, Object> resultMap = new HashMap<String, Object>();

        OAuth2AccessToken oauthToken;
        oauthToken = naverloginbo.getAccessToken(session, code, state);
        //로그인 사용자 정보를 읽어온다.
        String apiResult = naverloginbo.getUserProfile(oauthToken);
        System.out.println("apiResult =>"+apiResult);
        // objectMapper에 json형태인 apiResult를 역직렬화, map 형태로 만든다.
        ObjectMapper objectMapper =new ObjectMapper();
        Map<String, Object> apiJson = (Map<String, Object>) objectMapper.readValue(apiResult, Map.class).get("response");

        // 생년월일 만들기
        String birthYear = (String)apiJson.get("birthyear");
        String birthDay = (String)apiJson.get("birthday");
        birthDay = birthDay.replaceAll("-", "");
        birthDay.trim();
        birthDay = birthYear+birthDay;
        System.out.println(birthDay);

        // 핸드폰 특수문자 버리기
        String phn = (String)apiJson.get("mobile");
        phn = phn.replaceAll("-", "");

        // 이름 한글 변환
        String name = (String)apiJson.get("name");
        name = uniToKor(name);
        System.out.println(name);

        apiJson.put("usr_nm", name);
        apiJson.put("brth", birthDay);
        apiJson.put("phn", phn);
        apiJson.put("naver_id", apiJson.get("id"));

        String naver_id = (String)apiJson.get("naver_id");

        // 네이버로그인 정보와 일치하는 사용자정보 불러오기
        Map<String, Object> naverConnectionCheck = userService.naverConnectionCheck(apiJson);
        System.out.println(naverConnectionCheck);
        if(naverConnectionCheck == null) { // 일치하는 정보가 아예 없으면 가입
            model.addAttribute("email",apiJson.get("email"));
            model.addAttribute("brth",apiJson.get("brth"));
            model.addAttribute("phn",apiJson.get("phn"));
            model.addAttribute("usr_nm",apiJson.get("usr_nm"));
            model.addAttribute("gndr",apiJson.get("gender"));
            model.addAttribute("naver_id",apiJson.get("naver_id"));
            return "user/setSubInfo.tiles";
        }else if(naverConnectionCheck.get("naver_id") == null && naverConnectionCheck.get("email") != null) { // 가입했지만 네이버 연동 안되어 있을시, confirm("연동하시겠습니까?")
            model.addAttribute("msg", "alrdy_usr"); // 이미 가입한 회원입니다. sns 계정과 연동하시겠습니까?
            model.addAttribute("naver_id",apiJson.get("naver_id"));

            userService.setNaverConnection(apiJson);

            UserDto userDto = userService.userNaverLoginPro(naver_id);
            session.setAttribute("userDto", userDto);
        }else { // 모두 연동 되어있을시, 바로 세션으로 정보 저장
            UserDto userDto = userService.userNaverLoginPro(naver_id); // 해당 네이버 아이디를 가진 사용자를 부른다. (아이디, 이름, 이메일, 핸드폰, 역할)
            session.setAttribute("userDto", userDto);
            userService.updateHstForLogin(userDto.getUsr_id()); // 예외처리 예정 (에러 발생시 세션 안넘기고 에러메세지 + 메인 이동)
        }
        String toURL = (String)session.getAttribute("toURL");

        // 이전에 눌렀던 url 이동 (마이페이지, 고객센터에 적용?)
        toURL = toURL==null || toURL.equals("") ? "/" : toURL;
        return "redirect:"+toURL;
    }

    @PostMapping ("/setNaverConnection")
    public String setNaverConnection(String naver_id, HttpSession session, RedirectAttributes rattr){

        return "redirect:/";
    }

    @PostMapping ("/setSubInfo")
    public String setSubInfo(String kakao_id, String gndr, String email, Model m){
        m.addAttribute("kakao_id", kakao_id);
        m.addAttribute("gndr", gndr);
        m.addAttribute("email", email);
        return "/user/setSubInfo";
    }

    @GetMapping("/setSubInfo")
    public String setSubInfo(Model m){

        return "user/setSubInfo.tiles";
    }

    @PostMapping("checkPwdForUsrMod")
    public String checkPwdForUsrMod(HttpSession session, String pwd, RedirectAttributes rattr){
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        String usr_id = userDto.getUsr_id();

        try {
            boolean pwdCheck = userService.checkPwdForUsrMod(usr_id, pwd);
            if(pwdCheck==true)
                return "redirect:/user/usrMod";

            rattr.addFlashAttribute("msg","GET_ERR");
            return "redirect:/user/mypage";

        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg","DB_ERR");
            return "redirect:/user/mypage";
        }


    }

    // 유니코드로된 이름 한글 변환
    private String uniToKor(String uni){
        StringBuffer result = new StringBuffer();

        for(int i=0; i<uni.length(); i++){
            if(uni.charAt(i) == '\\' &&  uni.charAt(i+1) == 'u'){
                Character c = (char)Integer.parseInt(uni.substring(i+2, i+6), 16);
                result.append(c);
                i+=5;
            }else{
                result.append(uni.charAt(i));
            }
        }
        return result.toString();
    }
}