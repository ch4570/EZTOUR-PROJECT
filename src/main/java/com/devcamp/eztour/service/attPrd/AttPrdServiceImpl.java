package com.devcamp.eztour.service.attPrd;

import com.devcamp.eztour.dao.attPrd.AttPrdDao;
import com.devcamp.eztour.domain.attPrd.AttPrdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttPrdServiceImpl implements AttPrdService {
    @Autowired
    AttPrdDao attPrdDao;

    @Override
    public int count(String usr_id, String prd_cd) throws Exception {
        return attPrdDao.count(usr_id, prd_cd);
    }

    @Override
    public int checkAttPrd(AttPrdDto attPrdDto) throws Exception {
        return attPrdDao.checkAttPrd(attPrdDto);
    }

    @Override
    public int insert(AttPrdDto attPrdDto) throws Exception {
        return attPrdDao.insert(attPrdDto);
    }

    @Override
    public int updateLikeUp(AttPrdDto attPrdDto) throws Exception {
        return attPrdDao.updateLikeUp(attPrdDto);
    }

    @Override
    public int updateLikeDown(AttPrdDto attPrdDto) throws Exception {
        return attPrdDao.updateLikeDown(attPrdDto);
    }

    @Override
    public List<AttPrdDto> selectAll() throws Exception {
        return attPrdDao.selectAll();
    }

    @Override
    public List<AttPrdDto> selectPage(Integer offset, Integer pageSize) throws Exception {
        return attPrdDao.selectPage(offset, pageSize);
    }

    @Override
    public int deleteAll() throws Exception {
        return attPrdDao.deleteAll();
    }

    @Override
    public int deleteUserAll(String usr_id) throws Exception {
        return attPrdDao.deleteUserAll(usr_id);
    }

    @Override
    public int delete(Integer att_prd_no, String usr_id) throws Exception {
        return attPrdDao.delete(att_prd_no, usr_id);
    }

}
