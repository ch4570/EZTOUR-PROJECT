package com.devcamp.eztour.controller.rvw;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleRestController {
    @GetMapping("/rvwCmt")
    public String test() {
        return "review/rvwCmt"; //  뷰이름
    }
    
// testContoller와 경로가 겹친다.
//    @GetMapping("/test")
//    public String test() {
//        return "test"; //  뷰이름
//    }

}
