package com.devcamp.eztour.dao.event;

import com.devcamp.eztour.domain.event.EventDto;
import com.devcamp.eztour.domain.event.PageHandlerEvent;

import java.util.List;
import java.util.Map;

public interface EventDao {

   public List<EventDto> eventlist() throws Exception;

    // 이벤트 리스트 페이징
    int count() throws Exception;

    List<EventDto>selectEventPage(Map map)throws Exception;

    int selectEventImage()throws Exception;

    List<EventDto> selectEventImage(PageHandlerEvent pageHandlerEvent);

 List<EventDto> searchSelectEventImage(PageHandlerEvent pageHandlerEvent);

 int selectEventImageCnt();

    int searchSelectEventImageCnt(PageHandlerEvent pageHandlerEvent);

    int insertEventImage()throws Exception;


    int eventInsertImage()throws Exception;

 int updateEventImage(EventDto eventDto)throws Exception;

 int deleteEventImage();
}
