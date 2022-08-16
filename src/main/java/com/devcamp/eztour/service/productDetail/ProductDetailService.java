package com.devcamp.eztour.service.productDetail;

import com.devcamp.eztour.domain.productDetail.TrvPrdDetailDto;

import java.util.List;

public interface ProductDetailService {
    List<TrvPrdDetailDto> getAllProduct() throws Exception;
}
