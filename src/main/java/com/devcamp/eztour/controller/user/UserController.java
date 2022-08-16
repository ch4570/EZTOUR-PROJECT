package com.devcamp.eztour.controller.user;

import com.devcamp.eztour.domain.user.UserDto;
import com.devcamp.eztour.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

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
    public String join(HttpSession session, RedirectAttributes rattr, String usr_nm, String phn, Model m) {
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
    public String loginView(HttpSession session, RedirectAttributes rattr) {
        if(session.getAttribute("userDto")==null)
            return "user/login.tiles";

        rattr.addFlashAttribute("msg", "ACC_ERR");
        return "redirect:/";
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

        userDto = userService.selectUsr(userDto.getUsr_id());

        if(!(userDto!=null && userDto.getPwd().equals(pwd))) {
            String pwCheckErr = URLEncoder.encode("pwd가 일치하지 않습니다.", "utf-8");
            return "redirect:/user/usrMod?pwCheckErr="+pwCheckErr;
        }
        return "forward:/user/usrDel";
    }


}