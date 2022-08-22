package com.devcamp.eztour.dao.event;


import com.devcamp.eztour.domain.event.EventDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class EventDaoImpl implements EventDao {

    @Autowired
    private SqlSession session;

    private String namespace = "com.devcamp.eztour.dao.eventMapper.";

    // 이벤트 리스트 DAO
    @Override
    public List<EventDto> eventlist() throws Exception {
        return session.selectList(namespace + "eventlist");
    }
    // 이벤트 리스트 페이징
    @Override
    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }
    @Override
    public List<EventDto>selectEventPage(Map map)throws Exception{
        return session.selectList(namespace+"selectEventPage",map);
 }

}
