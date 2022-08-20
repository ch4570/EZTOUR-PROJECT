package com.devcamp.eztour.service.rvw;

import com.devcamp.eztour.domain.rvw.RvwLkAdmDto;

public interface RvwLkAdmService {
    int checkRvwLkAdmUser(String usr_id, Integer rvw_no);

    int checkLkYn(String usr_id, Integer rvw_no);

    int insert(RvwLkAdmDto rvwLkAdmDto);

    int updateLikeUp(RvwLkAdmDto rvwLkAdmDto);

    int updateLikeDown(RvwLkAdmDto rvwLkAdmDto);
}
