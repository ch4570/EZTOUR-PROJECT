package com.devcamp.eztour.dao.rvw;


import com.devcamp.eztour.domain.rvw.RvwLkAdmDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RvwLkAdmDaoImpl implements RvwLkAdmDao {

    @Autowired
    SqlSession session;
    String namespace = "com.devcamp.eztour.rvwLkAdmMapper.";

    @Override
    public int checkRvwLkAdmUser(String usr_id, Integer rvw_no) {
        Map map = new HashMap();
        map.put("usr_id", usr_id);
        map.put("rvw_no", rvw_no);
        return session.selectOne(namespace + "checkRvwLkAdmUser", map);
    }

    @Override
    public int checkLkYn(String usr_id, Integer rvw_no) {
        Map map = new HashMap();
        map.put("usr_id", usr_id);
        map.put("rvw_no", rvw_no);
        return session.selectOne(namespace + "checkLkYn", map);
    }

    @Override
    public int insert(RvwLkAdmDto rvwLkAdmDto) {
        return session.insert(namespace + "insert", rvwLkAdmDto);
    }

    @Override
    public int updateLikeUp(RvwLkAdmDto rvwLkAdmDto) {
        return session.update(namespace + "updateLikeUp", rvwLkAdmDto);
    }

    @Override
    public int updateLikeDown(RvwLkAdmDto rvwLkAdmDto) {
        return session.update(namespace + "updateLikeDown", rvwLkAdmDto);
    }
}
