package com.devcamp.eztour.service.home;

import com.devcamp.eztour.dao.home.HomeDao;
import com.devcamp.eztour.domain.home.TrvPrdDto;
import com.devcamp.eztour.domain.product.Trv_prd_dto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {

    private final HomeDao homeDao;

    @Override
    public List<TrvPrdDto> getSearch(String keyword) throws Exception {
        return homeDao.searchSelect(keyword);
    }

    @Override
    public int getSearchResultCnt(String keyword) throws Exception {
        return homeDao.searchResultCnt(keyword);
    }

}