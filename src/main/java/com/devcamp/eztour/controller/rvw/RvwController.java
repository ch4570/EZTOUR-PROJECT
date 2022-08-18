package com.devcamp.eztour.controller.rvw;


import com.devcamp.eztour.domain.rvw.PageHandler;
import com.devcamp.eztour.domain.rvw.RvwDto;
import com.devcamp.eztour.domain.rvw.SearchCondition;
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
import java.util.List;

@Controller
@RequestMapping("/review")
public class RvwController {

    @Autowired
    UserService userService;
    @Autowired
    RvwService rvwService;

    @PostMapping("/remove")
    public String remove(Integer rvw_no, SearchCondition sc, RedirectAttributes rattr, HttpSession session) {
//        String usr_id = (String)session.getAttribute("id");
        String usr_id = "to9251";


        try {

            if(rvwService.remove(rvw_no, usr_id)!=1)
                throw new Exception("review remove error");

            rattr.addFlashAttribute("msg","DEL_OK");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg","DEL_ERR");
        }

        return "redirect:/review/list"+sc.getQueryString();
    }

    @GetMapping("/list")
    public String list(SearchCondition sc, Model m) {


        try {

            int totalCnt = rvwService.getSearchResultCnt(sc);
            System.out.println("totalCnt = " + totalCnt);
            m.addAttribute("totalCnt", totalCnt);

            PageHandler pageHandler = new PageHandler(totalCnt, sc);

            List<RvwDto> list = rvwService.getSearchResultPage(sc);

            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "rvwList";
    }

    @GetMapping("/read")
    public String read(Integer rvw_no, SearchCondition sc, RedirectAttributes rattr, Model m) {
        try {
            RvwDto rvwDto = rvwService.read(rvw_no);
            m.addAttribute("rvwDto", rvwDto);
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg","READ_ERR");
            return "redirect:/rvwList"+sc.getQueryString();
        }

        return "rvwDetail";
    }


    @GetMapping("/write")
    public String write(Model m, HttpSession session) throws Exception{
        //        String usr_id = (String)session.getAttribute("id");
        String usr_id = "to9251";

        RvwDto rvwDto = rvwService.selectUsernmEmail(usr_id);

        rvwDto.setWrt_nm(rvwDto.getUsr_nm());
        rvwDto.setWrt_email(rvwDto.getEmail());

        m.addAttribute("rvwDto", rvwDto);

        List<RvwDto> list = rvwService.selectPrdnm(usr_id); // prd_cd, prd_nm, usr_id
        System.out.println("list = " + list);
        System.out.println("rvwDto = " + rvwDto);
        m.addAttribute("list",list);
        return "rvwRegister";
    }

    @PostMapping("/write")
    public String write(RvwDto rvwDto, Model m, RedirectAttributes rattr, HttpSession session) throws Exception {
        try {
            System.out.println("rvwDto = " + rvwDto);
            int rowCnt = rvwService.write(rvwDto);

            if(rowCnt!=1)
                throw new Exception("Write failed");

            rattr.addFlashAttribute("msg","WRT_OK");

            return "redirect:/review/list";
        } catch (Exception e) {
            e.printStackTrace();
            //        String usr_id = (String)session.getAttribute("id");
            String usr_id = "to9251";
            m.addAttribute("rvwDto", rvwDto);
            List<RvwDto> list = rvwService.selectPrdnm(usr_id);
            m.addAttribute("list",list);
            rattr.addFlashAttribute("msg", "WRT_ERR");
            return "rvwRegister";
        }
    }

    @GetMapping("/modify")
    public String modify(Integer rvw_no, Model m, HttpSession session) throws Exception{
//        String usr_id = (String)session.getAttribute("id");
        String usr_id = "to9251";

        System.out.println("rvw_no = " + rvw_no);


        RvwDto rvwDto = rvwService.selectUsernmEmail(usr_id);
        rvwDto.setRvw_no(rvw_no);
        rvwDto.setWrt_nm(rvwDto.getUsr_nm());
        rvwDto.setWrt_email(rvwDto.getEmail());

        List<RvwDto> list = rvwService.selectPrdnm(usr_id); // prd_cd, prd_nm, usr_id
        System.out.println("list = " + list);
        System.out.println("rvwDto = " + rvwDto);
        m.addAttribute("list",list);
        m.addAttribute("rvwDto", rvwDto);

        return "rvwRegister";
    }

    @PostMapping("/modify")
    public String modify(RvwDto rvwDto, SearchCondition sc, HttpSession session, Model m, RedirectAttributes rattr) {
        try {
            int rowCnt = rvwService.modify(rvwDto);

            if(rowCnt!=1)
                throw new Exception("Modify failed");

            rattr.addFlashAttribute("msg","MOD_OK");

            return "redirect:/review/list"+sc.getQueryString();

        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("rvwDto", rvwDto);
            rattr.addFlashAttribute("msg", "MOD_ERR");
            return "rvwRegister";
        }
    }




}
