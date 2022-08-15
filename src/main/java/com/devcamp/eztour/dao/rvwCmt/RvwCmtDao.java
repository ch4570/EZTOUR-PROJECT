package com.devcamp.eztour.dao.rvwCmt;

import com.devcamp.eztour.domain.rvw.RvwCmtDto;

import java.util.List;

public interface RvwCmtDao {
    int deleteAll(Integer rvw_no) throws Exception;

    int count(Integer rvw_no) throws Exception;

    int delete(Integer cmt_no, String usr_nm);

    int insert(RvwCmtDto rvwCmtDto) throws Exception;

    List<RvwCmtDto> selectAll(Integer rvw_no) throws Exception;

    RvwCmtDto select(Integer cmt_no) throws Exception;

    int update(RvwCmtDto rvwCmtDto) throws Exception;
}
