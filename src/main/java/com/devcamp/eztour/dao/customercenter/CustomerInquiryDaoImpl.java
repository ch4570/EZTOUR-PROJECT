package com.devcamp.eztour.dao.customercenter;

import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import com.devcamp.eztour.domain.customercenter.CustomerSearchCondition;
import lombok.Builder;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<CustomerInquiryDto> searchSelectCustomerPage(CustomerSearchCondition csc) throws Exception {
        return session.selectList(namespace+"searchSelectCustomerPage", csc);
    }

    @Override
    public int searchResultCustomerCnt(CustomerSearchCondition csc) throws Exception {
        return session.selectOne(namespace+"searchResultCustomerCnt", csc);
    }
}
