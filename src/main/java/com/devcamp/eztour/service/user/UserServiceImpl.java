package com.devcamp.eztour.service.user;

import com.devcamp.eztour.dao.user.UserDao;
import com.devcamp.eztour.domain.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Transactional(rollbackFor = Exception.class)
    public int insertUsr(UserDto user) throws Exception {
        userDao.insertUsr(user);

        return userDao.insertUsrHis(user);
    }

    public UserDto selectUsr(String usr_id) throws Exception {
        return userDao.selectUsr(usr_id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateUsr(UserDto user) throws Exception {
        userDao.updateUsr(user);

        return userDao.updateUsrHst(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public int deleteUsr(UserDto user) throws Exception {
        userDao.deleteUsr(user);

        return userDao.deleteUsr(user);
    }

}
