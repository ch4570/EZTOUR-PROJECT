package com.devcamp.eztour.common;

import com.devcamp.eztour.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestInsert {
    @Autowired
    private TestService testService;

    @RequestMapping("/insert")
    public String insert() {
        int result = testService.insertTest("ch4580");
        return "home";
    }


}
