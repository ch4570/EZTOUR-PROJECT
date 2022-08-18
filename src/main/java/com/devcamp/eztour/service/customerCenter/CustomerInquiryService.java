package com.devcamp.eztour.service.customerCenter;

import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import com.devcamp.eztour.domain.customercenter.CustomerSearchCondition;

import java.util.List;

public interface CustomerInquiryService {
    int writeCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception;

    List<CustomerInquiryDto> getCustomerSearchResultPage(CustomerSearchCondition csc) throws Exception;

    int getCustomerSearchResultCnt(CustomerSearchCondition csc) throws Exception;

}
