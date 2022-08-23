package com.devcamp.eztour.dao.customercenter;

import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import com.devcamp.eztour.domain.customercenter.CustomerPropDto;
import com.devcamp.eztour.domain.customercenter.CustomerSearchCondition;

import java.util.List;
import java.util.Map;

public interface CustomerInquiryDao {
    List<CustomerInquiryDto> selectAllCustomerInquiry() throws Exception;

    int insertCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception;

}
