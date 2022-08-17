package com.devcamp.eztour.dao.productDetail;

import com.devcamp.eztour.domain.product.TrvPrdDtlReadDto;
import com.devcamp.eztour.domain.productDetail.TrvPrdDetailDto;

import java.util.List;

public interface ProductDetailDao {
    List<TrvPrdDtlReadDto> selectAllProduct() throws Exception;
}
