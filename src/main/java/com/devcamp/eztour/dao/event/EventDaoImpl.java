package com.devcamp.eztour.dao.event;


import com.devcamp.eztour.domain.event.EventDto;
import com.devcamp.eztour.domain.event.PageHandlerEvent;
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
    // 이벤트 이미지 등록 및 관리

    @Override
    public int selectEventImage()throws Exception{
        return  session.selectOne(namespace+"selectEventImage");
    }
    @Override
    public List<EventDto> selectEventImage(PageHandlerEvent pageHandlerEvent) {
        return session.selectList(namespace+"selectEventImage",pageHandlerEvent);
    }
    @Override
    public List<EventDto> searchSelectEventImage(PageHandlerEvent pageHandlerEvent) {
        return session.selectList(namespace+"searchSelectEventImage",pageHandlerEvent);
    }

    @Override
    public int selectEventImageCnt() {
        return session.selectOne(namespace+"selectEventImageCnt");
    }

    @Override
    public int searchSelectEventImageCnt(PageHandlerEvent pageHandlerEvent) {
        return session.selectOne(namespace+"searchSelectEventImageCnt",pageHandlerEvent);
    }



    @Override
    public int insertEventImage() throws Exception {
        return  session.insert(namespace + " eventInsertImage");
    }

    @Override
    public int eventInsertImage()throws Exception{
        return  session.insert(namespace + " eventInsertImage");
    }

    @Override
    public int updateEventImage(EventDto eventDto)throws Exception{
        return session.update(namespace="updateEventImage");
    }
    @Override
    public int deleteEventImage(){
        return session.delete(namespace+"deleteEventImage");
    }

}
