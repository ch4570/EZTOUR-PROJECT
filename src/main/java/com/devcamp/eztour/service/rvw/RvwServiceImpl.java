package com.devcamp.eztour.service.rvw;

import com.devcamp.eztour.dao.rvw.RvwDao;
import com.devcamp.eztour.domain.rvw.RvwDto;
import com.devcamp.eztour.domain.rvw.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class RvwServiceImpl implements RvwService {

    @Autowired
    RvwDao rvwDao;

    @Transactional(rollbackFor = Exception.class)
    public int getCount() throws Exception {
        return rvwDao.count();
    }

    @Transactional(rollbackFor = Exception.class)
    public int remove(Integer rvw_no, String usr_id) throws Exception {
        return rvwDao.delete(rvw_no, usr_id);
    }

    @Transactional(rollbackFor = Exception.class)
    public int write(RvwDto rvwDto) throws Exception {
        return rvwDao.insert(rvwDto);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<RvwDto> getList() throws Exception {
        return rvwDao.selectAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public RvwDto read(Integer rvw_no) throws Exception {
        rvwDao.increaseViewCnt(rvw_no);
        RvwDto rvwDto = rvwDao.select(rvw_no);
        return rvwDto;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<RvwDto> getPage(Map map) throws Exception {
        return rvwDao.selectPage(map);
    }

    @Transactional(rollbackFor = Exception.class)
    public int modify(RvwDto rvwDto) throws Exception {
//        throw new Exception("test");
        return rvwDao.update(rvwDto);
    }


    @Transactional(rollbackFor = Exception.class)
    public int insert(RvwDto rvwDto) throws Exception {
        return rvwDao.insert(rvwDto);
    }

    @Override
    public List<RvwDto> getSearchResultPage(SearchCondition sc) throws Exception {
        return rvwDao.searchSelectPage(sc);
    }

    @Override
    public int getSearchResultCnt(SearchCondition sc) throws Exception {
        return rvwDao.searchResultCnt(sc);
    }

    @Override
    public RvwDto selectUsernmEmail(String usr_id) throws Exception {
        return rvwDao.selectUsernmEmail(usr_id);
    }

    @Override
    public List<RvwDto> selectPrdnm(String usr_id) throws Exception {
        return rvwDao.selectPrdnm(usr_id);
    }

    @Override
    public String getprdCd(String prd_dtl_cd) throws Exception {
        return rvwDao.getprdCd(prd_dtl_cd);
    }

    @Override
    public int checkRvwUser(String usr_id, Integer rvw_no) throws Exception {
        return rvwDao.checkRvwUser(usr_id, rvw_no);
    }

    @Override
    public int increaseLikeCnt(Integer rvw_no) throws Exception {
        return rvwDao.increaseLikeCnt(rvw_no);
    }

    @Override
    public int decreaseLikeCnt(Integer rvw_no) throws Exception {
        return rvwDao.decreaseLikeCnt(rvw_no);
    }

}
