package com.devcamp.eztour.service.rvwCmt;

import com.devcamp.eztour.domain.rvw.RvwCmtDto;

import java.util.List;

public interface RvwCmtService {
    int getCount(Integer rvw_no) throws Exception;

    int remove(Integer cmt_no, Integer rvw_no, String usr_nm) throws Exception;

    int write(RvwCmtDto rvwCmtDto) throws Exception;

    List<RvwCmtDto> getList(Integer rvw_no) throws Exception;

    RvwCmtDto read(Integer cmt_no) throws Exception;

    int modify(RvwCmtDto rvwCmtDto) throws Exception;
}
