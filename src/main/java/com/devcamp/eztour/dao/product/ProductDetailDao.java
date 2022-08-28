package com.devcamp.eztour.dao.product;

import com.devcamp.eztour.domain.product.PrdDtlPageDto;
import com.devcamp.eztour.domain.product.TrvPrdDtlDto;
import com.devcamp.eztour.domain.product.TrvPrdDtlReadDto;

import java.util.List;
import java.util.Map;

public interface ProductDetailDao {
    List<TrvPrdDtlReadDto> selectAllProduct() throws Exception;

    List<TrvPrdDtlDto> selectAllDetailProduct(String prd_cd) throws Exception;

    List<TrvPrdDtlReadDto> selectAllProductCategory(Map map) throws Exception;

    List<TrvPrdDtlReadDto> selectAllProductOrder(Map map) throws Exception;
    List<TrvPrdDtlReadDto> selectUserLike() throws Exception;
    PrdDtlPageDto selectProductDetailPage(String prd_dtl_cd) throws Exception;
    TrvPrdDtlReadDto selectOneProduct(String prd_cd) throws Exception;
    List<TrvPrdDtlReadDto> selectProductAttractive(String usr_id) throws Exception;
    int selectProductAttractiveCnt(String usr_id) throws Exception;
    int deleteAllProductAttractive(String usr_id) throws Exception;
    int deleteProductAttractive(Map map) throws Exception;

}
