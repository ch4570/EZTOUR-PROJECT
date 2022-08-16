package com.devcamp.eztour.dao.reserv;

import com.devcamp.eztour.domain.reserv.TravelerInfoDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class TravelerInfoDaoImpl implements TravelerInfoDao {
    @Autowired
    SqlSession session;
    String namespace = "com.devcamp.eztour.travelerInfoMapper.";

    @Override
    public int insertTrvlrInfo(List<TravelerInfoDto> travelerInfoDto) throws Exception{
        return session.insert(namespace+"insertTrvlrInfo", travelerInfoDto);
    }

    @Override
    public List<TravelerInfoDto> selectTrvlrInfoList(String rsvt_no) throws Exception {
        return session.selectList(namespace+"selectTrvlrInfoList", rsvt_no);
    }
}
