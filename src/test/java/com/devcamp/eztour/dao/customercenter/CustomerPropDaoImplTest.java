//package com.devcamp.eztour.dao.customercenter;
//
//import com.devcamp.eztour.domain.customercenter.CustomerPropDto;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.List;
//
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
//public class CustomerPropDaoImplTest {
//    @Autowired
//    private CustomerPropDao customerPropDao;
//
//    @Test
//    public void insertCustomerPropTest() throws Exception {
//        customerPropDao.deleteAllCustomerProp();
//        CustomerPropDto customerPropDto = new CustomerPropDto("test title", "test content");
//        assertTrue(customerPropDao.insertCustomerProp(customerPropDto)==1);
//
//        customerPropDto = new CustomerPropDto("test title", "test content");
//        assertTrue(customerPropDao.insertCustomerProp(customerPropDto)==1);
//        assertTrue(customerPropDao.countCustomerProp()==2);
//
//        customerPropDao.deleteAllCustomerProp();
//        customerPropDto = new CustomerPropDto("test title", "test content");
//        assertTrue(customerPropDao.insertCustomerProp(customerPropDto)==1);
//        assertTrue(customerPropDao.countCustomerProp()==1);
//    }
//
//    @Test
//    public void selectAllCustomerProp() throws Exception{
//        List<CustomerPropDto> list =  customerPropDao.selectAllCustomerProp();
//        assertTrue(list==null);
//    }
//
//}
