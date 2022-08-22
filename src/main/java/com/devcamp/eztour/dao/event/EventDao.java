package com.devcamp.eztour.dao.event;

import com.devcamp.eztour.domain.event.EventDto;

import java.util.List;
import java.util.Map;

public interface EventDao {

//    int insertEvent(EventDto dto) throws Exception;
//
//    List<EventDto> selectAll() throws Exception;
//
//
//    int selectEvent() throws Exception;

    //  @Override
  //    public int insertEvent(EventDto dto) throws Exception{
  //        return session.insert(namespace + "insertEvent" );
  //    }
   public List<EventDto> eventlist() throws Exception;

    // 이벤트 리스트 페이징
    int count() throws Exception;

    List<EventDto>selectEventPage(Map map)throws Exception;
}
