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

    @Override
    public UserDto selectUsr(String usr_id) throws Exception {
        return userDao.selectUsr(usr_id);
    }

    @Override
    public int updateHstForLogin(String usr_id) throws Exception {
        return userDao.updateHstForLogin(usr_id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateUsr(UserDto user) throws Exception {
        userDao.updateUsr(user);

        return userDao.updateUsrHst(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public int deleteUsr(String usr_id, String cmn_cd_drp) throws Exception {
        userDao.deleteUsr(usr_id);
        return userDao.deleteUsrHst(usr_id, cmn_cd_drp);
    }

    @Transactional(rollbackFor = Exception.class)
    public UserDto selectUserEmail(String usr_id) throws Exception {
        return userDao.selectUserEmail(usr_id);
    }

    @Override
    public int checkId(String usr_id) throws Exception{
        return userDao.checkId(usr_id);
    }

}
