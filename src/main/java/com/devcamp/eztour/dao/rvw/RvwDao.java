package com.devcamp.eztour.dao.rvw;

import com.devcamp.eztour.domain.rvw.RvwDto;

public interface RvwDao {
    int count() throws Exception;

    int insert(RvwDto rvwDto) throws Exception;
}
