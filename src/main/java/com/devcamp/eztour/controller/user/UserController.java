package com.devcamp.eztour.controller.user;

import com.devcamp.eztour.domain.user.NaverLoginBO;
import com.devcamp.eztour.domain.user.UserDto;
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
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    NaverLoginBO naverloginbo;

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
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Expires", "0"); // Proxies.

        if(session.getAttribute("userDto")==null) {
            m.addAttribute("usr_nm", usr_nm);
            m.addAttribute("phn", phn);
            return "user/join.tiles";
        }
        rattr.addFlashAttribute("msg", "ACC_ERR");
        return "redirect:/";
    }
    @PostMapping("/join")
    public String join(UserDto user, RedirectAttributes rattr, HttpSession session) {
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
                        HttpSession session, HttpServletResponse response, String toURL) throws Exception {

        UserDto userDto = userService.selectUsr(usr_id);
        System.out.println(usr_id);

        if(!(userDto!=null && userDto.getPwd().equals(pwd))) {
            String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");

            return "redirect:/user/login?msg="+msg;
        }

        userService.updateHstForLogin(usr_id); // 예외처리 예정 (에러 발생시 세션 안넘기고 에러메세지 + 메인 이동)

        UserDto loginUser = new UserDto(userDto.getUsr_id(), userDto.getUsr_nm(),
                                                 userDto.getEmail(), userDto.getRl(), userDto.getPhn());
        session.setAttribute("userDto", loginUser);

        if(rememberId) {
            Cookie cookie = new Cookie("id", usr_id);
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("id", usr_id);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        // 3. 이전에 눌렀던 url 이동 (마이페이지, 고객센터에 적용?)
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
    public String mypage(HttpSession session, RedirectAttributes rattr) {
        if(session.getAttribute("userDto")==null){
            rattr.addFlashAttribute("msg", "ACC_ERR");
            return "redirect:/user/login";
        }
        return "user/mypage.tiles";
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
    public String usrMod(UserDto userDto, RedirectAttributes rattr){
        try {
            int rowCnt = userService.updateUsr(userDto);
            if(rowCnt != 1)
                throw new Exception("user update error");

            rattr.addFlashAttribute("msg","MOD_OK");
            return "redirect:/user/usrMod";
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
        System.out.println("네이버 사용자 프로필 정보 =============================="+apiResult);

        System.out.println("apiResult =>"+apiResult);
        ObjectMapper objectMapper =new ObjectMapper();
        Map<String, Object> apiJson = (Map<String, Object>) objectMapper.readValue(apiResult, Map.class).get("response");

        Map<String, Object> naverConnectionCheck = userService.naverConnectionCheck(apiJson);

        if(naverConnectionCheck == null) { //일치하는 이메일 없으면 가입

            model.addAttribute("email",apiJson.get("email"));
            model.addAttribute("password",apiJson.get("id"));
            model.addAttribute("phone",apiJson.get("mobile"));
            return "user/setNickname";
        }else if(naverConnectionCheck.get("NAVERLOGIN") == null && naverConnectionCheck.get("EMAIL") != null) { //이메일 가입 되어있고 네이버 연동 안되어 있을시
            userService.setNaverConnection(apiJson);
            Map<String, Object> loginCheck = userService.userNaverLoginPro(apiJson);
            session.setAttribute("userInfo", loginCheck);
        }else { //모두 연동 되어있을시
            Map<String, Object> loginCheck = userService.userNaverLoginPro(apiJson);
            session.setAttribute("userInfo", loginCheck);
        }

        return "redirect:/";
    }

}