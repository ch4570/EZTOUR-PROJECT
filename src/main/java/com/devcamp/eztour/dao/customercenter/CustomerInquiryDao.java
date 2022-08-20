package com.devcamp.eztour.dao.customercenter;

import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import com.devcamp.eztour.domain.customercenter.CustomerSearchCondition;

import java.util.List;
import java.util.Map;

public interface CustomerInquiryDao {
    CustomerInquiryDto selectCustomerInquiry(Integer qna_no) throws Exception;
    List<CustomerInquiryDto> selectAllCustomerInquiry() throws Exception;
    int insertCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception;
    int deleteCustomerInquiry(Integer qna_no, String usr_id) throws Exception;
    int updateCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception;
    //    int increaseViewCnt(Integer qna_no) throws Exception;
    int deleteAllCustomerInquiry() throws Exception;
    List<CustomerInquiryDto> selectCustomerPage(Map map) throws Exception;
    int countCustomerInquiry() throws Exception;

    List<CustomerInquiryDto> searchSelectCustomerPage(CustomerSearchCondition csc) throws Exception;
    int searchResultCustomerCnt(CustomerSearchCondition csc) throws Exception;
}
