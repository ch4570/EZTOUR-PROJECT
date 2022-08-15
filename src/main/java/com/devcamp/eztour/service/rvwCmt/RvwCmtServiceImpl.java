package com.devcamp.eztour.service.rvwCmt;

import com.devcamp.eztour.dao.rvw.RvwDao;
import com.devcamp.eztour.dao.rvwCmt.RvwCmtDao;
import com.devcamp.eztour.domain.rvw.RvwCmtDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RvwCmtServiceImpl implements RvwCmtService {
    RvwCmtDao rvwCmtDao;
    RvwDao rvwDao;

    public RvwCmtServiceImpl(RvwCmtDao rvwCmtDao, RvwDao rvwDao) {
        this.rvwCmtDao = rvwCmtDao;
        this.rvwDao = rvwDao;
    }

    @Override
    public int getCount(Integer rvw_no) throws Exception {
        return rvwCmtDao.count(rvw_no);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int remove(Integer cmt_no, Integer rvw_no, String usr_nm) throws Exception {
        int rowCnt = rvwDao.updateCommentCnt(rvw_no, -1);
        System.out.println("updateCommentCnt - rowCnt = " + rowCnt);
//        throw new Exception("test");
        rowCnt = rvwCmtDao.delete(cmt_no, usr_nm);
        System.out.println("rowCnt = " + rowCnt);
        return rowCnt;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int write(RvwCmtDto rvwCmtDto) throws Exception {
        rvwDao.updateCommentCnt(rvwCmtDto.getRvw_no(), 1);
//        throw new Exception("test");
        return rvwCmtDao.insert(rvwCmtDto);
    }

    @Override
    public List<RvwCmtDto> getList(Integer rvw_no) throws Exception {
//        throw new Exception("test");
        return rvwCmtDao.selectAll(rvw_no);
    }

    @Override
    public RvwCmtDto read(Integer cmt_no) throws Exception {
        return rvwCmtDao.select(cmt_no);
    }

    @Override
    public int modify(RvwCmtDto rvwCmtDto) throws Exception {
        return rvwCmtDao.update(rvwCmtDto);
    }
}
