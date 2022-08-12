package com.devcamp.eztour.controller;

import com.devcamp.eztour.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

//    private final TestService testService;

    @GetMapping("/")
    public String home() {

        return "home.tiles";
    }

}
