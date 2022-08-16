package com.devcamp.eztour.service.customerCenter;

import com.devcamp.eztour.dao.customercenter.CustomerInquiryDao;
import com.devcamp.eztour.dao.product.ProductDao;
import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerInquiryServiceImpl implements CustomerInquiryService {
    @Autowired
    CustomerInquiryDao customerInquiryDao;

    @Override
    public int writeCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception {
       return customerInquiryDao.insertCustomerInquiry(customerInquiryDto);
    }
}
