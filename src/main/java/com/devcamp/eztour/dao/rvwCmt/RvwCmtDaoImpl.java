package com.devcamp.eztour.dao.rvwCmt;

import com.devcamp.eztour.domain.rvw.RvwCmtDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RvwCmtDaoImpl implements RvwCmtDao {

    @Autowired
    SqlSession session;
    String namespace = "com.devcamp.eztour.rvwCmtMapper.";

    @Override
    public int deleteAll(Integer rvw_no) throws Exception {
        return session.delete(namespace + "deleteAll", rvw_no);
    }

    @Override
    public int count(Integer rvw_no) throws Exception {
        return session.selectOne(namespace +"count", rvw_no);
    }

    @Override
    public int delete(Integer cmt_no, String usr_nm) {
        Map map = new HashMap();
        map.put("cmt_no", cmt_no);
        map.put("usr_nm", usr_nm);
        return session.delete(namespace + "delete", map);
    }

    @Override
    public int insert(RvwCmtDto rvwCmtDto) throws Exception {
        return session.insert(namespace + "insert", rvwCmtDto);
    }

    @Override
    public List<RvwCmtDto> selectAll(Integer rvw_no) throws Exception {
        return session.selectList(namespace + "selectAll", rvw_no);
    }

    @Override
    public RvwCmtDto select(Integer cmt_no) throws Exception {
        return session.selectOne(namespace + "select", cmt_no);
    }

    @Override
    public int update(RvwCmtDto rvwCmtDto) throws Exception {
        return session.update(namespace + "update", rvwCmtDto);
    }

}
