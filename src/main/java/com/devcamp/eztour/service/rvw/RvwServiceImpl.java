package com.devcamp.eztour.service.rvw;

import com.devcamp.eztour.dao.rvw.RvwDao;
import com.devcamp.eztour.domain.rvw.RvwDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RvwServiceImpl implements RvwService {

    @Autowired
    RvwDao rvwDao;

    @Override
    public int count() throws Exception {
        return rvwDao.count();
    }

    @Transactional(rollbackFor = Exception.class)
    public int insert(RvwDto rvwDto) throws Exception {
        return rvwDao.insert(rvwDto);
    }
}
