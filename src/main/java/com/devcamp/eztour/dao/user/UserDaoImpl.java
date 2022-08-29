package com.devcamp.eztour.dao.user;

import com.devcamp.eztour.domain.user.UserDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
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
    public UserDto selectUsrHst(String usr_id) throws Exception {
        return session.selectOne(namespace+"selectUsrHst", usr_id);
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
    public int updateUsrHst(UserDto user) {
        return session.update(namespace+"updateUsrHst", user);
    }

    @Override
    public int deleteUsr(String usr_id) {
        return session.update(namespace+"deleteUsr", usr_id);
    }

    @Override
    public int deleteUsrHst(String usr_id, String cmn_cd_drp) {
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

    @Override
    public int checkId(String usr_id) throws Exception {
        return session.selectOne(namespace+"checkId", usr_id);
    }

    @Override
    public String findId(String usr_nm, String phn) {
        Map map = new HashMap<>();
        map.put("usr_nm",usr_nm);
        map.put("phn",phn);
        return session.selectOne(namespace+"findId", map);
    }

    @Override
    public String findPwd(String usr_id, String usr_nm, String email) {
        Map map = new HashMap<>();
        map.put("usr_id",usr_id);
        map.put("usr_nm",usr_nm);
        map.put("email",email);
        String pwd = session.selectOne(namespace+"findPwd", map);

        System.out.println("dao에서 받은 usr_id = " + usr_id);
        System.out.println("dao에서 받은 usr_nm = " + usr_nm);
        System.out.println("dao에서 받은 email = " + email);
        System.out.println("dao에서 받은 pwd = " + pwd);
        return pwd;
    }

    @Override
    public Map naverConnectionCheck(Map map) {
        return session.selectOne(namespace+"naverConnectionCheck", map);
    }

    @Override
    public UserDto userNaverLoginPro(String naver_id) {
        return session.selectOne(namespace+"userNaverLoginPro",naver_id);
    }

    @Override
    public int setNaverConnection(Map<String, Object> apiJson) {
        return session.update(namespace+"setNaverConnection",apiJson);
    }

    @Override
    public Map kakaoConnectionCheck(Map paramMap) {
        return session.selectOne(namespace+"kakaoConnectionCheck", paramMap);
    }

    @Override
    public UserDto userKakaoLoginPro(String kakao_id) {
        return session.selectOne(namespace+"userKakaoLoginPro",kakao_id);
    }

    @Override
    public List<Map> selectPaylogForMypage(String usr_id) {
        return session.selectList(namespace+"selectPaylogForMypage", usr_id);
    }

    @Override
    public boolean checkPwdForUsrMod(String usr_id, String pwd) {
        Map map = new HashMap<>();
        map.put("usr_id", usr_id);
        map.put("pwd", pwd);
        return session.selectOne(namespace+"checkPwdForUsrMod", map);
    }

    @Override
    public int changePwd(String usr_id, String new_pwd){
        Map map = new HashMap<>();
        map.put("usr_id", usr_id);
        map.put("new_pwd", new_pwd);
        return session.update(namespace+"changePwd", map);
    }

    @Override
    public int rstRelease(String usr_id){
        return session.update(namespace+"rstRelease", usr_id);
    }

    @Override
    public int rstReleaseLog(String usr_id){
        return session.update(namespace+"rstReleaseLog", usr_id);
    }
}
