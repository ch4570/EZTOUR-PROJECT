package com.devcamp.eztour.dao.rvw;

import com.devcamp.eztour.domain.rvw.RvwLkAdmDto;

public interface RvwLkAdmDao {
    int checkRvwLkAdmUser(String usr_id, Integer rvw_no);

    int checkLkYn(String usr_id, Integer rvw_no);

    int insert(String usr_id, Integer rvw_no);

    int updateLikeUp(String usr_id, Integer rvw_no);

    int updateLikeDown(String usr_id, Integer rvw_no);

    RvwLkAdmDto select(String usr_id, Integer rvw_no);
}
