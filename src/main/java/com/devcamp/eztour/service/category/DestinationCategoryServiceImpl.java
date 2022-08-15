package com.devcamp.eztour.service.category;

import com.devcamp.eztour.dao.category.DestinationCategoryDao;
import com.devcamp.eztour.domain.category.DestinationCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DestinationCategoryServiceImpl implements DestinationCategoryService {

    private final DestinationCategoryDao destinationCategoryDao;

    @Override
    public List<DestinationCategoryDto> getCategory() throws Exception {
        return destinationCategoryDao.selectCategory();
    }

}
