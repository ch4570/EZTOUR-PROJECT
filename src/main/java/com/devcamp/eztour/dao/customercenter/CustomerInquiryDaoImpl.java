package com.devcamp.eztour.dao.customercenter;

import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import com.devcamp.eztour.domain.customercenter.CustomerSearchCondition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerInquiryDaoImpl implements CustomerInquiryDao {

    @Autowired
    private SqlSession session;
    private String namespace = "com.devcamp.eztour.dao.customercenterMapper.";

    @Override
    public CustomerInquiryDto selectCustomerInquiry(Integer qna_no) throws Exception {
        return session.selectOne(namespace+"selectCustomerInquiry", qna_no);
    }

    @Override
    public int countCustomerInquiry() throws Exception {
        return session.selectOne(namespace+"countCustomerInquiry");
    } // T selectOne(String statement)

    @Override
    public int deleteAllCustomerInquiry() {
        return session.delete(namespace+"deleteAllCustomerInquiry");
    } // int delete(String statement)

    @Override
    public int deleteCustomerInquiry(Integer qna_no, String usr_id) throws Exception {
        Map map = new HashMap();
//        qna_no와 usr_id를 어떻게 해결할 것인가?
        map.put("qna_no", qna_no);
        map.put("usr_id", usr_id);
        return session.delete(namespace+"deleteCustomerInquiry", map);
    }

    @Override
    public int insertCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception {
        return session.insert(namespace+"insertCustomerInquiry", customerInquiryDto);
    }

    @Override
    public List<CustomerInquiryDto> selectCustomerPage(Map map) throws Exception {
        return session.selectList(namespace+"selectCustomerPage", map);
    }

    @Override
    public List<CustomerInquiryDto> searchSelectCustomerPage(CustomerSearchCondition csc) throws Exception {
        return session.selectList(namespace+"searchSelectCustomerPage", csc);
    }
    @Override
    public int searchResultCustomerCnt(CustomerSearchCondition csc) throws Exception {
        return session.selectOne(namespace+"searchResultCustomerCnt", csc);
    }

    @Override
    public List<CustomerInquiryDto> selectAllCustomerInquiry() throws Exception {
        return session.selectList(namespace+"selectAllCustomerInquiry");
    }

    @Override
    public int updateCustomerInquiry(CustomerInquiryDto customerInquiryDto) throws Exception {
        return session.update(namespace+"updateCustomerInquiry", customerInquiryDto);
    }

// 조회수
//    @Override
//    public int increaseViewCnt(Integer qna_no) throws Exception {
//        return session.update(namespace+"increaseViewCnt", qna_no);
//    }
}
