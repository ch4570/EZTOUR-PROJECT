package com.devcamp.eztour.dao.customercenter;

import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import com.devcamp.eztour.domain.customercenter.CustomerPropDto;
import com.devcamp.eztour.domain.customercenter.CustomerSearchCondition;

import java.util.List;
import java.util.Map;

public interface CustomerPropDao {
    CustomerPropDto selectCustomerProp(Integer prop_no) throws Exception;
    List<CustomerPropDto> selectAllCustomerProp() throws Exception;
    int insertCustomerProp(CustomerPropDto customerPropDto) throws Exception;
    int deleteCustomerProp(Integer prop_no, String usr_id) throws Exception;
    int updateCustomerProp(CustomerPropDto customerPropDto) throws Exception;
    //    int increaseViewCnt(Integer qna_no) throws Exception;
    int deleteAllCustomerProp() throws Exception;
    List<CustomerPropDto> selectCustomerPropPage(Map map) throws Exception;
    int countCustomerProp() throws Exception;

}
