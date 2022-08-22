package com.devcamp.eztour.service.event;


import com.devcamp.eztour.dao.event.EventDao;
import com.devcamp.eztour.domain.event.EventDto;
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




}
