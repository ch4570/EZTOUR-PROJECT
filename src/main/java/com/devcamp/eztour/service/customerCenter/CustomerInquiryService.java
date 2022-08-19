package com.devcamp.eztour.service.customerCenter;

import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import com.devcamp.eztour.domain.customercenter.CustomerSearchCondition;

import java.util.List;
import java.util.Map;

public interface CustomerInquiryService {
    int getCountCustomerInquiry() throws Exception;
    int removeCustomerInquiry(Integer bno, String writer) throws Exception;
    List<CustomerInquiryDto> getListCustomerInquiry() throws Exception;
    CustomerInquiryDto readCustomerInquiry(Integer bno) throws Exception;
    List<CustomerInquiryDto> getPageCustomerInquiry(Map map) throws Exception;
    int modifyCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception;
    int writeCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception;



    List<CustomerInquiryDto> getCustomerSearchResultPage(CustomerSearchCondition csc) throws Exception;
    int getCustomerSearchResultCnt(CustomerSearchCondition csc) throws Exception;

}
