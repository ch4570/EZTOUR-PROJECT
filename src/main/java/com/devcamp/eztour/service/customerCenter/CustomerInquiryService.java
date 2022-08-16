package com.devcamp.eztour.service.customerCenter;

import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;

public interface CustomerInquiryService {
    int writeCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception;
}
