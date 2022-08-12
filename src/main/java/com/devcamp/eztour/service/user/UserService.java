package com.devcamp.eztour.service.user;

import com.devcamp.eztour.domain.user.UserDto;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    @Transactional(rollbackFor = Exception.class)
    int insertUsr(UserDto user) throws Exception;

    UserDto selectUsr(String usr_id) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int updateUsr(UserDto user) throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int deleteUsr(String usr_id, String cmn_cd_drp) throws Exception;

    UserDto selectUserEmail(String usr_id) throws Exception;

}
