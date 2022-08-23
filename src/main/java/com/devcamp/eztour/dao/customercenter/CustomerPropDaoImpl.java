package com.devcamp.eztour.dao.customercenter;

import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import com.devcamp.eztour.domain.customercenter.CustomerPropDto;
import com.devcamp.eztour.domain.customercenter.CustomerSearchCondition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerPropDaoImpl implements CustomerPropDao {

    @Autowired
    private SqlSession session;

    private static String namespace = "com.devcamp.eztour.dao.CustomerCenterMapper.";

    @Override
    public List<CustomerPropDto> selectAllCustomerProp() throws Exception {
        return session.selectList(namespace+"selectAllCustomerProp");
    }

    @Override
    public int insertCustomerProp(CustomerPropDto customerPropDto) throws Exception {
        return session.insert(namespace+"insertCustomerProp", customerPropDto);
    }

}
