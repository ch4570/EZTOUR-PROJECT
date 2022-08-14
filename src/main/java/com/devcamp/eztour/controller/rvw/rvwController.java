package com.devcamp.eztour.controller.rvw;


import com.devcamp.eztour.domain.rvw.PageHandler;
import com.devcamp.eztour.domain.rvw.RvwDto;
import com.devcamp.eztour.domain.user.UserDto;
import com.devcamp.eztour.service.rvw.RvwService;
import com.devcamp.eztour.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/review")
public class rvwController {

    @Autowired
    UserService userService;
    @Autowired
    RvwService rvwService;

    @PostMapping("/remove")
    public String remove(Integer rvw_no, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr) {
        String usr_id = "to9251";

//        String writer = (String) session.getAttribute("id");

        try {
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            int rowCnt = rvwService.remove(rvw_no, usr_id);

            if(rowCnt!=1)
                throw new Exception("review remove error");

            rattr.addFlashAttribute("msg","DEL_OK");

            if(rowCnt==1){
            }
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg","DEL_ERR");
        }


        return "redirect:/review/list";
    }

    @GetMapping("/list")
    public String list(Integer page, Integer pageSize, Model m) {

        if(page == null) page = 1;
        if(pageSize == null) pageSize = 10;

        try {

            int totalCnt = rvwService.getCount();

            PageHandler pageHandler = new PageHandler(page, pageSize, totalCnt);

            Map map = new HashMap();
            map.put("offset", (page-1)*pageSize);
            map.put("pageSize", pageSize);

            List<RvwDto> list = rvwService.getPage(map);

            m.addAttribute("list", list);
            m.addAttribute("totalCnt", totalCnt);
            m.addAttribute("ph", pageHandler);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "rvwList";
    }

    @GetMapping("/read")
    public String read(Integer rvw_no, Integer page, Integer pageSize, Model m) {
        try {
            RvwDto rvwDto = rvwService.read(rvw_no);
            m.addAttribute("rvwDto",rvwDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "rvw_detail";
    }


    @GetMapping("/write")
    public String write(HttpServletRequest request, Model m) throws Exception{
        //비회원일 때 작성하기 버튼 클릭하면 접근 못하게 조건문 추가해야함.
//        HttpSession session = request.getSession();
//        UserDto userDto = userService.selectUserEmail("to9251");
//        System.out.println(userDto);
//        m.addAttribute("userDto",userDto);
        RvwDto rvwDto = rvwService.selectUserEmail("to9251");
        rvwDto.setPrd_cd("a001");
        rvwDto.setWrt_nm(rvwDto.getUsr_nm());
        rvwDto.setWrt_email(rvwDto.getEmail());
        System.out.println(rvwDto);
        m.addAttribute("rvwDto",rvwDto);
        return "rvwRegister";
    }

    @PostMapping("/write")
    public String write(RvwDto rvwDto, HttpSession session, Model m, RedirectAttributes rattr) {
        try {
            int rowCnt = rvwService.write(rvwDto);

            if(rowCnt!=1)
                throw new Exception("Write failed");

            rattr.addFlashAttribute("msg","WRT_OK");

            return "redirect:/review/list";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("rvwDto", rvwDto);
            rattr.addFlashAttribute("msg", "WRT_ERR");
            return "rvwRegister";
        }
    }

    @GetMapping("/modify")
    public String modify(HttpServletRequest request, Model m) throws Exception{
        //비회원일 때 작성하기 버튼 클릭하면 접근 못하게 조건문 추가해야함.
//        HttpSession session = request.getSession();
//        UserDto userDto = userService.selectUserEmail("to9251");
//        System.out.println(userDto);
//        m.addAttribute("userDto",userDto);
        RvwDto rvwDto = rvwService.selectUserEmail("to9251");
        rvwDto.setRvw_no(121);
        rvwDto.setPrd_cd("a001");
        rvwDto.setWrt_nm(rvwDto.getUsr_nm());
        rvwDto.setWrt_email(rvwDto.getEmail());
        System.out.println(rvwDto);
        m.addAttribute("rvwDto",rvwDto);
        return "rvwRegister";
    }

    @PostMapping("/modify")
    public String modify(RvwDto rvwDto, HttpSession session, Model m, RedirectAttributes rattr) {
        try {
            int rowCnt = rvwService.modify(rvwDto);

            if(rowCnt!=1)
                throw new Exception("Modify failed");

            rattr.addFlashAttribute("msg","MOD_OK");

            return "redirect:/review/list";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("rvwDto", rvwDto);
            rattr.addFlashAttribute("msg", "MOD_ERR");
            return "rvwRegister";
        }
    }
}
