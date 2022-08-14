package com.devcamp.eztour.dao.rvw;

import com.devcamp.eztour.domain.rvw.RvwDto;

import java.util.List;
import java.util.Map;

public interface RvwDao {
    int count() throws Exception;

    int deleteAll() throws Exception;

    int delete(Integer rvw_no, String rvw_nm) throws Exception;

    List<RvwDto> selectPage(Map map) throws Exception;

    List<RvwDto> selectAll() throws Exception;

    int increaseViewCnt(Integer rvw_no) throws Exception;

    int insert(RvwDto rvwDto) throws Exception;


    RvwDto select(Integer rvw_no) throws Exception;

    int update(RvwDto rvwDto) throws Exception;

    RvwDto selectUserEmail(String usr_id) throws Exception;

    List<RvwDto> selectUsernmEmailPrdnm(String usr_id) throws Exception;
}
