package com.devcamp.eztour.dao.reserv;

import com.devcamp.eztour.domain.reserv.ReservInfoDto;
import com.devcamp.eztour.domain.reserv.TravelerInfoDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class travelerInfoDaoImplTest {
    @Autowired
    TravelerInfoDao travelerInfoDao;

    @Test
    public void insertTrvlrInfTest() throws Exception {
        List<TravelerInfoDto> list = new ArrayList<>();
        TravelerInfoDto travelerInfoDto = new TravelerInfoDto("a001001", "아무개", "amk", "11A",800000);
        TravelerInfoDto travelerInfoDto1 = new TravelerInfoDto("a001001", "여행자1", null, "11A",800000);
        TravelerInfoDto travelerInfoDto2 = new TravelerInfoDto("a001001", "아무개", null, "11B",500000);
        list.add(travelerInfoDto);
        list.add(travelerInfoDto1);
        list.add(travelerInfoDto2);

        int rowCnt = travelerInfoDao.insertTrvlrInfo(list);
        assertTrue(rowCnt==3);
    }

    @Test
    public void selectTrvlrInfTest() throws Exception {
        String rsvt_no = "it16603758712938596";
        List<TravelerInfoDto> list = travelerInfoDao.selectTrvlrInfoList(rsvt_no);
        System.out.println("list = " + list);
        assertTrue(list.size()==2);
    }
}