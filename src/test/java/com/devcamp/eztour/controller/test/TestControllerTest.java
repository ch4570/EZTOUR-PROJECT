package com.devcamp.eztour.controller.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


import javax.servlet.http.Cookie;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class TestControllerTest {

    private static final Cookie cookie = new Cookie("token", "test1234");


    @Test
    public void testController1() throws Exception{
        MockMvc mockMvc = getMockMvc();

        mockMvc.perform(MockMvcRequestBuilders.get("/test"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("test"))
                .andDo(print())
                .andDo(MockMvcResultHandlers.log());
    }

    @Test
    public void testController2() throws Exception{
        MockMvc mockMvc = getMockMvc();

        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/test"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("delete"))
                .andDo(print())
                .andDo(MockMvcResultHandlers.log());
    }

    @Test
    public void testController3() throws Exception{
        MockMvc mockMvc = getMockMvc();

        mockMvc.perform(MockMvcRequestBuilders.post("/post/test"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("post"))
                .andDo(print())
                .andDo(MockMvcResultHandlers.log());
    }

    @Test
    public void testController4() throws Exception{
        MockMvc mockMvc = getMockMvc();

        mockMvc.perform(MockMvcRequestBuilders.patch("/patch/test"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("patch"))
                .andDo(print())
                .andDo(MockMvcResultHandlers.log());
    }

    @Test
    public void testController5() throws Exception{
        MockMvc mockMvc = getMockMvc();

        mockMvc.perform(MockMvcRequestBuilders.put("/put/test"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("put"))
                .andDo(print())
                .andDo(MockMvcResultHandlers.log());
    }

    @Test
    public void testController6() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new TestApiController()).build();
        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("criteria","회");

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/food/api")
                .params(param)
                .cookie(cookie)
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.body[0].name").value("광어"))
                .andExpect(jsonPath("$.body[1].name").value("우럭"))
                .andExpect(jsonPath("$.body[2].name").value("대방어"))
                .andDo(print());
    }

    private MockMvc getMockMvc(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new TestController()).setViewResolvers(viewResolver).build();
        return mockMvc;
    }

}