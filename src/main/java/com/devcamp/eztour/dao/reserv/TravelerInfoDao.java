package com.devcamp.eztour.dao.reserv;

import com.devcamp.eztour.domain.reserv.TravelerInfoDto;

import java.util.List;

public interface TravelerInfoDao {
    int insertTrvlrInfo(List<TravelerInfoDto> travelerInfoDto) throws Exception;

    List<TravelerInfoDto> selectTrvlrInfoList(String rsvt_no) throws Exception;
}
