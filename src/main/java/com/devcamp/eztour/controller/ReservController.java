package com.devcamp.eztour.controller;

import com.devcamp.eztour.domain.reserv.ReservInfoDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reserv")
public class ReservController {

    @GetMapping("/reserv")
    public String reserv(String prdDtlCd, int adtCnt, int chdCnt, int bbCnt, ReservInfoDto rid){
        //여행자 세부정보 얻어오기

        //회원정보 얻어오기

        return "reserv";
    }

}

