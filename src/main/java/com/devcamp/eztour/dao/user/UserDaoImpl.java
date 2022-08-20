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

    @Override
    public int checkId(String usr_id) throws Exception {
        return session.selectOne(namespace+"checkId", usr_id);
    }

    @Override
    public String findId(String usr_nm, String phn) throws Exception {
        Map map = new HashMap<>();
        map.put("usr_nm",usr_nm);
        map.put("phn",phn);
        return session.selectOne(namespace+"findId", map);
    }

    @Override
    public String findPwd(String usr_id, String usr_nm, String email) throws Exception {
        System.out.println("이름 이상하게 나올거같음 ................................."+usr_nm);
        Map map = new HashMap<>();
        map.put("usr_id",usr_id);
        map.put("usr_nm",usr_nm);
        map.put("email",email);
        return session.selectOne(namespace+"findPwd", map);
    }

    @Override
    public Map naverConnectionCheck(Map map) throws Exception {
        return session.selectOne(namespace+"naverConnectionCheck", map);
    }

    @Override
    public UserDto userNaverLoginPro(String naver_id) throws Exception {
        return session.selectOne(namespace+"userNaverLoginPro",naver_id);
    }

    @Override
    public int setNaverConnection(Map<String, Object> apiJson) throws Exception {
        return session.update(namespace+"setNaverConnection",apiJson);
    }

    @Override
    public Map kakaoConnectionCheck(Map paramMap) throws Exception {
        return session.selectOne(namespace+"kakaoConnectionCheck", paramMap);
    }

    @Override
    public UserDto userKakaoLoginPro(String kakao_id) throws Exception {
        return session.selectOne(namespace+"userKakaoLoginPro",kakao_id);
    }

}
