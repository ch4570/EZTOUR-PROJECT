package com.devcamp.eztour.dao.product;

import com.devcamp.eztour.domain.product.TrvPrdDtlDto;
import com.devcamp.eztour.domain.product.TrvPrdDtlReadDto;

import java.util.List;
import java.util.Map;

public interface ProductDetailDao {
    List<TrvPrdDtlReadDto> selectAllProduct() throws Exception;

    List<TrvPrdDtlDto> selectAllDetailProduct(String prd_cd) throws Exception;

    List<TrvPrdDtlReadDto> selectAllProductCategory(Map map) throws Exception;

    List<TrvPrdDtlReadDto> selectAllProductOrder(Map map) throws Exception;

}
