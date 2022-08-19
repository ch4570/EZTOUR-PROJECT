package com.devcamp.eztour.dao.user;

import com.devcamp.eztour.domain.user.UserDto;

import java.util.HashMap;
import java.util.Map;

public interface UserDao {
    int insertUsr(UserDto user) throws Exception;

    int insertUsrHis(UserDto user) throws Exception;

    UserDto selectUsr(String usr_id) throws Exception;
    int updateHstForLogin(String usr_id) throws Exception;

    int updateUsr(UserDto user) throws Exception;

    int updateUsrHst(UserDto user) throws Exception;

    int deleteUsr(String usr_id) throws Exception;

    int deleteUsrHst(String usr_id, String cmn_cd_drp) throws Exception;

    UserDto selectUserEmail(String usr_id) throws Exception;

    int checkId(String usr_id)throws Exception;

    String findId(String usr_nm, String phn)throws Exception;

    Map naverConnectionCheck(Map map)throws Exception;

    UserDto userNaverLoginPro(String naver_id)throws Exception;

    int setNaverConnection(Map<String, Object> apiJson)throws Exception;

    Map kakaoConnectionCheck(Map paramMap) throws Exception;

    UserDto userKakaoLoginPro(String kakao_id) throws Exception;


    }
