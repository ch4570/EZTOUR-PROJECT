package com.devcamp.eztour.service.reserv;

import com.devcamp.eztour.domain.reserv.ReservDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"
        ,"file:src/main/webapp/WEB-INF/spring/**/security-context.xml"})
public class ReservServiceImplTest {

    @Autowired
    ReservService reservService;

    //Tx test
    @Test
    public void testCancelReserv() throws Exception{
        ReservDto reservDto = new ReservDto();
//        reservDto.setCmn_cd_rsvt_stt("6D");
//        reservDto.setCmn_cd_pay_stt("7B");
        reservDto.setCmn_cd_rsvt_stt("6A");
        reservDto.setCmn_cd_pay_stt("7A");
        reservDto.setRsvt_no("A020061687351666438");

        reservService.cancelReserv(reservDto);
    }
}