package com.devcamp.eztour.controller.user;

import com.devcamp.eztour.service.user.UserService;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.*;

@RestController
@PropertySource("classpath:config/api.properties")
public class MessageController {

    DefaultMessageService messageService;

    @Autowired
    UserService userService;

    private String authNum;
    private static String smsKey;
    private static String smsSecretKey;

    @Value("${SMS.KEY}")
    public void setSmsKey(String value) {
        smsKey = value;
    }

    @Value("${SMS.SECRETKEY}")
    public void setSmsSecretKey(String value) {
        smsSecretKey = value;
    }


    @PostConstruct
    public void MessageController() {
        messageService = NurigoApp.INSTANCE.initialize(smsKey, smsSecretKey, "https://api.coolsms.co.kr");
    }

    @GetMapping("/authPhn/{phn}")
    public ResponseEntity<String> sendOne(@PathVariable(value="phn", required=false) String phn) {
        Message message = new Message();
        message.setFrom("01095007753");
        message.setTo(phn);
        authNum = authNum();
        message.setText("[이지투어] 요청하신 인증번호는 "+authNum+"입니다. 정확히 입력해주세요.");

        String msg = "";
        try {
            SingleMessageSentResponse  response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
            if(response.getStatusCode().equals("2000")) {
                msg = "AUTH_OK";
            }
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            msg="AUTH_ERR";
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }
    }

    // 인증번호 확인
    @GetMapping("/checkAuthNum/{checkNum}")
    public ResponseEntity<String> checkAuthNum(@PathVariable(value="checkNum", required = false)String checkNum){
        String msg;
        System.out.println(authNum);
        if(checkNum.equals(authNum)) {
            msg="AUTH_OK";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        }else {
            msg="AUTH_ERR";
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        }

    }

    // 임의의 숫자 여섯 자리 만들기
    private String authNum() {
        Random random = new Random();	//랜덤 함수 선언
        int createNum = 0;  			//1자리 난수
        String ranNum = ""; 			//1자리 난수 형변환 변수
        int letter    = 6;			    //난수 자릿수:6
        String resultNum = "";  		//결과 난수

        for (int i=0; i<letter; i++) {
            createNum = random.nextInt(9);   //0부터 9까지 올 수 있는 1자리 난수 생성
            ranNum = Integer.toString(createNum);  //1자리 난수를 String으로 형변환
            resultNum += ranNum;                  //생성된 난수(문자열)을 원하는 수(letter)만큼 더하며 나열
        }
        return resultNum;
    }


}
