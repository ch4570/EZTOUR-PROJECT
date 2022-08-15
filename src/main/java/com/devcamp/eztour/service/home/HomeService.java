package com.devcamp.eztour.service.home;

import com.devcamp.eztour.domain.home.TrvPrdDto;
import com.devcamp.eztour.domain.product.Trv_prd_dto;

import java.util.List;

public interface HomeService {
    List<TrvPrdDto> getSearch(String keyword) throws Exception;

    int getSearchResultCnt(String keyword) throws Exception;
}
