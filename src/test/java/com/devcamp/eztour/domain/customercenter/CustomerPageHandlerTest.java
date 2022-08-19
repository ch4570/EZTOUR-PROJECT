//package com.devcamp.eztour.domain.customercenter;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
//public class CustomerPageHandlerTest {
//    @Test
//    public void test() {
//        CustomerPageHandler cph = new CustomerPageHandler(250, 1);
//        cph.print();
//        System.out.println("cph = " + cph);
//        assertTrue(cph.getBeginPage() ==1);
//        assertTrue(cph.getEndPage() ==10);
//    }
//
//    @Test
//    public void test2() {
//        CustomerPageHandler cph = new CustomerPageHandler(250, 11);
//        cph.print();
//        System.out.println("cph = " + cph);
//        assertTrue(cph.getBeginPage() ==11);
//        assertTrue(cph.getEndPage() ==20);
//    }
//
//    @Test
//    public void test3() {
//        CustomerPageHandler cph = new CustomerPageHandler(255, 25);
//        cph.print();
//        System.out.println("cph = " + cph);
//        assertTrue(cph.getBeginPage() ==21);
//        assertTrue(cph.getEndPage() ==26);
//    }
//}