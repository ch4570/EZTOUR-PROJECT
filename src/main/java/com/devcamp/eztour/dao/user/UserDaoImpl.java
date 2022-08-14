package com.devcamp.eztour.dao.user;

import com.devcamp.eztour.domain.user.UserDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SqlSession session;

    private static String namespace = "com.devcamp.eztour.dao.UserMapper.";

    @Override
    public int insertUsr(UserDto user) throws Exception {
        return session.insert(namespace+"insertUsr", user);
    }

    @Override
    public int insertUsrHis(UserDto user) throws Exception {
        return session.insert(namespace+"insertUsrHis", user);
    }

    @Override
    public UserDto selectUsr(String usr_id) throws Exception {
        return session.selectOne(namespace+"selectUsr", usr_id);
    }

    @Override
    public int updateHstForLogin(String usr_id) throws Exception {
        return session.update(namespace+"updateHstForLogin", usr_id);
    }


    @Override
    public int updateUsr(UserDto user) throws Exception {
        return session.update(namespace + "updateUsr", user);
    }

    @Override
    public int updateUsrHst(UserDto user) throws Exception {
        return session.update(namespace+"updateUsrHst", user);
    }

    @Override
    public int deleteUsr(String usr_id) throws Exception {
        return session.update(namespace+"deleteUsr", usr_id);
    }

    @Override
    public int deleteUsrHst(String usr_id, String cmn_cd_drp) throws Exception {
        Map map = new HashMap<>();
        map.put("usr_id",usr_id);
        map.put("cmn_cd_drp",cmn_cd_drp);
        System.out.println(map.get("usr_id"));
        return session.update(namespace+"deleteUsrHst", map);
    }


    @Override
    public UserDto selectUserEmail(String usr_id) {
        return session.selectOne(namespace+"selectUserEmail",usr_id);
    }


}
