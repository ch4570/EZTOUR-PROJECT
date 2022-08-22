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
    public int insert(String usr_id, Integer rvw_no) {
        Map map = new HashMap();
        map.put("usr_id", usr_id);
        map.put("rvw_no", rvw_no);
        return session.insert(namespace + "insert", map);
    }

    @Override
    public int updateLikeUp(String usr_id, Integer rvw_no) {
        Map map = new HashMap();
        map.put("usr_id", usr_id);
        map.put("rvw_no", rvw_no);
        return session.update(namespace + "updateLikeUp", map);
    }

    @Override
    public int updateLikeDown(String usr_id, Integer rvw_no) {
        Map map = new HashMap();
        map.put("usr_id", usr_id);
        map.put("rvw_no", rvw_no);
        return session.update(namespace + "updateLikeDown", map);
    }
    @Override
    public RvwLkAdmDto select(String usr_id, Integer rvw_no) {
        Map map = new HashMap();
        map.put("usr_id", usr_id);
        map.put("rvw_no", rvw_no);
        return session.selectOne(namespace + "select", map);
    }
}
