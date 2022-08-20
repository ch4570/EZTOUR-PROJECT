package com.devcamp.eztour.service.rvw;


import com.devcamp.eztour.dao.rvw.RvwLkAdmDao;
import com.devcamp.eztour.domain.rvw.RvwLkAdmDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RvwLkAdmServiceImpl implements RvwLkAdmService {

    @Autowired
    RvwLkAdmDao rvwLkAdmDao;

    @Override
    public int checkRvwLkAdmUser(String usr_id, Integer rvw_no) {
        return rvwLkAdmDao.checkRvwLkAdmUser(usr_id, rvw_no);
    }

    @Override
    public int checkLkYn(String usr_id, Integer rvw_no) {
        return rvwLkAdmDao.checkLkYn(usr_id, rvw_no);
    }

    @Override
    public int insert(RvwLkAdmDto rvwLkAdmDto) {
        return rvwLkAdmDao.insert(rvwLkAdmDto);
    }

    @Override
    public int updateLikeUp(RvwLkAdmDto rvwLkAdmDto) {
        return rvwLkAdmDao.updateLikeUp(rvwLkAdmDto);
    }

    @Override
    public int updateLikeDown(RvwLkAdmDto rvwLkAdmDto) {
        return rvwLkAdmDao.updateLikeDown(rvwLkAdmDto);
    }
}
