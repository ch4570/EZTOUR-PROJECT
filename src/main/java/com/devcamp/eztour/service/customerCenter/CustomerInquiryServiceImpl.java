package com.devcamp.eztour.service.customerCenter;

import com.devcamp.eztour.dao.customercenter.CustomerInquiryDao;
import com.devcamp.eztour.dao.product.ProductDao;
import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import com.devcamp.eztour.domain.customercenter.CustomerSearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerInquiryServiceImpl implements CustomerInquiryService {
    @Autowired
    CustomerInquiryDao customerInquiryDao;

    @Override
    public int writeCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception {
       return customerInquiryDao.insertCustomerInquiry(customerInquiryDto);
    }

    @Override
    public List<CustomerInquiryDto> getCustomerSearchResultPage(CustomerSearchCondition csc) throws Exception{
        return customerInquiryDao.searchSelectCustomerPage(csc);
    }

    @Override
    public int getCustomerSearchResultCnt(CustomerSearchCondition csc) throws Exception{
        return customerInquiryDao.searchResultCustomerCnt(csc);
    }
}
