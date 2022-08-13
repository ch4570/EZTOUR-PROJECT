package com.devcamp.eztour.controller.home;

import com.devcamp.eztour.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

//    private final TestService testService;

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("usr_id","admin");
        return "home.tiles";
    }

}
