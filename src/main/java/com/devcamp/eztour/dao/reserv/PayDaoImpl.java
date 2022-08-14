package com.devcamp.eztour.dao.reserv;

import com.devcamp.eztour.domain.reserv.PayDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PayDaoImpl implements PayDao {

    @Autowired
    SqlSession session;

    String namespace = "com.devcamp.eztour.payMapper.";


    @Override
    public PayDto selectPay(String rsvt_no) throws Exception {
        return session.selectOne(namespace + "selectPay", rsvt_no);
    }

    @Override
    public int insertPay(PayDto payDto) throws Exception {
        return session.insert(namespace + "insertPay", payDto);
    }

    @Override
    public int deletePayAdmin() throws Exception {
        return session.delete(namespace+"deletePayAdmin");
    }

}
