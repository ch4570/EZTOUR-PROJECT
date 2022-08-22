package com.devcamp.eztour.domain.event;

import com.devcamp.eztour.dao.event.EventDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class EventPageHandlerTest {

    @Autowired
    private EventDao eventDao;

//    @Test
//    public void insertTestData()throws Exception{
//        eventDao.deleteAll();
//        for(int i = 1; i<=220; i++){
//            EventDto eventDto = new EventDto("evnt_no" + i ,"evnt_ttl","evnt_cont");
//            eventDao.insert(eventDto);
//        }




    @Test
    public void eventPrintTest() {
        EventPageHandler ph = new EventPageHandler(200,1);
        ph.print();
        System.out.println("ph = " + ph);
        assertTrue(ph.getEventBeginPage()==1);
        assertTrue(ph.getEventEndPage()==10);
    }

    @Test
    public void eventPrintTest2() {
        EventPageHandler ph = new EventPageHandler(200,11);
        ph.print();
        System.out.println("ph = " + ph);
        assertTrue(ph.getEventBeginPage()==11);
        assertTrue(ph.getEventEndPage()==20);
    }

    @Test
    public void eventPrintTest3() {
        EventPageHandler ph = new EventPageHandler(250,21);
        ph.print();
        System.out.println("ph = " + ph);
        assertTrue(ph.getEventBeginPage()==21);
        assertTrue(ph.getEventEndPage()==30);
    }


}