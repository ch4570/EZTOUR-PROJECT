package com.devcamp.eztour.dao.home;

import com.devcamp.eztour.domain.home.TrvPrdDto;

import java.util.List;

public interface HomeDao {
    List<TrvPrdDto> searchSelect(String keyword) throws Exception;

    int searchResultCnt(String keyword) throws Exception;
}
