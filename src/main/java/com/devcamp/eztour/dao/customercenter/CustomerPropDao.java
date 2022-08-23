package com.devcamp.eztour.dao.customercenter;

import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import com.devcamp.eztour.domain.customercenter.CustomerPropDto;
import com.devcamp.eztour.domain.customercenter.CustomerSearchCondition;

import java.util.List;
import java.util.Map;

public interface CustomerPropDao {

    List<CustomerPropDto> selectAllCustomerProp() throws Exception;

    int insertCustomerProp(CustomerPropDto customerPropDto) throws Exception;
}
