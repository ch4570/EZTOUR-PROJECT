package com.devcamp.eztour.dao.customercenter;

import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;

public interface CustomerInquiryDao {
    int insertCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception;
    int deleteAllCustomerInquiry() throws Exception;

    public int countCustomerInquiry() throws Exception;
}
