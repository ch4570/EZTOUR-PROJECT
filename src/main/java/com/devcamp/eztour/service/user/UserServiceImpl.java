package com.devcamp.eztour.service.user;

import com.devcamp.eztour.dao.user.UserDao;
import com.devcamp.eztour.domain.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public UserDto selectUsrHst(String usr_id) throws Exception {
        return userDao.selectUsrHst(usr_id);
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

    @Override
    public String findId(String usr_nm, String phn) throws Exception {
        return userDao.findId(usr_nm, phn);
    }

    @Override
    public String findPwd(String usr_id, String usr_nm, String email) throws Exception {
        return userDao.findPwd(usr_id, usr_nm, email);
    }

    @Override
    public Map naverConnectionCheck(Map apiJson) throws Exception{

        return userDao.naverConnectionCheck(apiJson);
    }

    @Override
    public UserDto userNaverLoginPro(String naver_id) throws Exception {
        return userDao.userNaverLoginPro(naver_id);
    }

    @Override
    public int setNaverConnection(Map<String, Object> apiJson) throws Exception {
        return userDao.setNaverConnection(apiJson);
    }

    @Override
    public Map kakaoConnectionCheck(Map paramMap) throws Exception {
        return userDao.kakaoConnectionCheck(paramMap);
    }

    @Override
    public UserDto userKakaoLoginPro(String kakao_id) throws Exception{
        return userDao.userKakaoLoginPro(kakao_id);
    }

    @Override
    public void setKakaoConnection(Map paramMap) {
    }

    @Override
    public List<Map> selectPaylogForMypage(String usr_id) throws Exception {
        return userDao.selectPaylogForMypage(usr_id);
    }

    @Override
    public boolean checkPwdForUsrMod(String usr_id, String pwd) throws Exception{
        return userDao.checkPwdForUsrMod(usr_id, pwd);
    }

    @Override
    public int changePwd(String usr_id, String new_pwd) throws Exception{
        return userDao.changePwd(usr_id, new_pwd);
    }

    @Transactional(rollbackFor = Exception.class)
    public int rstRelease(String usr_id) throws Exception{
        userDao.rstRelease(usr_id);
        return userDao.rstReleaseLog(usr_id);
    }


}
