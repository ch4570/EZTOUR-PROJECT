package com.devcamp.eztour.dao.category;

import com.devcamp.eztour.domain.category.DestinationCategoryDto;

import java.util.List;

public interface DestinationCategoryDao {
    List<DestinationCategoryDto> selectCategory() throws Exception;
}
