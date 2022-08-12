package com.devcamp.eztour.dao.user;

import com.devcamp.eztour.domain.user.UserDto;

public interface UserDao {
    int insertUsr(UserDto user) throws Exception;

    int insertUsrHis(UserDto user) throws Exception;

    UserDto selectUsr(String usr_id) throws Exception;

    int updateUsr(UserDto user) throws Exception;

    int updateUsrHst(UserDto user) throws Exception;

    int deleteUsr(String usr_id, String cmn_cd_drp) throws Exception;

}
