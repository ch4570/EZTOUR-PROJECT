package com.devcamp.eztour.dao.rvw;

import com.devcamp.eztour.domain.rvw.RvwDto;
import com.devcamp.eztour.domain.rvw.SearchCondition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RvwDaoImpl implements RvwDao {

    @Autowired
    SqlSession session;
    String namespace = "com.devcamp.eztour.rvwMapper.";

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace + "deleteAll");
    }

    @Override
    public int delete(Integer rvw_no, String usr_id) throws Exception {
        Map map = new HashMap();
        map.put("rvw_no", rvw_no);
        map.put("usr_id", usr_id);
        return session.delete(namespace + "delete", map);
    }

    @Override
    public List<RvwDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace + "selectPage", map);
    }

    @Override
    public List<RvwDto> selectAll() throws Exception {
        return session.selectList(namespace + "selectAll");
    }

    @Override
    public int increaseViewCnt(Integer rvw_no) throws Exception {
        return session.update(namespace + "increaseViewCnt", rvw_no);
    }


    @Override
    public int insert(RvwDto rvwDto) throws Exception {
        return session.insert(namespace + "insert", rvwDto);
    }

    @Override
    public RvwDto select(Integer rvw_no) throws Exception {
        return session.selectOne(namespace + "select", rvw_no);
    }

    @Override
    public int update(RvwDto rvwDto) throws Exception {
        return session.update(namespace + "update", rvwDto);
    }

    @Override
    public RvwDto selectUsernmEmail(String usr_id) throws Exception {
        return session.selectOne(namespace + "selectUsernmEmail", usr_id);
    }

    @Override
    public List<RvwDto> searchSelectPage(SearchCondition sc) throws Exception {
        return session.selectList(namespace + "searchSelectPage", sc);
    }

    @Override
    public int searchResultCnt(SearchCondition sc) throws Exception {
        return session.selectOne(namespace + "searchResultCnt", sc);
    }

    @Override
    public int updateCommentCnt(Integer rvw_no, Integer cnt) throws Exception {
        Map map = new HashMap();
        map.put("rvw_no", rvw_no);
        map.put("cnt", cnt);

        return session.update(namespace + "updateCommentCnt", map);
    }

    @Override
    public List<RvwDto> selectPrdnm(String usr_id) throws Exception {
        return session.selectList(namespace + "selectPrdnm", usr_id);
    }


}
