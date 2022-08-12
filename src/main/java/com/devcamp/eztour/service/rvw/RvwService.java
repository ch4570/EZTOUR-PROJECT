package com.devcamp.eztour.service.rvw;

import com.devcamp.eztour.domain.rvw.RvwDto;
import org.springframework.transaction.annotation.Transactional;

public interface RvwService {
    @Transactional(rollbackFor = Exception.class)
    int count() throws Exception;

    @Transactional(rollbackFor = Exception.class)
    int insert(RvwDto rvwDto) throws Exception;
}
