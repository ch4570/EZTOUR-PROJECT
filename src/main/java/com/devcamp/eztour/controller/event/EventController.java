package com.devcamp.eztour.controller.event;

import com.devcamp.eztour.domain.event.EventDto;

import com.devcamp.eztour.domain.event.EventPageHandler;
import com.devcamp.eztour.domain.event.PageHandlerEvent;
import com.devcamp.eztour.domain.product.PageHandlerProduct;
import com.devcamp.eztour.domain.product.PrdImgDto;
import com.devcamp.eztour.domain.product.TrvPrdReadDto;
import com.devcamp.eztour.domain.product.TrvSchImgDto;
import com.devcamp.eztour.service.event.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EventController {

    @Autowired
    EventService eventService;


    @RequestMapping("/event/eventList")
    public String EventList(Integer eventPage, Integer eventPageSize, Model m, HttpServletRequest request) {

        if (eventPage == null) eventPage = 1;
        if (eventPageSize == null) eventPageSize = 10;

        try {

            int eventTotalCnt = eventService.getCount();
            EventPageHandler eventPageHandler = new EventPageHandler(eventTotalCnt, eventPage, eventPageSize);

            Map map = new HashMap();
            map.put("offset", (eventPage - 1) * eventPageSize);
            map.put("eventPageSize", eventPageSize);

            List<EventDto> list = eventService.EventPage(map);
            System.out.println("list = " + list);
            m.addAttribute("list", list);
            m.addAttribute("ph", eventPageHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "evntList.tiles";
    }

    @GetMapping("/event/eventList/eventListLook")
    public String EventLook() {
        return "evntLook.tiles";
    }

    @GetMapping("/event/image/insert")
    public String insertEventImage() {
        return "eventImage";
    }


    // 이벤트 이미지 ajax로 파일 업로드
    @PostMapping("/event/image/insert")
    @ResponseBody
    public String insertEventImage(MultipartFile img_file, HttpServletRequest request) throws Exception {
        File checkFile = new File(img_file.getOriginalFilename());
        String type = null;
        try {
            type = Files.probeContentType(checkFile.toPath());
            // 프로젝트 root 경로 확인 -> 이미지 경로 잡기
            HttpSession session = request.getSession();
            String root_path = session.getServletContext().getRealPath("/");
            String uploadPath = root_path + "resources/image/event";
            // 이미지 파일이 아닐경우 실패
            if (!type.startsWith("image")) {
                return "fail";
            } else if (type == null) {
                return "fail";
            } else {
                String fileName = UUID.
                        randomUUID().toString() + ".jpg";
                File uploadFile = new File(uploadPath, fileName);
                img_file.transferTo(uploadFile);
                String finalPath = "/image/event/" + fileName;
                EventDto eventDto = new EventDto();
                int result = eventService.eventInsertImage(eventDto);
                if (result == 1) {
                    return "success";
                } else {
                    return "fail";
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/event/image/read")
    public String productImageRead(String evnt_img_pth,Model model,HttpSession session){

            model.addAttribute("evnt_img_pth",evnt_img_pth);

            return "eventImageRead";
        }

    @GetMapping("/event/image/modify")
    public String productImageModify(HttpSession session,String evnt_img_pth,Model model) throws Exception{
            model.addAttribute("evnt_img_pth",evnt_img_pth);
            return "eventImageModify";
        }
    private boolean deleteImage(HttpServletRequest request,String evnt_img_pth){
        HttpSession session = request.getSession();
        String root_path = session.getServletContext().getRealPath("/");
        String uploadPath = root_path+"resources"+evnt_img_pth;
        File file = new File(uploadPath);
        boolean flag = file.delete();
        return flag;
    }

    @ResponseBody
    @PostMapping("/event/image/modify")
    public String productImageModify(MultipartFile img_file, String evnt_img_pth,HttpServletRequest request) throws Exception{
        // 원본 파일이 이미지 파일이 맞는지 확장자를 확인
        File checkFile = new File(img_file.getOriginalFilename());
        String type = null;
        try {
            type = Files.probeContentType(checkFile.toPath());
            // 프로젝트 root 경로 확인 -> 이미지 경로 잡기
            HttpSession session = request.getSession();
            String root_path = session.getServletContext().getRealPath("/");
            String uploadPath = root_path+"resources/image/event";
            boolean flag = deleteImage(request,evnt_img_pth);
            if(flag){
                // 이미지 파일이 아닐경우 실패
                if(!type.startsWith("image")){
                    return "fail";
                }else if(type==null){
                    return "fail";
                }else{
                    String fileName = UUID.
                            randomUUID().toString()+".jpg";
                    File uploadFile = new File(uploadPath, fileName);
                    img_file.transferTo(uploadFile);
                    String finalPath = "/image/event/"+fileName;
                    EventDto eventDto = new EventDto();
                    int result = eventService.eventInsertImage(eventDto);
                    if(result == 1){
                        return "success";
                    }else{
                        return "fail";
                    }

                }
            }else{
                return "fail";
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @ResponseBody
    @PostMapping("/event/image/delete")
    public String productImageDelete(String evnt_img_pth,HttpServletRequest request) throws Exception{
        int result = eventService.deleteEventImage();

        if(result==1){
            // 이미지를 삭제
            boolean flag = deleteImage(request,evnt_img_pth);
            if(flag){
                return "success";
            }else{
                return "fail";
            }
        }
        return "fail";
    }

    // 이미지 관리

    @GetMapping("/event/management/image")
    public String productImageManagement(HttpSession session,Model model,@RequestParam(value = "page",defaultValue = "1") int page
            ,String search_option,String search_keyword) throws Exception{
        boolean isAdmin = isAdmin(session);
        if(!isAdmin){
            return "redirect:/";
        }else{
            if(search_option == null || search_option == ""){
                int totalCnt = eventService.selectEventImageCnt();
                PageHandlerEvent paging = new PageHandlerEvent(totalCnt,page,5);
                List<EventDto> eventDtoList = eventService.selectEventImage(paging);
                model.addAttribute("event_list",eventDtoList);
                model.addAttribute("paging",paging);
                return "eventManagement";
            }else{
                PageHandlerEvent option = new PageHandlerEvent(search_option,search_keyword);
                int totalCnt = eventService.searchSelectEventImageCnt(option);
                PageHandlerEvent paging = new PageHandlerEvent(totalCnt,page,search_option,search_keyword,5);
                List<EventDto> eventDtoList = eventService.searchSelectEventImage(paging);
                model.addAttribute("event_list",eventDtoList);
                model.addAttribute("paging",paging);
                return "eventManagement";
            }
        }
    }

    private boolean isAdmin(HttpSession session) {
        String id = (String)session.getAttribute("usr_id");
        if(id.equals("admin")){
            return true;
        }else{
            return false;
        }
    }
}




