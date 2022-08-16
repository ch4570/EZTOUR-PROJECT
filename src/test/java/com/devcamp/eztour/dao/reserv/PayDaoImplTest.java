//package com.devcamp.eztour.dao.reserv;
//
//import com.devcamp.eztour.domain.reserv.PayDto;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.Date;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
//public class PayDaoImplTest {
//    @Autowired
//    PayDao payDao;
//
//    @Test
//    public void selectPay() throws Exception {
//        payDao.deletePayAdmin();
//
//        PayDto payDto = new PayDto("it1660418896171", "a001001", 300000, new Date(),
//                "8A","7C","card",0);
//
//        payDao.insertPay(payDto);
//        PayDto payDto1 = payDao.selectPay(payDto.getRsvt_no());
//        assertTrue(payDto1.getPay_prc()==300000);
//    }
//
//    @Test
//    public void insertPay() throws Exception{
//        payDao.deletePayAdmin();
//
//        PayDto payDto = new PayDto("it1660418896171", "a001001", 300000, new Date(),
//                "8A","7C","card",0);
//
//        assertTrue(payDao.insertPay(payDto)==1);
//    }
//}