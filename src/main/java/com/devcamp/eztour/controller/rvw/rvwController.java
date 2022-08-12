package com.devcamp.eztour.controller.rvw;


import com.devcamp.eztour.dao.rvw.RvwDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/review")
public class rvwController {

    @GetMapping("/review")
    public String loginForm() {
        return "rvwList";
    }


//    @GetMapping("/review")
//    public String review(String id, String pwd, HttpServletRequest request, HttpServletResponse response) {
//        HttpSession session = request.getSession();
//
//        session.setAttribute("id", id);
//        session.setAttribute("pwd", pwd);
//
//        return "rvwList.jsp";
//
//
//    }
}
