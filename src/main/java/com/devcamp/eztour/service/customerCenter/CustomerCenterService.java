package com.devcamp.eztour.service.customerCenter;

import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import com.devcamp.eztour.domain.customercenter.CustomerPropDto;
import com.devcamp.eztour.domain.customercenter.CustomerSearchCondition;

import java.util.List;
import java.util.Map;

public interface CustomerCenterService {
//    inquiry 서비스 시작
    List<CustomerInquiryDto> getAllCustomerInquiry() throws Exception;
    int writeCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception;
//    inquiry 서비스 끝

//    prop 서비스 시작
    List<CustomerPropDto> getAllCustomerProp() throws Exception;
    int writeCustomerProp(CustomerPropDto customerPropDto) throws Exception;
//    prop 서비스 끝

}
