package com.devcamp.eztour.dao.customercenter;

import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import com.devcamp.eztour.domain.customercenter.CustomerPropDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class CustomerInquiryDaoImplTest {
    @Autowired
    CustomerInquiryDao customerInquiryDao;

    @Test
    public void selectAllCustomerProp() throws Exception{
        List<CustomerInquiryDto> list =  customerInquiryDao.selectAllCustomerInquiry();
        assertTrue(list==null);
    }

    @Test
    public void insertCustomerInquiry() {
    }
}