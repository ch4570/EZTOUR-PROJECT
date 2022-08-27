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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EventController {

    @Autowired
    EventService eventService;


    @RequestMapping("/event/eventList")
    public String EventList(Integer eventPage, Integer eventPageSize,String evnt_img_pth, Model m, HttpServletRequest request) {

        if (eventPage == null) eventPage = 1;
        if (eventPageSize == null) eventPageSize = 10;

        try {

            int eventTotalCnt = eventService.getCount();
            EventPageHandler eventPageHandler = new EventPageHandler(eventTotalCnt, eventPage, eventPageSize);

            if(evnt_img_pth == null){

                 EventDto eventDto = new EventDto();

                 eventDto.setEvnt_img_pth(getFolder());
                m.addAttribute("evnt_img_pth",evnt_img_pth);
            }

            Map map = new HashMap();
            map.put("offset", (eventPage - 1) * eventPageSize);
            map.put("eventPageSize", eventPageSize);

            List<EventDto> list = eventService.EventPage(map);
            System.out.println("list = " + list);
            m.addAttribute("list", list);
            m.addAttribute("ph", eventPageHandler);
            m.addAttribute("evnt_img_pth",evnt_img_pth);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "evntList.tiles";
    }

    @GetMapping("/event/eventList/eventListLook")
    public String EventLook() {
        return "evntLook.tiles";
    }

    @RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
    public void uploadAjax() {

    }

    @RequestMapping(value = "/uploadAjaxAction", method = RequestMethod.POST)
    public ResponseEntity<ArrayList<EventDto>>uploadFormPost(MultipartFile[] uploadFile,HttpSession session) {

        ArrayList<EventDto>list = new ArrayList<>();
        String path = session.getServletContext().getRealPath("/");

       String uploadFolder = "C:\\Users\\fhohf\\Documents\\EZTOUR-PROJECT\\src\\main\\webapp\\resources\\image\\event";
        File uploadPath = new File(uploadFolder, getFolder());
//        File uploadPath = new File(path, getFolder());

        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }


        for (MultipartFile multipartFile : uploadFile) {

        EventDto eventDto = new EventDto();

            System.out.println("파일명="+multipartFile.getOriginalFilename());
            System.out.println("파일사이즈="+multipartFile.getSize());

            UUID uuid = UUID.randomUUID();
            System.out.println(uuid.toString());

            eventDto.setEvnt_img_pth(getFolder());
            eventDto.setFileName(multipartFile.getOriginalFilename());
            eventDto.setUuid(uuid.toString());



            File saveFile = new File(uploadPath, uuid.toString() + "_" + multipartFile.getOriginalFilename());

            try {
                multipartFile.transferTo(saveFile);

                if (checkImageType(saveFile)) {

                    eventDto.setImage(true);

                    FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath,"s_"+uuid.toString()+"_"+multipartFile.getOriginalFilename()));

              //     Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumbnail,150,150);

                    thumbnail.close();
                }
                list.add(eventDto);
            }catch (Exception e){
                System.out.println(e.getMessage());
        }
    }
return new ResponseEntity<>(list,HttpStatus.OK);
}

    private String getFolder(){

        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String str = sdf.format(date);

        return str.replace("-","\\");
    }

    private boolean checkImageType(File file){

        try{
            String contentType = Files.probeContentType(file.toPath());
            System.out.println("contentType="+contentType);
        return contentType.startsWith("image");
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }


}






    //

//    @GetMapping("/event/insert/image")
//    public String insertEventImage(HttpSession session) {
//        return "eventImage";
//    }
//
//
//    // 이벤트 이미지 ajax로 파일 업로드
//    @PostMapping("/event/insert/image")
//    public String insertEventImage(MultipartFile img_file, HttpServletRequest request,String prd_cd) throws Exception {
//        File checkFile = new File(img_file.getOriginalFilename());
//        String type = null;
//        try {
//            type = Files.probeContentType(checkFile.toPath());
//            // 프로젝트 root 경로 확인 -> 이미지 경로 잡기
//            HttpSession session = request.getSession();
//            String root_path = session.getServletContext().getRealPath("/");
//            String uploadPath = root_path + "resources/image/event/";
//            // 이미지 파일이 아닐경우 실패
//            if (!type.startsWith("image")) {
//                return "fail";
//            } else if (type == null) {
//                return "fail";
//            } else {
//                String fileName = UUID.
//                        randomUUID().toString() + ".jpg";
//                File uploadFile = new File(uploadPath, fileName);
//                img_file.transferTo(uploadFile);
//                String finalPath = "/image/event/" + fileName;
//                EventDto event_Dto = new EventDto(finalPath);
//                int result = eventService.eventInsertImage(event_Dto);
//                if (result == 1) {
//                    return "success";
//                } else {
//                    return "fail";
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
////    @GetMapping("/event/image/read")
////    public String productImageRead(String evnt_img_pth,Model model,HttpSession session){
////
////            model.addAttribute("evnt_img_pth",evnt_img_pth);
////
////            return "eventImageRead";
////        }
////
////    @GetMapping("/event/image/modify")
////    public String productImageModify(HttpSession session,String evnt_img_pth,Model model) throws Exception{
////            model.addAttribute("evnt_img_pth",evnt_img_pth);
////            return "eventImageModify";
////        }
////    private boolean deleteImage(HttpServletRequest request,String evnt_img_pth){
////        HttpSession session = request.getSession();
////        String root_path = session.getServletContext().getRealPath("/");
////        String uploadPath = root_path+"resources"+evnt_img_pth;
////        File file = new File(uploadPath);
////        boolean flag = file.delete();
////        return flag;
////    }
////
////    @ResponseBody
////    @PostMapping("/event/image/modify")
////    public String productImageModify(MultipartFile img_file, String evnt_img_pth,HttpServletRequest request) throws Exception{
////        // 원본 파일이 이미지 파일이 맞는지 확장자를 확인
////        File checkFile = new File(img_file.getOriginalFilename());
////        String type = null;
////        try {
////            type = Files.probeContentType(checkFile.toPath());
////            // 프로젝트 root 경로 확인 -> 이미지 경로 잡기
////            HttpSession session = request.getSession();
////            String root_path = session.getServletContext().getRealPath("/");
////            String uploadPath = root_path+"resources/image/event";
////            boolean flag = deleteImage(request,evnt_img_pth);
////            if(flag){
////                // 이미지 파일이 아닐경우 실패
////                if(!type.startsWith("image")){
////                    return "fail";
////                }else if(type==null){
////                    return "fail";
////                }else{
////                    String fileName = UUID.
////                            randomUUID().toString()+".jpg";
////                    File uploadFile = new File(uploadPath, fileName);
////                    img_file.transferTo(uploadFile);
////                    String finalPath = "/image/event/"+fileName;
////                    EventDto eventDto = new EventDto();
////                    int result = eventService.eventInsertImage(eventDto);
////                    if(result == 1){
////                        return "success";
////                    }else{
////                        return "fail";
////                    }
////
////                }
////            }else{
////                return "fail";
////            }
////
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
////    }
////    @ResponseBody
////    @PostMapping("/event/image/delete")
////    public String productImageDelete(String evnt_img_pth,HttpServletRequest request) throws Exception{
////        int result = eventService.deleteEventImage();
////
////        if(result==1){
////            // 이미지를 삭제
////            boolean flag = deleteImage(request,evnt_img_pth);
////            if(flag){
////                return "success";
////            }else{
////                return "fail";
////            }
////        }
////        return "fail";
////    }
////
////    // 이미지 관리
////
////    @GetMapping("/event/management/image")
////    public String productImageManagement(HttpSession session,Model model,@RequestParam(value = "page",defaultValue = "1") int page
////            ,String search_option,String search_keyword) throws Exception{
////        boolean isAdmin = isAdmin(session);
////        if(!isAdmin){
////            return "redirect:/";
////        }else{
////            if(search_option == null || search_option == ""){
////                int totalCnt = eventService.selectEventImageCnt();
////                PageHandlerEvent paging = new PageHandlerEvent(totalCnt,page,5);
////                List<EventDto> eventDtoList = eventService.selectEventImage(paging);
////                model.addAttribute("event_list",eventDtoList);
////                model.addAttribute("paging",paging);
////                return "eventManagement";
////            }else{
////                PageHandlerEvent option = new PageHandlerEvent(search_option,search_keyword);
////                int totalCnt = eventService.searchSelectEventImageCnt(option);
////                PageHandlerEvent paging = new PageHandlerEvent(totalCnt,page,search_option,search_keyword,5);
////                List<EventDto> eventDtoList = eventService.searchSelectEventImage(paging);
////                model.addAttribute("event_list",eventDtoList);
////                model.addAttribute("paging",paging);
////                return "eventManagement";
////            }
////        }
////    }
////
////    private boolean isAdmin(HttpSession session) {
////        String id = (String)session.getAttribute("usr_id");
////        if(id.equals("admin")){
////            return true;
////        }else{
////            return false;
////        }
////    }
//}




