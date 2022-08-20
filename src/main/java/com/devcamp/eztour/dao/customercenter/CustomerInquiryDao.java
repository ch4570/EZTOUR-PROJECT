//package com.devcamp.eztour.dao.customercenter;
//
//import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
//import com.devcamp.eztour.domain.customercenter.CustomerSearchCondition;
//
//import java.util.List;
//import java.util.Map;
//
//public interface CustomerInquiryDao {
//    @Override
//    CustomerInquiryDao(Integer qna_no, String usr_id) throws Exception;
//
//    CustomerInquiryDto countCustomerInquiry(Integer qna_no) throws Exception;
//    int insertCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception;
//    int deleteAllCustomerInquiry() throws Exception;
//
//    CustomerInquiryDto selectCustomerInquiry(Integer qna_no) throws Exception;
//
//    int countCustomerInquiry() throws Exception;
//
//    List<CustomerInquiryDto> selectCustomerPage(Map map) throws Exception;
//
//    List<CustomerInquiryDto> searchSelectCustomerPage(CustomerSearchCondition csc) throws Exception;
//    //    "count" 이름 어떻게 할 것인가??
//    int searchResultCustomerCnt(CustomerSearchCondition csc) throws Exception;
//
//    int deleteCustomerInquiry(Integer qna_no, String usr_id) throws Exception;
//
//    List<CustomerInquiryDto> selectAllCustomerInquiry();
//
//    int updateCustomerInquiry(CustomerInquiryDto customerInquiryDto);
//}
