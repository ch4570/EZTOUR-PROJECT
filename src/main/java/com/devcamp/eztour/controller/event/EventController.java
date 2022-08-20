package com.devcamp.eztour.controller.event;

import com.devcamp.eztour.domain.event.EventDto;
import com.devcamp.eztour.domain.event.EventPageHandler;
import com.devcamp.eztour.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EventController {

    @Autowired
    EventService eventService;


    @RequestMapping("/event/eventList")
    public String EventList(Integer eventPage,Integer eventPageSize,Model m, HttpServletRequest request) {

        if(eventPage==null) eventPage=1;
        if(eventPageSize==null) eventPageSize=10;

        try {

            int eventTotalCnt = eventService.getCount();
            EventPageHandler eventPageHandler = new EventPageHandler(eventTotalCnt,eventPage,eventPageSize);

            Map map = new HashMap();
            map.put("offset",(eventPage-1) * eventPageSize);
            map.put("eventPageSize", eventPageSize);

            List<EventDto> list = eventService.EventPage(map);
            System.out.println("list = " + list);
            m.addAttribute("list", list);
            m.addAttribute("ph",eventPageHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "evntList.tiles";
    }


//    @RequestMapping("/event/eventList")
//    public String EventList(Model m, HttpServletRequest request) throws Exception {
//        List<EventDto> list = eventService.eventlist();
//        System.out.println("list = " + list);
//        m.addAttribute("list", list);
//
//        return "evntList.tiles";
//    }
    @GetMapping("/event/eventList/eventListLook")
    public String EventLook(){

        return "evntLook.tiles";
    }


    // 이벤트 이미지 ajax로 파일 업로드
    @GetMapping("/eventUploadAjax")
    public void eventUploadAjax(){


    }
}
