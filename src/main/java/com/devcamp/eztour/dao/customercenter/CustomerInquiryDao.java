package com.devcamp.eztour.dao.customercenter;

import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import com.devcamp.eztour.domain.customercenter.CustomerSearchCondition;

import java.util.List;

public interface CustomerInquiryDao {
    int insertCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception;
    int deleteAllCustomerInquiry() throws Exception;
    int countCustomerInquiry() throws Exception;
    List<CustomerInquiryDto> searchSelectCustomerPage(CustomerSearchCondition csc) throws Exception;
    //    "count" 이름 어떻게 할 것인가??
    int searchResultCustomerCnt(CustomerSearchCondition csc) throws Exception;

}
