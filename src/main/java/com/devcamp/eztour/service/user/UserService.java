package com.devcamp.eztour.service.user;

import com.devcamp.eztour.domain.user.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface UserService {

    @Transactional(rollbackFor = Exception.class)
    int insertUsr(UserDto user) throws Exception;

    UserDto selectUsr(String usr_id) throws Exception;

    int updateHstForLogin(String usr_id) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int updateUsr(UserDto user) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int deleteUsr(String usr_id, String cmn_cd_drp) throws Exception;

    UserDto selectUserEmail(String usr_id) throws Exception;

    int checkId(String usr_id) throws Exception;

    String findId(String usr_nm, String phn) throws Exception;

    String findPwd(String usr_id, String usr_nm, String email)throws Exception;

    Map naverConnectionCheck(Map apiJson) throws Exception;

    UserDto userNaverLoginPro(String naver_id) throws Exception;

    void setNaverConnection(Map<String, Object> apiJson) throws Exception;

    Map kakaoConnectionCheck(Map paramMap) throws Exception;

    UserDto userKakaoLoginPro(String kakao_id) throws Exception;

    void setKakaoConnection(Map<String, Object> paramMap);

    List<Map> selectPaylogForMypage(String usr_id) throws Exception;

    boolean checkPwdForUsrMod(String usr_id, String pwd)throws Exception;

}
