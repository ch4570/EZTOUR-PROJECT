package com.devcamp.eztour.service.event;

import com.devcamp.eztour.domain.event.EventDto;

import java.util.List;
import java.util.Map;

public interface EventService {
    List<EventDto> eventlist()throws Exception;


    // 이벤트 리스트 페이징
    int getCount() throws Exception;

    // 이벤트 리스트 페이징
    List<EventDto> EventPage(Map map)throws Exception;
}
