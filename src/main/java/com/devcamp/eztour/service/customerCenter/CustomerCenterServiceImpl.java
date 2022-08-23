package com.devcamp.eztour.service.customerCenter;

import com.devcamp.eztour.dao.customercenter.CustomerInquiryDao;
import com.devcamp.eztour.dao.customercenter.CustomerPropDao;
import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import com.devcamp.eztour.domain.customercenter.CustomerPropDto;
import com.devcamp.eztour.domain.customercenter.CustomerSearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerCenterServiceImpl implements CustomerCenterService {

    @Autowired
    CustomerPropDao customerPropDao;
    CustomerInquiryDao customerInquiryDao;

//    inquiry 시작
    @Override
    public List<CustomerInquiryDto> getAllCustomerInquiry() throws Exception{
        return customerInquiryDao.selectAllCustomerInquiry();
    }

    @Override
    public int writeCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception {
        return customerInquiryDao.insertCustomerInquiry(customerInquiryDto);
    }
//    inquiry끝

    @Override
    public List<CustomerPropDto> getAllCustomerProp() throws Exception{
        return customerPropDao.selectAllCustomerProp();
    }

    @Override
    public int writeCustomerProp(CustomerPropDto customerPropDto) throws Exception {
        return customerPropDao.insertCustomerProp(customerPropDto);
    }
}
