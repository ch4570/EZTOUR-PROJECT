package com.devcamp.eztour.dao;

public interface UserDao {
    int insertUser(User user) throws Exception;

    int insertUserHistory(User user) throws Exception;
}
