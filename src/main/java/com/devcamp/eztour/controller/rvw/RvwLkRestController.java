package com.devcamp.eztour.controller.rvw;


import com.devcamp.eztour.domain.rvw.RvwLkAdmDto;
import com.devcamp.eztour.domain.user.UserDto;
import com.devcamp.eztour.service.rvw.RvwLkAdmService;
import com.devcamp.eztour.service.rvw.RvwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@RestController
public class RvwLkRestController {
    @Autowired
    RvwLkAdmService rvwLkAdmService;
    @Autowired
    RvwService rvwService;



    @PostMapping("/heart") // 재확인 필요
    public String heart(@RequestBody Integer rvw_no, Model m, RedirectAttributes rattr, HttpSession session) throws Exception {

        String result;

        // 1. 비회원이면 로그인 화면으로 redirect 로 이동
        if (session.getAttribute("userDto") == null) {
            rattr.addFlashAttribute("msg", "ACC_ERR");
            return "redirect:/user/login";
        }

        UserDto userDto = (UserDto) session.getAttribute("userDto");
        String usr_id = userDto.getUsr_id();

        int checkLike = rvwLkAdmService.checkLkYn(usr_id, rvw_no); // 좋아요 체크 확인

        // 2. rvw_lk_yn = 0 이면 likeUp
        if (checkLike == 0) {
            rvwLkAdmService.updateLikeUp(usr_id, rvw_no);
            rvwService.increaseLikeCnt(rvw_no);
            result = "HeartUp";

        }
        // 3. rvw_lk_yn = 1 이면 likeDown
        else {
            rvwLkAdmService.updateLikeDown(usr_id, rvw_no);
            rvwService.decreaseLikeCnt(rvw_no);
            result = "HeartDown";
        }
        return result;
    }

}
