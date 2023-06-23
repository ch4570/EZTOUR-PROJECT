package com.devcamp.eztour.dao.reserv;

import com.devcamp.eztour.dao.guest.GuestDao;
import com.devcamp.eztour.domain.guest.GuestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class GuestDaoImplTest {
    @Autowired
    GuestDao guestDao;

    @Test
    public void insertGuest() throws Exception{
        guestDao.deleteAllGuest();

        List<GuestDto> list = guestDao.selectAllGuest();
        assertTrue(list.size()==0);

        GuestDto guestDto1 = new GuestDto("guest0001", "ahk", "01011111111", new Date(), "guest0001", new Date(), "guest0001");
        GuestDto guestDto2 = new GuestDto("guest0002", "aaaa", "01011111111", new Date(), "guest0002", new Date(), "guest0001");
        GuestDto guestDto3 = new GuestDto("guest0003", "bbbb", "01011111111", new Date(), "guest0003", new Date(), "guest0001");
        guestDao.insertGuest(guestDto1);
        guestDao.insertGuest(guestDto2);
        guestDao.insertGuest(guestDto3);

        List<GuestDto> list2 = guestDao.selectAllGuest();
        assertTrue(list2.size()==3);
    }

    @Test
    public void selectGuest() throws Exception{
        guestDao.deleteAllGuest();

        List<GuestDto> list = guestDao.selectAllGuest();
        assertTrue(list.size()==0);

        GuestDto guestDto1 = new GuestDto("guest0001", "ahk", "01011111111", new Date(), "guest0001", new Date(), "guest0001");
        guestDao.insertGuest(guestDto1);

        GuestDto guestDto2 = guestDao.selectGuest(guestDto1.getGst_id());
        assertTrue(guestDto1.getGst_id().equals(guestDto2.getGst_id()));
    }

    @Test
    public void deleteGuest() throws Exception{
        guestDao.deleteAllGuest();

        List<GuestDto> list = guestDao.selectAllGuest();
        assertTrue(list.size()==0);

        GuestDto guestDto1 = new GuestDto("guest0001", "ahk", "01011111111", new Date(), "guest0001", new Date(), "guest0001");
        guestDao.insertGuest(guestDto1);

        GuestDto guestDto2 = guestDao.selectGuest(guestDto1.getGst_id());
        assertTrue(guestDto1.getGst_id().equals(guestDto2.getGst_id()));

        guestDao.deleteGuest(guestDto2.getGst_id());
        List<GuestDto> list2 = guestDao.selectAllGuest();
        assertTrue(list2.size()==0);
    }
}