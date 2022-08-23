package com.devcamp.eztour.dao.customercenter;

import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import com.devcamp.eztour.domain.customercenter.CustomerPropDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerInquiryDaoImpl implements CustomerInquiryDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "com.devcamp.eztour.dao.CustomerCenterMapper.";

    @Override
    public List<CustomerInquiryDto> selectAllCustomerInquiry() throws Exception {
        return session.selectList(namespace+"selectAllCustomerInquiry");
    }

    @Override
    public int insertCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception {
        return session.insert(namespace+"insertCustomerInquiry", customerInquiryDto);
    }
}

