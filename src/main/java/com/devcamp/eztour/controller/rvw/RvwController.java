package com.devcamp.eztour.controller.rvw;


import com.devcamp.eztour.domain.rvw.PageHandler;
import com.devcamp.eztour.domain.rvw.RvwDto;
import com.devcamp.eztour.domain.rvw.SearchCondition;
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
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        String usr_id = userDto.getUsr_id();


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
    public String read(Integer rvw_no, SearchCondition sc, RedirectAttributes rattr, Model m, HttpSession session) {
        String usr_id = null;
        try {
            UserDto userDto = (UserDto) session.getAttribute("userDto");
            usr_id = userDto.getUsr_id();
            System.out.println("usr_id = " + usr_id);
        } catch (NullPointerException e) {
            usr_id = "";
        }

        try {

            // 내가 작성한 글만 "삭제", "수정" 버튼 보이기 위한 조건
            int rowCnt = rvwService.checkRvwUser(usr_id, rvw_no);
            System.out.println("rowCnt = " + rowCnt);
            if(rowCnt == 1)
                m.addAttribute("check", "me");

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
    public String write(Model m,RedirectAttributes rattr, HttpSession session) throws Exception {
        if(session.getAttribute("userDto")==null){
            rattr.addFlashAttribute("msg", "ACC_ERR");
            return "redirect:/user/login";
        }
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        String usr_id = userDto.getUsr_id();



        RvwDto rvwDto = rvwService.selectUsernmEmail(usr_id);

        rvwDto.setWrt_nm(rvwDto.getUsr_nm());
        rvwDto.setWrt_email(rvwDto.getEmail());

        m.addAttribute("rvwDto", rvwDto);

        List<RvwDto> list = rvwService.selectPrdnm(usr_id); // prd_dtl_cd, prd_nm, prd_cd
        System.out.println("list = " + list);
        System.out.println("rvwDto = " + rvwDto);
        m.addAttribute("list", list);
        return "rvwRegister";
    }


        @PostMapping("/write")
        public String write (RvwDto rvwDto, Model m, RedirectAttributes rattr, HttpSession session) throws Exception {
            try {
                if(rvwDto.getRvw_ttl() == "" || rvwDto.getRvw_cont() == "" || rvwDto.getPrd_dtl_cd() == ""){
                    rattr.addFlashAttribute("msg", "RVW_REGISTER_ERR");
                    return "redirect:/review/write";
                }

                String prd_cd = rvwService.getprdCd(rvwDto.getPrd_dtl_cd());
                rvwDto.setPrd_cd(prd_cd);

                System.out.println("rvwDto = " + rvwDto);


                int rowCnt = rvwService.write(rvwDto);

                if (rowCnt == 1)
                    throw new Exception("Write failed");

                rattr.addFlashAttribute("msg", "WRT_OK");

                return "redirect:/review/list";
            } catch (Exception e) {
                e.printStackTrace();
                UserDto userDto = (UserDto) session.getAttribute("userDto");
                String usr_id = userDto.getUsr_id();
                m.addAttribute("rvwDto", rvwDto);
                List<RvwDto> list = rvwService.selectPrdnm(usr_id);
                m.addAttribute("list", list);
                rattr.addFlashAttribute("msg", "WRT_ERR");
                return "rvwRegister";
            }
        }

        @GetMapping("/modify")
        public String modify (Integer rvw_no, Model m, HttpSession session) throws Exception {
            UserDto userDto = (UserDto) session.getAttribute("userDto");
            String usr_id = userDto.getUsr_id();

            RvwDto rvwDto = rvwService.selectUsernmEmail(usr_id);
            rvwDto.setRvw_no(rvw_no);
            rvwDto.setWrt_nm(rvwDto.getUsr_nm());
            rvwDto.setWrt_email(rvwDto.getEmail());

            List<RvwDto> list = rvwService.selectPrdnm(usr_id);
            m.addAttribute("list", list);
            m.addAttribute("rvwDto", rvwDto);

            return "rvwRegister";
        }

        @PostMapping("/modify")
        public String modify (RvwDto rvwDto, SearchCondition sc, HttpSession session, Model m, RedirectAttributes rattr) throws Exception {


            try {
                if(rvwDto.getRvw_ttl() == "" || rvwDto.getRvw_cont() == "" || rvwDto.getPrd_dtl_cd() == ""){
                    rattr.addFlashAttribute("msg", "RVW_REGISTER_ERR");
                    return "redirect:/review/modify";
                }

                String prd_cd = rvwService.getprdCd(rvwDto.getPrd_dtl_cd());
                rvwDto.setPrd_cd(prd_cd);

                int rowCnt = rvwService.modify(rvwDto);

                if (rowCnt != 1)
                    throw new Exception("Modify failed");

                rattr.addFlashAttribute("msg", "MOD_OK");

                return "redirect:/review/list" + sc.getQueryString();

            } catch (Exception e) {
                e.printStackTrace();
                UserDto userDto = (UserDto) session.getAttribute("userDto");
                String usr_id = userDto.getUsr_id();
                m.addAttribute("rvwDto", rvwDto);
                List<RvwDto> list = rvwService.selectPrdnm(usr_id);
                m.addAttribute("list", list);
                rattr.addFlashAttribute("msg", "WRT_ERR");
                return "rvwRegister";
            }
        }
    }
