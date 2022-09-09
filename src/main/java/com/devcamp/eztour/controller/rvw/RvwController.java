package com.devcamp.eztour.controller.rvw;


import com.devcamp.eztour.dao.rvw.RvwDao;
import com.devcamp.eztour.domain.rvw.PageHandler;
import com.devcamp.eztour.domain.rvw.RvwDto;
import com.devcamp.eztour.domain.rvw.RvwLkAdmDto;
import com.devcamp.eztour.domain.rvw.SearchCondition;
import com.devcamp.eztour.domain.user.UserDto;
import com.devcamp.eztour.service.rvw.RvwLkAdmService;
import com.devcamp.eztour.service.rvw.RvwService;
import com.devcamp.eztour.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/review")
public class RvwController {

    @Autowired
    UserService userService;
    @Autowired
    RvwService rvwService;
    @Autowired
    RvwLkAdmService rvwLkAdmService;


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

        return "redirect:/review/list.tiles"+sc.getQueryString();
        }

    @GetMapping("/list")
    public String list(SearchCondition sc, Model m) {

        if (sc.getCntn_cd() == "") {
            try {

                int totalCnt = rvwService.getSearchResultCnt(sc);
                m.addAttribute("totalCnt", totalCnt);

                PageHandler pageHandler = new PageHandler(totalCnt, sc);

                List<RvwDto> list = rvwService.getSearchResultPage(sc);

                m.addAttribute("list", list);
                m.addAttribute("ph", pageHandler);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "review/rvwList.tiles";

        }
        else {
            try {
                int totalCnt = rvwService.cntnCdSearchResultCnt(sc);

                m.addAttribute("totalCnt", totalCnt);

                PageHandler pageHandler = new PageHandler(totalCnt, sc);

                System.out.println("sc.getCntn_cd() = " + sc.getCntn_cd());


                List<RvwDto> list = rvwService.cntnCdSearch(sc);
                m.addAttribute("list", list);
                m.addAttribute("ph", pageHandler);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "review/rvwList.tiles";
        }
    }



    @GetMapping("/read")
    public String read(Integer rvw_no, SearchCondition sc, RedirectAttributes rattr, Model m, HttpSession session) throws Exception {
        String usr_id = null;
        try {
            UserDto userDto = (UserDto) session.getAttribute("userDto");
            usr_id = userDto.getUsr_id();
            System.out.println("usr_id = " + usr_id);
        } catch (NullPointerException e) {
            usr_id = ""; // 비회원
        }

        RvwDto rvwDto = rvwService.read(rvw_no);
        System.out.println("rvwDto = " + rvwDto);
        m.addAttribute("rvwDto", rvwDto);


        // 1. rvw_lk_adm 테이블 데이터가 있는지 아니면 rvw_lk_adm 테이블 데이터가 없는지 조건문
        RvwLkAdmDto rvwLkAdmDto = rvwLkAdmService.select(usr_id, rvw_no);
        if(rvwLkAdmDto == null && usr_id != "") // 회원 + 좋아요를 누른적이 없다면,
            rvwLkAdmService.insert(usr_id, rvw_no); // 게시물 read 시, rvw_lk_adm 테이블에 insert
            rvwLkAdmDto = rvwLkAdmService.select(usr_id,rvw_no); //

        System.out.println("rvwLkAdmDto = " + rvwLkAdmDto);

        m.addAttribute("rvwLkAdmDto", rvwLkAdmDto);

        // 내가 작성한 글만 "삭제", "수정" 버튼 보이기 위한 조건
        int rowCnt = rvwService.checkRvwUser(usr_id, rvw_no);
        System.out.println("rowCnt = " + rowCnt);
        if(rowCnt == 1)
            m.addAttribute("check", "me");



// 나중에 아래 내용도 같이 예외 같이 처리하기
//        catch (Exception e) {
//            e.printStackTrace();
//            rattr.addFlashAttribute("msg","READ_ERR");
//            return "redirect:/rvwList"+sc.getQueryString();
//        }

        return "review/rvwDetail.tiles";
    }


    @GetMapping("/write")
    public String write(Model m, RedirectAttributes rattr, HttpSession session) throws Exception {
        if(session.getAttribute("userDto")==null){
            return "redirect:/user/login.tiles";
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
        m.addAttribute("mode", "new");
        return "review/rvwRegister.tiles";
    }


        @PostMapping("/write")
        public String write (RvwDto rvwDto, Model m, RedirectAttributes rattr, HttpSession session) throws Exception {
            m.addAttribute("mode", "new");
            try {
                if(rvwDto.getRvw_ttl() == "" || rvwDto.getRvw_cont() == "" || rvwDto.getPrd_dtl_cd() == ""){
                    rattr.addFlashAttribute("msg", "RVW_REGISTER_ERR");
                    return "redirect:/review/write.tiles";
                }

//                 줄 바꾸기
                String rvw_cont = rvwDto.getRvw_cont().replace("\r\n","<br>");
                rvwDto.setRvw_cont(rvw_cont);

                String prd_cd = rvwService.getprdCd(rvwDto.getPrd_dtl_cd());
                rvwDto.setPrd_cd(prd_cd);

                System.out.println("rvwDto = " + rvwDto);


                int rowCnt = rvwService.write(rvwDto);

                if (rowCnt != 1) {
                    throw new Exception("Write failed");
                }

                rattr.addFlashAttribute("msg", "WRT_OK");

                return "redirect:/review/list.tiles";
            } catch (Exception e) {
                e.printStackTrace();
                UserDto userDto = (UserDto) session.getAttribute("userDto");
                String usr_id = userDto.getUsr_id();
                m.addAttribute("rvwDto", rvwDto);
                List<RvwDto> list = rvwService.selectPrdnm(usr_id);
                m.addAttribute("list", list);
                rattr.addFlashAttribute("msg", "WRT_ERR");
                return "review/rvwRegister.tiles";
            }
        }

        @GetMapping("/modify")
        public String modify (RvwDto rvwDto, Integer rvw_no, Model m, HttpSession session) throws Exception {
            UserDto userDto = (UserDto) session.getAttribute("userDto");
            String usr_id = userDto.getUsr_id();

//            rvwDto = rvwService.selectUsernmEmail(usr_id);
//            rvwDto.setRvw_no(rvw_no);
//            rvwDto.setWrt_nm(rvwDto.getUsr_nm());
//            rvwDto.setWrt_email(rvwDto.getEmail());
            System.out.println("rvw_no = " + rvw_no);
            rvwDto = rvwService.getRvwttlRvwCont(rvw_no);

            List<RvwDto> list = rvwService.selectPrdnm(usr_id);
            m.addAttribute("list", list);
            m.addAttribute("rvwDto", rvwDto);
            return "review/rvwRegister.tiles";
        }

        @PostMapping("/modify")
        public String modify (RvwDto rvwDto, SearchCondition sc, HttpSession session, Model m, RedirectAttributes rattr) throws Exception {
            int rvw_no = rvwDto.getRvw_no();
            try {
                if(rvwDto.getRvw_ttl() == "" || rvwDto.getRvw_cont() == "" || rvwDto.getPrd_dtl_cd() == ""){
                    System.out.println("rvw_no = " + rvw_no);
                    rattr.addFlashAttribute("msg", "RVW_REGISTER_ERR");
                    return "redirect:/review/modify.tiles?rvw_no=" + rvw_no;
                }


                //                 줄 바꾸기
                String rvw_cont = rvwDto.getRvw_cont().replace("\r\n","<br>");
                rvwDto.setRvw_cont(rvw_cont);

                String prd_cd = rvwService.getprdCd(rvwDto.getPrd_dtl_cd());
                rvwDto.setPrd_cd(prd_cd);

                int rowCnt = rvwService.modify(rvwDto);

                if (rowCnt != 1)
                    throw new Exception("Modify failed");

                rattr.addFlashAttribute("msg", "MOD_OK");

                return "redirect:/review/list.tiles" + sc.getQueryString();

            } catch (Exception e) {
                e.printStackTrace();
                UserDto userDto = (UserDto) session.getAttribute("userDto");
                String usr_id = userDto.getUsr_id();
                m.addAttribute("rvwDto", rvwDto);
                List<RvwDto> list = rvwService.selectPrdnm(usr_id);
                m.addAttribute("list", list);
                rattr.addFlashAttribute("msg", "WRT_ERR");
                return "review/rvwRegister.tiles";
            }
        }
    }
