package com.devcamp.eztour.dao.reserv;

import com.devcamp.eztour.domain.reserv.CancelViewDto;
import com.devcamp.eztour.domain.reserv.PayDto;
import com.devcamp.eztour.domain.reserv.ReservDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class PayDaoImplTest {
    @Autowired
    PayDao payDao;
    @Autowired
    ReservDao reservDao;

    @Test
    public void selectPay() throws Exception {
//        payDao.deletePayAdmin();
//        String pay_no = String.valueOf(UUID.randomUUID());
//        PayDto payDto = new PayDto(pay_no, "it1660418896171", "a001001", "asdf", null, Long.valueOf("300000"), new Date(),
//                "8A","7C","card",0,0, null);
//
//        payDao.insertPay(payDto);
//        PayDto payDto1 = payDao.selectPay(payDto.getRsvt_no());
//        assertTrue(payDto1.getPay_prc()==300000);
    }

    @Test
    public void insertPay() throws Exception{
//        payDao.deletePayAdmin();
//        payDao.deletePayAdmin();
//        String pay_no = String.valueOf(UUID.randomUUID());
//
//        PayDto payDto = new PayDto(pay_no, "it1660418896171", "a001001", "asdf", null, Long.valueOf("300000"), new Date(),
//                "8A","7C","card",0,0, null);
//
//        assertTrue(payDao.insertPay(payDto)==1);
    }

    @Test
    public void selectPayStatus() throws Exception{
//        int ranNum = (int)(Math.random() * 9000 + 1000);
//        String rsvt_no = ranNum+System.currentTimeMillis()+"";
//        ReservDto reservDto = new ReservDto(rsvt_no, "a001001", "asdf", "asdf", "adsf", "01010101010", "sdf33@jajsdf.com", 1000000, 1000000, null, "6E", "7A", new Date(), null, 1, 0, 0);
//        reservDao.insertReserv(reservDto);
//
//        String usr_id = "asdf";
//
//        payDao.deletePayAdmin();
//        String pay_no = String.valueOf(UUID.randomUUID());
//
//        PayDto payDto = new PayDto(pay_no, "it1660418896171", "a001001", "asdf", null, Long.valueOf("300000"), new Date(),
//                "8A","7C","card",0,0, null);
//
//        payDao.insertPay(payDto);
//
//
//        Map<String, String> map = new HashMap<>();
//        map.put("rsvt_no", rsvt_no);
//        map.put("usr_id", usr_id);
////        assertTrue(payDao.selectPayStatus(map).equals(reservDto.getCmn_cd_pay_stt()));
//        System.out.println("payDao.selectPayStatus(map) = " + payDao.selectPayStatus(map));
//        assertTrue(payDao.selectPayStatus(map).equals(payDto.getCmn_cd_pay_stt()));
    }

    @Test
    public void selectCncInfo() throws Exception{
        String rsvt_no = "A010011661104869005";
        CancelViewDto cancelViewDto = payDao.selectCancelInfo(rsvt_no);

//        assertTrue(rsvt_no.equals(cancelViewDto.getRsvt_no()));
    }

    @Test
    public void selectPayByIdTest() throws Exception{
        String rsvt_no = "A010011661104869005";
        Map<String, String> map = new HashMap<>();
        map.put("rsvt_no", rsvt_no);
        map.put("usr_id", "asdf");
        PayDto payDto = payDao.selectPayById(map);
        assertTrue(rsvt_no.equals(payDto.getRsvt_no()));
    }

}