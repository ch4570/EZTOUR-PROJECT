package com.devcamp.eztour.dao.customercenter;

import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import lombok.Builder;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerInquiryDaoImpl implements CustomerInquiryDao {

    @Autowired
    private SqlSession session;
    private String namespace = "com.devcamp.eztour.dao.customercenterMapper.";

    @Override
    public int insertCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception {
        return session.insert(namespace+"insertCustomerInquiry", "CustomerInquiryDto");
    }

    @Override
    public int deleteAllCustomerInquiry() {
        return session.delete(namespace+"deleteAllCustomerInquiry");
    } // int delete(String statement)

    @Override
    public int countCustomerInquiry() throws Exception {
        return session.selectOne(namespace+"countCustomerInquiry");
    } // T selectOne(String statement)
}
