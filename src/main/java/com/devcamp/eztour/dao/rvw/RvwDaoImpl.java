package com.devcamp.eztour.dao.rvw;

import com.devcamp.eztour.domain.rvw.RvwDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RvwDaoImpl implements RvwDao {

    @Autowired
    SqlSession session;
    String namespace = "com.fastcampus.ch4.dao.RvwMapper.";

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count"); // 게시물 총 수량
    }

    @Override
    public int insert(RvwDto rvwDto) throws Exception {
        return session.insert(namespace + "insert", rvwDto);
    }


}
