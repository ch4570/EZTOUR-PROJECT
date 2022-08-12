package com.devcamp.eztour.dao.user;

import com.devcamp.eztour.domain.user.UserDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SqlSession session;

    private static String namespace = "com.devcamp.eztour.dao.UserMapper.";

    public int insertUsr(UserDto user) throws Exception {
        return session.insert(namespace+"insertUsr", user);
    }

    public int insertUsrHis(UserDto user) throws Exception {
        return session.insert(namespace+"insertUsrHis", user);
    }

    public UserDto selectUsr(String usr_id) throws Exception {
        return session.selectOne(namespace+"selectUsr", usr_id);
    }

    public int updateUsr(UserDto user) throws Exception {
        return session.update(namespace+"updateUsr", user);
    }

    public int updateUsrHst(UserDto user) throws Exception {
        return session.update(namespace+"updateUsrHst", user);
    }

    public int deleteUsr(UserDto user) throws Exception {
        return session.delete(namespace+"deleteUsr", user);
    }

    public int deleteUsrHst(UserDto user) throws Exception {
        return session.delete(namespace+"deleteUsrHst", user);
    }

    @Override
    public UserDto selectUserEmail(String usr_id) {
        return session.selectOne(namespace+"selectUserEmail",usr_id);
    }

}
