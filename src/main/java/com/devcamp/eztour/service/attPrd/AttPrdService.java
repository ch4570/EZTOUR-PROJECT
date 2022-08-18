package com.devcamp.eztour.service.attPrd;

import com.devcamp.eztour.dao.attPrd.AttPrdDao;
import com.devcamp.eztour.domain.attPrd.AttPrdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttPrdService {
    @Autowired
    AttPrdDao attPrdDao;

    public int count() throws Exception {
        return attPrdDao.count();
    }

    public int checkAttPrd(AttPrdDto attPrdDto) throws Exception {
        return attPrdDao.checkAttPrd(attPrdDto);
    }

    public int insert(AttPrdDto attPrdDto) throws Exception {
        return attPrdDao.insert(attPrdDto);
    }

    public int updateLikeUp(AttPrdDto attPrdDto) throws Exception {
        return attPrdDao.updateLikeUp(attPrdDto);
    }

    public int updateLikeDown(AttPrdDto attPrdDto) throws Exception {
        return attPrdDao.updateLikeDown(attPrdDto);
    }

    public List<AttPrdDto> selectAll() throws Exception {
        return attPrdDao.selectAll();
    }

    public List<AttPrdDto> selectPage() throws Exception {
        return attPrdDao.selectPage();
    }

    public int deleteAll() throws Exception {
        return attPrdDao.deleteAll();
    }

    public int deleteUserAll(String usr_id) throws Exception {
        return attPrdDao.deleteUserAll(usr_id);
    }

    public int delete(Integer att_prd_no, String usr_id) throws Exception {
        return attPrdDao.delete(att_prd_no, usr_id);
    }

}
