package com.devcamp.eztour.controller.user;

import com.devcamp.eztour.domain.user.UserDto;
import com.devcamp.eztour.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserRestController {

    @Autowired
    UserService userService;

    // 아이디 중복여부 가져오는 메서드
    @GetMapping("/checkId/{usr_id}")
    public ResponseEntity<String> checkId(@PathVariable(value = "usr_id", required = false) String usr_id){
        try {
            int checkId = userService.checkId(usr_id); // 예외처리 예정
            // 0이면 사용가능, 1이면 중복
            String msg;
            if(checkId==0){
                msg="usable";
            }else{
                msg="unusable";
            }
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/findId/{usr_nm}/{phn}")
    public ResponseEntity<String> findId(@PathVariable String usr_nm, @PathVariable String phn){
        try {
            String usr_id = userService.findId(usr_nm, phn);
            if(usr_id==null){
                throw new Exception();
            }
            return new ResponseEntity<>(usr_id, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
