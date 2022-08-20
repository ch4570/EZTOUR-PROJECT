package com.devcamp.eztour.dao.rvw;

import com.devcamp.eztour.domain.rvw.RvwLkAdmDto;

public interface RvwLkAdmDao {
    int checkRvwLkAdmUser(String usr_id, Integer rvw_no);

    int checkLkYn(String usr_id, Integer rvw_no);

    int insert(RvwLkAdmDto rvwLkAdmDto);

    int updateLikeUp(RvwLkAdmDto rvwLkAdmDto);

    int updateLikeDown(RvwLkAdmDto rvwLkAdmDto);
}
