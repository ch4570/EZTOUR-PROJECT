package com.devcamp.eztour.service.event;


import com.devcamp.eztour.dao.event.EventDao;
import com.devcamp.eztour.domain.event.EventDto;
import com.devcamp.eztour.domain.event.PageHandlerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EventServiceImpl implements EventService {

      @Autowired
      EventDao eventDao;
      // 이벤트 리스트 목록
      @Override
      public List<EventDto> eventlist()throws Exception{
            return eventDao.eventlist();
      }
      // 이벤트 리스트 페이징
      @Override
      public int getCount() throws Exception {
            return eventDao.count();
    }

      @Override
      public List<EventDto> EventPage(Map map)throws Exception{
            return eventDao.selectEventPage(map);
      }
      //

      @Override
      public int EventImage()throws Exception{
          return  eventDao.selectEventImage();
      }

    @Override
    public int selectEventImageCnt() throws Exception{
        return eventDao.selectEventImageCnt();
    }

    @Override
    public List<EventDto> searchSelectEventImage(PageHandlerEvent pageHandlerEvent) throws Exception{
        return eventDao.searchSelectEventImage(pageHandlerEvent);
    }
    @Override
    public List<EventDto> selectEventImage(PageHandlerEvent pageHandlerEvent) throws Exception{
        return eventDao.selectEventImage(pageHandlerEvent);
    }
    @Override
    public int searchSelectEventImageCnt(PageHandlerEvent pageHandlerEvent) throws Exception{
        return eventDao.searchSelectEventImageCnt(pageHandlerEvent);
    }














      @Override
      public int eventInsertImage(EventDto eventDto)throws Exception{
          return eventDao.eventInsertImage();
      }

      @Override
      public int deleteEventImage()throws Exception{
          return eventDao.deleteEventImage();
      }

      @Override
    public int updateEventImage(EventDto eventDto)throws Exception{
          return eventDao.updateEventImage(eventDto);
      }






}
