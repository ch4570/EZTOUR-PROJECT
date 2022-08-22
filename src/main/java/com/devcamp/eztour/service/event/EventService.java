package com.devcamp.eztour.service.event;

import com.devcamp.eztour.domain.event.EventDto;
import com.devcamp.eztour.domain.event.PageHandlerEvent;

import java.util.List;
import java.util.Map;

public interface EventService {
    List<EventDto> eventlist()throws Exception;


    // 이벤트 리스트 페이징
    int getCount() throws Exception;

    // 이벤트 리스트 페이징
    List<EventDto> EventPage(Map map)throws Exception;

    int EventImage()throws Exception;

    int selectEventImageCnt() throws Exception;

    List<EventDto> searchSelectEventImage(PageHandlerEvent pageHandlerEvent) throws Exception;

    List<EventDto> selectEventImage(PageHandlerEvent pageHandlerEvent) throws Exception;

    int searchSelectEventImageCnt(PageHandlerEvent pageHandlerEvent) throws Exception;

    int eventInsertImage(EventDto eventDto)throws Exception;

    int deleteEventImage()throws Exception;

    int updateEventImage(EventDto eventDto)throws Exception;
}
