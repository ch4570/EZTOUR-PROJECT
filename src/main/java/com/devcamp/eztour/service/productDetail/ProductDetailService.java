package com.devcamp.eztour.service.productDetail;

import com.devcamp.eztour.domain.product.TrvPrdDtlDto;
import com.devcamp.eztour.domain.product.TrvPrdDtlReadDto;

import java.util.List;
import java.util.Map;

public interface ProductDetailService {
    List<TrvPrdDtlReadDto> getAllProduct() throws Exception;

    List<TrvPrdDtlDto> getAllDetailProduct(String prd_cd) throws Exception;

    List<TrvPrdDtlReadDto> getAllProductCategory(Map map) throws Exception;
}
