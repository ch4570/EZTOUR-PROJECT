package com.devcamp.eztour.service.productDetail;

import com.devcamp.eztour.domain.product.PrdDtlPageDto;
import com.devcamp.eztour.domain.product.TrvPrdDtlDto;
import com.devcamp.eztour.domain.product.TrvPrdDtlReadDto;

import java.util.List;
import java.util.Map;

public interface ProductDetailService {
    List<TrvPrdDtlReadDto> getAllProduct() throws Exception;

    List<TrvPrdDtlDto> getAllDetailProduct(String prd_cd) throws Exception;

    List<TrvPrdDtlReadDto> getAllProductCategory(Map map) throws Exception;

    List<TrvPrdDtlReadDto> getAllProductOrder(Map map) throws Exception;

    List<TrvPrdDtlReadDto> getUserLike() throws Exception;
    PrdDtlPageDto getProductDetailPage(String prd_dtl_cd) throws Exception;

    TrvPrdDtlReadDto getOneProductDetail(String prd_cd) throws Exception;
    List<TrvPrdDtlReadDto> getProductAttractive(String usr_id) throws Exception;
    int getProductAttractiveCnt(String usr_id) throws Exception;
}
