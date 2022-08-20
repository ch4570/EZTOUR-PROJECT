package com.devcamp.eztour.controller.attPrd;

import com.devcamp.eztour.dao.attPrd.AttPrdDao;
import com.devcamp.eztour.domain.attPrd.AttPrdDto;
import com.devcamp.eztour.domain.attPrd.PageHandler;
import com.devcamp.eztour.domain.attPrd.SearchCondition;
import com.devcamp.eztour.domain.user.UserDto;
import com.devcamp.eztour.service.attPrd.AttPrdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/attprd")
public class AttPrdController {

    // 1. 유저가 관심 상품 테이블에 있고 AND 좋아요 여부 = 0 이면 update 좋아요 여부 = 1로 변경
    // 2. 유저가 관심 상품 테이블에 있고 AND 좋아요 여부 = 1 이면 update 좋아요 여부 = 0로 변경
    // 3. 유저가 관심 상품 테이블에 없으면 insert
    @Autowired
    AttPrdService attPrdService;

    @GetMapping("/list")
    public String list(String prd_cd, SearchCondition sc, Model m, HttpSession session) {
        /**
         * [해야할 작업]
         * 1. session 없으면(비회원이면) 회원가입 팝업 발생되게 예외 처리
         */

        UserDto userDto = (UserDto) session.getAttribute("userDto");
        String usr_id = userDto.getUsr_id();

        try {
            int totalCnt = attPrdService.count(usr_id, prd_cd);
            if(totalCnt != 1)
                throw new Exception("Not my user. Register our site");
            m.addAttribute("totalCnt", totalCnt);

            PageHandler pageHandler = new PageHandler(totalCnt, sc);

            Integer offset = sc.getOffset();
            Integer pageSize = sc.getPageSize();

            List<AttPrdDto> list = attPrdService.selectPage(offset, pageSize);

            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);

        } catch (Exception e) {
            e.printStackTrace();
            // 1. session 없으면(비회원이면) 회원가입 팝업 발생되게 예외 처리
        }

        return "attPrdList";
    }

//    @PostMapping("/remove")
//    public String remove(Integer att_prd_no, SearchCondition sc, RedirectAttributes rattr, HttpSession session) {
//
//    }






}
