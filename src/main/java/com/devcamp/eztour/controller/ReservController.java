package com.devcamp.eztour.controller;

import com.devcamp.eztour.domain.reserv.ReservInfoDto;
import com.devcamp.eztour.service.ReservService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/reserv")
public class ReservController {
    @Autowired
    ReservService reservService;

    @GetMapping("/reserv")
    public String reserv(String prdDtlCd, int adtCnt, int chdCnt, int bbCnt, Model m){
        //여행자 세부정보 얻어오기
        try {
//            session.getAttribute("id");
            ReservInfoDto rid = reservService.readPrdInfo(prdDtlCd);
            m.addAttribute("rid", rid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //회원정보 얻어오기

        return "reserv";
    }

}

