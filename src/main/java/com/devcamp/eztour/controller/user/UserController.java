package com.devcamp.eztour.controller.user;

import com.devcamp.eztour.domain.user.AES256Cipher;
import com.devcamp.eztour.domain.user.NaverLoginBO;
import com.devcamp.eztour.domain.user.UserDto;
import com.devcamp.eztour.service.reserv.ReservService;
import com.devcamp.eztour.service.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth2AccessToken;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final NaverLoginBO naverloginbo;

    private final ReservService reservService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private Date lst_acc_date2 = null;
    private Date rst_chg_date2= null;

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
    public String auth(HttpSession session) {
        return "user/auth.tiles";
    }

    // 인증번호 맞을시 중복체크 후에 이름과 폰번호 들고 redirect
    @PostMapping("/authOk")
    public String authOk(String usr_nm, String phn, RedirectAttributes rattr) throws Exception {

        if(userService.findId(usr_nm, phn)!=null){
            rattr.addFlashAttribute("msg","DUPL_ID");
            return "redirect:/user/auth";
        }

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
    public String join(UserDto user, RedirectAttributes rattr) {
        try {
            int rowCnt = userService.insertUsr(user);
            if (rowCnt != 1)
                throw new Exception("user insert error");
            rattr.addFlashAttribute("msg", "REG_OK");
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "REG_ERR");
            return "redirect:/user/auth";
        }
    }

    @GetMapping("/login")
    public String loginView(HttpSession session, Model m, RedirectAttributes rattr, String lst_acc_date,
                            String rst_chg_date, String usr_id, @CookieValue(name="id", required = false) String enCookieId) throws Exception{
        // 네이버 로그인 활성화
        if(session.getAttribute("userDto")==null) {
            String naverAuthUrl = naverloginbo.getAuthorizationUrl(session);
            m.addAttribute("naverUrl", naverAuthUrl);

           // =========================== 휴면 계정 처리 일원화 =============================
//            try {
//                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                if(lst_acc_date!=null && rst_chg_date!=null) {
//                    lst_acc_date2 = transFormat.parse(lst_acc_date);
//                    rst_chg_date2 = transFormat.parse(rst_chg_date);
//                }
//            } catch (ParseException e) {
//                e.printStackTrace();
//                rattr.addFlashAttribute("msg", "ACC_ERR");
//                return "redirect:/";
//            }

            if(lst_acc_date!=null & rst_chg_date!=null) {
                String result = checkRstUsr(lst_acc_date, rst_chg_date);
                if(result.equals("ACC_ERR")){
                    rattr.addFlashAttribute("msg", "ACC_ERR");
                    return "redirect:/";
                }
            }
            // ============================================================================

            // 쿠키 복호화
            String deCookieId=null;
            if(enCookieId!=null) {
                AES256Cipher a256 = AES256Cipher.getInstance();
                deCookieId = a256.AES_Decode(enCookieId);
            }

            System.out.println("this.lst_acc_date2 = " + lst_acc_date2);
            System.out.println("this.rst_chg_date2 = " + rst_chg_date2);


            m.addAttribute("deCookieId",deCookieId);
            m.addAttribute("lst_acc_date", lst_acc_date2);
            m.addAttribute("rst_chg_date", rst_chg_date2);
            m.addAttribute("usr_id", usr_id);
            return "user/login.tiles";

        }else {
            rattr.addFlashAttribute("msg", "ACC_ERR");
            return "redirect:/";
        }
    }

    @PostMapping("/login")
    public String login(String usr_id, String pwd, boolean rememberId,
                        HttpSession session, HttpServletResponse response, RedirectAttributes rattr) throws Exception {

        UserDto userDto = userService.selectUsr(usr_id);

        // 비밀번호 확인
        if(!(userDto!=null && bCryptPasswordEncoder.matches(pwd,userDto.getPwd()))) {
            rattr.addFlashAttribute("msg", "LOGIN_FAIL");
            return "redirect:/user/login";
        }

        // ======================== 휴면계정 처리 일원화 하기 ========================
        // 로그인시 유저 상태를 확인, 휴면계정이면 날짜정보를 get으로 넘긴다.

//        if(userDto.getCmn_cd_usr_stt().equals("2C")) {
//
//            UserDto userHst = userService.selectUsrHst(usr_id);
//
//            SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String lst_acc_date = transFormat.format(userHst.getLst_acc_date());
//            String rst_chg_date = transFormat.format(userHst.getRst_chg_date());
//
//            return "redirect:/user/login?lst_acc_date=" +lst_acc_date+ "&rst_chg_date=" +rst_chg_date+ "&usr_id=" +usr_id;
//        }

        String result = checkRstUsr(userDto.getCmn_cd_usr_stt(),usr_id);
        System.out.println(result);
        if(!result.equals("NO_RST")){
            return "redirect:/user/login?"+result+"&rstmsg=YES_RST";
        }
        // ========================================================================

        userService.updateHstForLogin(usr_id); // 예외처리 예정 (에러 발생시 세션 안넘기고 에러메세지 + 메인 이동)
        UserDto loginUser = new UserDto(userDto.getUsr_id(), userDto.getUsr_nm(),
                                                 userDto.getEmail(), userDto.getRl(), userDto.getPhn(), userDto.getMlg(), userDto.getCmn_cd_prf_img());
        session.setAttribute("userDto", loginUser);

        // 쿠키 암호화
        AES256Cipher a256 = AES256Cipher.getInstance();

        if(rememberId) {
            Cookie cookie = new Cookie("id", a256.AES_Encode(usr_id));
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("id", "");
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
    public String usrModView(HttpSession session, RedirectAttributes rattr, Model model, String pwCheckErr){
        if(pwCheckErr!=null) {
            model.addAttribute("msg", "DEL_ERR");
            return "user/usrMod.tiles";
        }
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

        if(!(userDto!=null && bCryptPasswordEncoder.matches(pwd,userDto.getPwd()))) {
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
    public String userNaverLoginPro(@RequestParam Map<String,Object> paramMap, @RequestParam String code, @RequestParam String state, HttpSession session, Model model, RedirectAttributes rattr, ModelMap modelMap) throws SQLException, Exception {
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

        // 핸드폰 특수문자 버리기
        String phn = (String)apiJson.get("mobile");
        phn = phn.replaceAll("-", "");

        // 이름 한글 변환
        String name = (String)apiJson.get("name");
        name = uniToKor(name);

        apiJson.put("usr_nm", name);
        apiJson.put("brth", birthDay);
        apiJson.put("phn", phn);
        apiJson.put("naver_id", apiJson.get("id"));

        String naver_id = (String)apiJson.get("naver_id");

        // 네이버 정보와 일치하는 사용자 정보 불러오기
        Map<String, Object> naverConnectionCheck = null;
        try {
            naverConnectionCheck = userService.naverConnectionCheck(apiJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(naverConnectionCheck == null) { // 일치하는 정보가 아예 없으면 가입
            model.addAttribute("email",apiJson.get("email"));
            model.addAttribute("brth",apiJson.get("brth"));
            model.addAttribute("phn",apiJson.get("phn"));
            model.addAttribute("usr_nm",apiJson.get("usr_nm"));
            model.addAttribute("gndr",apiJson.get("gender"));
            model.addAttribute("naver_id",apiJson.get("naver_id"));
            return "user/setSubInfo.tiles";
        }else if((naverConnectionCheck.get("naver_id") == null) && (naverConnectionCheck.get("phn") != null)) { // 가입했지만 네이버 연동 안되어 있을시, confirm("연동하시겠습니까?")
            rattr.addFlashAttribute("msg","NAVER_SET_CONFIRM");

            String usr_nm = URLEncoder.encode((String) naverConnectionCheck.get("usr_nm"));
            return "redirect:/user/login?naver_id=" +naver_id+ "&usr_nm=" +usr_nm+ "&phn=" +naverConnectionCheck.get("phn");
        }else { // 모두 연동 되어있을시, 바로 세션으로 정보 저장
            UserDto userDto = userService.userNaverLoginPro(naver_id); // 해당 네이버 아이디를 가진 사용자를 부른다. (아이디, 이름, 이메일, 핸드폰, 역할)
            session.setAttribute("userDto", userDto);
            userService.updateHstForLogin(userDto.getUsr_id()); // 예외처리 예정 (에러 발생시 세션 안넘기고 에러메세지 + 메인 이동)
        }

        String toURL = (String) session.getAttribute("toURL");
        toURL = toURL==null || toURL.equals("") ? "/" : toURL;
        return "redirect:"+toURL;
    }

    @PostMapping ("/setNaverConnection")
    public String setNaverConnection(String naver_id, String usr_nm, String phn, HttpSession session, RedirectAttributes rattr){
        Map<String, Object> apiJson = new HashMap<>();
        apiJson.put("naver_id", naver_id);
        apiJson.put("usr_nm", usr_nm);
        apiJson.put("phn", phn);
        UserDto userDto = null;
        try {
            int rowCnt = userService.setNaverConnection(apiJson);
            if(rowCnt != 1){
                throw new Exception("naver connection error" +usr_nm);
            }
            rattr.addFlashAttribute("msg", "SET_NAVER_OK");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg","SET_NAVER_ERR");
            return "redirect:/user/login";
        }
        return "redirect:/user/login";
    }

    @PostMapping ("/setSubInfo")
    public String setSubInfo(String kakao_id, String gndr, String email, Model m){
        m.addAttribute("kakao_id", kakao_id);
        m.addAttribute("gndr", gndr);
        m.addAttribute("email", email);
        return "redirect:/user/setSubInfo";
    }

    @GetMapping("/setSubInfo")
    public String setSubInfo(Model m,String kakao_id, String gndr, String email){
        m.addAttribute("kakao_id", kakao_id);
        m.addAttribute("gndr", gndr);
        m.addAttribute("email", email);
        return "user/setSubInfo.tiles";
    }

    @PostMapping("/checkPwdForUsrMod")
    public String checkPwdForUsrMod(HttpSession session, String pwd, RedirectAttributes rattr){
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        String usr_id = userDto.getUsr_id();
        try {
            // 세션에 있는 아이디를 가져와서 유저정보를 다 가져오고, 거기서 비밀번호랑 해시암호랑 같은지 확인
            userDto = userService.selectUsr(usr_id);
            boolean pwdCheck = bCryptPasswordEncoder.matches(pwd, userDto.getPwd());
            if(pwdCheck)
                return "redirect:/user/usrMod";

            rattr.addFlashAttribute("msg","GET_ERR");
            return "redirect:/user/mypage";

        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg","DB_ERR");
            return "redirect:/user/mypage";
        }
    }

    @PostMapping("/changePwd")
    public String changePwd(HttpSession session, HttpServletRequest req, String pwd, String new_pwd, RedirectAttributes rattr) {
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        String usr_id = userDto.getUsr_id();

        try {
            if (req.getRequestURI().equals("/user/usrMod")) {
                userDto = userService.selectUsr(usr_id);
                boolean pwdCheck = bCryptPasswordEncoder.matches(pwd, userDto.getPwd());
                System.out.println("pwd = " + pwd);
                System.out.println("userDto.getPwd() = " + userDto.getPwd());
                if (!pwdCheck) {
                    rattr.addFlashAttribute("msg", "PWD_ERR");
                    return "redirect:/user/usrMod";
                }
            }
            // 변경할 비밀번호, 비밀번호 확인란과 일치하지 않을시
            if (!(pwd.equals(new_pwd))) {
                rattr.addFlashAttribute("msg", "NEW_PWD_ERR");
                return "redirect:/user/usrMod";
            }
                String encodedPwd = bCryptPasswordEncoder.encode(new_pwd);
                int rowCnt = userService.changePwd(usr_id, encodedPwd);
                if (rowCnt != 1) {
                    throw new Exception("user update error");
                }
            } catch(Exception e){
                e.printStackTrace();
                rattr.addFlashAttribute("msg", "MOD_ERR");
                return "redirect:/user/usrMod";
        }
        rattr.addFlashAttribute("msg", "MOD_OK");
        return "redirect:/user/mypage";
    }

    @PostMapping("/findAndChangePwd")
    public String findAndChangePwd(String usr_id, String new_pwd, String new_pwd_chk, RedirectAttributes rattr){
        // 1. 일치 여부 확인
        if (!(new_pwd.equals(new_pwd_chk))) {
            rattr.addFlashAttribute("msg", "NEW_PWD_ERR");
            return "redirect:/user/findIdPwd";
        }
        String encodedPwd = bCryptPasswordEncoder.encode(new_pwd);
        try{
            int rowCnt = userService.changePwd(usr_id, encodedPwd);
            if (rowCnt != 1) {
                throw new Exception("user change pwd error");
            }
        }catch(Exception e){
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "MOD_ERR");
            return "redirect:/user/findIdPwd";
        }
        rattr.addFlashAttribute("msg","NEW_PWD_OK");
        return "redirect:/user/login";
    }

    @PostMapping("/rstRelease")
    public String rstRelease(String usr_id, RedirectAttributes rattr){
        try {
            int rowCnt = userService.rstRelease(usr_id);
            if(rowCnt!=1)
                throw new Exception("user rstRelease error");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg","RST_ERR");
            return "redirect:/user/login";
        }
        rattr.addFlashAttribute("msg","RLS_OK");
        return "redirect:/user/login";
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

    // 휴면 계정 날짜 처리
    private String checkRstUsr(String...str) throws Exception {

        // 만약 길이가 2라면 로그인(post)메서드에서 호출한 것
        if (str[0] != null && str[1] != null && str[0].length()==2){
            // 휴면 계정이면 날짜정보를 파싱해 쿼리스트링 형태로 넘긴다.
            // 휴면 계정이 아니면 "NO_RST" 메시지를 넘긴다.
            String usr_stt = str[0];
            String usr_id = str[1];
            if(usr_stt.equals("2C")){
                UserDto userHst = userService.selectUsrHst(usr_id);

                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String lst_acc_date = transFormat.format(userHst.getLst_acc_date());
                String rst_chg_date = transFormat.format(userHst.getRst_chg_date());

                return "lst_acc_date=" +lst_acc_date+ "&rst_chg_date=" +rst_chg_date+ "&usr_id=" +usr_id;
            }else{
                return "NO_RST";
            }
        }

        // 길이가 19라면... 로그인뷰(get)메서드에서 호출한 것
        System.out.println("str[0].length() = " + str[0].length());
        System.out.println("str[1].length() = " + str[1].length());
        if(str[0] != null && str[1] != null && str[0].length()==19){
            // 날짜를 파싱해서 인스턴스 변수로 넘긴다.
            // 완료되면 "PARSE_OK" 메시지를 넘긴다.
            SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            lst_acc_date2 = transFormat.parse(str[0]);
            rst_chg_date2 = transFormat.parse(str[1]);

            return "PARSE_OK";
        }
        return "ACC_ERR";
    }

}