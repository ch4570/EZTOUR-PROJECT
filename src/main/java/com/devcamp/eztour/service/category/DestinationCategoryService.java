package com.devcamp.eztour.service.category;

import com.devcamp.eztour.domain.category.DestinationCategoryDto;

import java.util.List;

public interface DestinationCategoryService {

    List<DestinationCategoryDto> getCategory() throws Exception;

}
