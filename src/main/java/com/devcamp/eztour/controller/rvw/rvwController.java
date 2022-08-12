package com.devcamp.eztour.controller.rvw;


import com.devcamp.eztour.domain.rvw.RvwDto;
import com.devcamp.eztour.domain.user.UserDto;
import com.devcamp.eztour.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/review")
public class rvwController {
    @Autowired
    UserService userService;

    @GetMapping("/write")
    public String saveReview(HttpServletRequest request, Model model) throws Exception{
        HttpSession session = request.getSession();
        UserDto userDto = userService.selectUserEmail("to9251");
        System.out.println(userDto);
        model.addAttribute("userDto",userDto);
        return "rvwList";
    }

    @PostMapping("/write")
    public String saveReview(RvwDto rvwDto, HttpSession session){
        System.out.println(rvwDto);
        return "redirect:/";
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
