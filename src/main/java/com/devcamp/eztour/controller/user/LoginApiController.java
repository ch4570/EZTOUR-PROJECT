package com.devcamp.eztour.controller.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@PropertySource("classpath:config/api.properties")
public class LoginApiController {

    @Value("${KAKAO.JS}")
    String kakaoApi;

    @PostMapping("/getKakaoApi")
    public String getKakaoApi() throws Exception {
        System.out.println("getKakaoApi");
        System.out.println(kakaoApi);
        return kakaoApi;
    }

}
