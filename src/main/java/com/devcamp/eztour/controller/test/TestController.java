package com.devcamp.eztour.controller.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class TestController {

    @GetMapping("/test")
    public String testMapping(){
        return "rvwCmt";
    }

    @DeleteMapping("/delete/test")
    public String deleteTest(){
        return "delete";
    }

    @PostMapping("/post/test")
    public String postTest(){
        return "post";
    }

    @PatchMapping ("/patch/test")
    public String patchTest(){
        return "patch";
    }

    @PutMapping("/put/test")
    public String putTest(){
        return "put";
    }
}
