package com.devcamp.eztour.service.home;

import com.devcamp.eztour.domain.home.TrvPrdDto;


import java.util.List;

public interface HomeService {
    List<TrvPrdDto> getSearch(String keyword) throws Exception;

    int getSearchResultCnt(String keyword) throws Exception;
}
