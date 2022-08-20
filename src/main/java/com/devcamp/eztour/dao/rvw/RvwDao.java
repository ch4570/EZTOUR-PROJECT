package com.devcamp.eztour.dao.rvw;

import com.devcamp.eztour.domain.rvw.RvwDto;
import com.devcamp.eztour.domain.rvw.SearchCondition;

import java.util.List;
import java.util.Map;

public interface RvwDao {
    int count() throws Exception;

    int deleteAll() throws Exception;

    int delete(Integer rvw_no, String usr_id) throws Exception;

    List<RvwDto> selectPage(Map map) throws Exception;

    List<RvwDto> selectAll() throws Exception;

    int increaseViewCnt(Integer rvw_no) throws Exception;

    int insert(RvwDto rvwDto) throws Exception;


    RvwDto select(Integer rvw_no) throws Exception;

    int update(RvwDto rvwDto) throws Exception;

    RvwDto selectUsernmEmail(String usr_id) throws Exception;

    List<RvwDto> searchSelectPage(SearchCondition sc) throws Exception;

    int searchResultCnt(SearchCondition sc) throws Exception;

    int updateCommentCnt(Integer rvw_no, Integer cnt) throws Exception;

    List<RvwDto> selectPrdnm(String usr_id) throws Exception;

    String getprdCd(String prd_dtl_cd) throws Exception;

    int checkRvwUser(String usr_id, Integer rvw_no) throws Exception;
}
