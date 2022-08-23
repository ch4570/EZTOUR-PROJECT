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
    private String namespace = "com.devcamp.eztour.dao.customercenterMapper.";

    @Override
    public CustomerPropDto selectCustomerProp(Integer prop_no) throws Exception {
        return session.selectOne(namespace+"selectCustomerProp", prop_no);
    }

    @Override
    public int countCustomerProp() throws Exception {
        return session.selectOne(namespace+"countCustomerProp");
    } // T selectOne(String statement)

    @Override
    public int deleteAllCustomerProp() {
        return session.delete(namespace+"deleteAllCustomerProp");
    } // int delete(String statement)

    @Override
    public int deleteCustomerProp(Integer prop_no, String usr_id) throws Exception {
        Map map = new HashMap();
//        qna_no와 usr_id를 어떻게 해결할 것인가?
        map.put("qna_no", prop_no);
        map.put("usr_id", usr_id);
        return session.delete(namespace+"deleteCustomerProp", map);
    }

    @Override
    public int insertCustomerProp(CustomerPropDto customerPropDto) throws Exception {
        return session.insert(namespace+"insertCustomerProp", customerPropDto);
    }

    @Override
    public List<CustomerPropDto> selectCustomerPropPage(Map map) throws Exception {
        return session.selectList(namespace+"selectCustomerPropPage", map);
    }


    @Override
    public List<CustomerPropDto> selectAllCustomerProp() throws Exception {
        return session.selectList(namespace+"selectAllCustomerProp");
    }

    @Override
    public int updateCustomerProp(CustomerPropDto customerPropDto) throws Exception {
        return session.update(namespace+"updateCustomerProp", customerPropDto);
    }

// 조회수
//    @Override
//    public int increaseViewCnt(Integer qna_no) throws Exception {
//        return session.update(namespace+"increaseViewCnt", qna_no);
//    }
}
