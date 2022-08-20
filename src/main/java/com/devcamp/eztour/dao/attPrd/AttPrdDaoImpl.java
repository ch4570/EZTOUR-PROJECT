package com.devcamp.eztour.dao.attPrd;

import com.devcamp.eztour.domain.attPrd.AttPrdDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AttPrdDaoImpl implements AttPrdDao {

    @Autowired
    SqlSession session;
    String namespace = "com.devcamp.eztour.attPrdMapper.";

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public int checkAttPrd(AttPrdDto attPrdDto) throws Exception {
        return session.selectOne(namespace + "checkAttPrd", attPrdDto);
    }

    @Override
    public int insert(AttPrdDto attPrdDto) throws Exception {
        return session.insert(namespace + "insert", attPrdDto);
    }

    @Override
    public int updateLikeUp(AttPrdDto attPrdDto) throws Exception {
        return session.update(namespace + "updateLikeUp", attPrdDto);
    }

    @Override
    public int updateLikeDown(AttPrdDto attPrdDto) throws Exception {
        return session.update(namespace + "updateLikeDown", attPrdDto);
    }

    @Override
    public List<AttPrdDto> selectAll() throws Exception {
        return session.selectList(namespace + "selectAll");
    }

    @Override
    public List<AttPrdDto> selectPage() throws Exception {
        return session.selectList(namespace + "selectPage");
    }

    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace + "deleteAll");
    }

    @Override
    public int deleteUserAll(String urs_id) throws Exception {
        return session.delete(namespace + "deleteUserAll", urs_id);
    }

    @Override
    public int delete(Integer att_prd_no, String usr_id) throws Exception {
        Map map = new HashMap();
        map.put("att_prd_no", att_prd_no);
        map.put("usr_id", usr_id);
        return session.delete(namespace + "delete", map);
    }


}
