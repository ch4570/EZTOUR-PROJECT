//package com.devcamp.eztour.dao.customercenter;
//
//import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
//public class CustomerInquiryDoaImplTest {
//    @Test
//    public void insertTest() throws Exception {
//        CustomerInquiryDao.deleteAllCustomerInquiry();
//        CustomerInquiryDto customerInquiryDto = new CustomerInquiryDto();
//        assertTrue(CustomerInquiryDao.insertCustomerInquiry(customerInquiryDto)==1);
//
//        customerInquiryDto = new CustomerInquiryDto();
//        assertTrue(CustomerInquiryDao.insertCustomerInquiry(CustomerInquiryDto)==1);
//        assertTrue(CustomerInquiryDao.countCustomerInquiry()==2);
//
//        CustomerInquiryDao.deleteAllCustomerInquiry();
//        customerInquiryDto = new CustomerInquiryDto();
//        assertTrue(CustomerInquiryDao.insertCustomerInquiry(CustomerInquiryDto)==1);
//        assertTrue(CustomerInquiryDao.countCustomerInquiry()==1);
//    }
//}
