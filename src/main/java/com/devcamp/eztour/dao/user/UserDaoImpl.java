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

    public int insertUser(UserDto user) throws Exception {
        return session.insert(namespace+"insertUser", user);
    }

    public int insertUserHistory(UserDto user) throws Exception {
        return session.insert(namespace+"insertUserHistory", user);
    }

}
