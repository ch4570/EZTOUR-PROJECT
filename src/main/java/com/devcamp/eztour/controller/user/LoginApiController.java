package com.devcamp.eztour.controller.user;

import com.devcamp.eztour.domain.user.UserDto;
import com.devcamp.eztour.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@PropertySource("classpath:config/api.properties")
public class LoginApiController {

    @Value("${KAKAO.JS}")
    String kakaoApi;

    @Autowired
    UserService userService;

    @PostMapping("/getKakaoApi")
    public String getKakaoApi() throws Exception {
        System.out.println("getKakaoApi");
        System.out.println(kakaoApi);
        return kakaoApi;
    }

    @RequestMapping(value="/kakaoLoginPro", method= RequestMethod.POST)
    public Map<String, Object> kakaoLoginPro(@RequestParam Map<String,Object> paramMap, HttpSession session) throws SQLException, Exception {
        System.out.println("paramMap:" + paramMap);
        Map <String, Object> resultMap = new HashMap<String, Object>();

        Map kakaoConnectionCheck = userService.kakaoConnectionCheck(paramMap);

        if(kakaoConnectionCheck == null) { // 이메일과 카카오 아이디 둘다 없을시
            resultMap.put("JavaData", "register");
        } else if(kakaoConnectionCheck.get("kakao_id") == null && kakaoConnectionCheck.get("email") != null) { // 이메일 가입 되어있고 카카오 연동 안되어 있을시
            System.out.println("kakaoLogin");
            userService.setKakaoConnection(paramMap);
            UserDto userDto = userService.userKakaoLoginPro((String)paramMap.get("kakao_id"));
            session.setAttribute("userDto", userDto);
            resultMap.put("JavaData", "connect");
        } else { // 가입과 연동 모두 되어있을시
            UserDto userDto = userService.userKakaoLoginPro((String)paramMap.get("kakao_id"));
            System.out.println("userDto = " + userDto);
            session.setAttribute("userDto", userDto);
            resultMap.put("JavaData", "login");
        }
        return resultMap;
    }

}
