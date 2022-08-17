package com.devcamp.eztour.service.productDetail;

import com.devcamp.eztour.dao.productDetail.ProductDetailDao;
import com.devcamp.eztour.domain.product.TrvPrdDtlReadDto;
import com.devcamp.eztour.domain.productDetail.TrvPrdDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {

    private final ProductDetailDao productDetailDao;

    @Override
    public List<TrvPrdDtlReadDto> getAllProduct() throws Exception {
        return productDetailDao.selectAllProduct();
    }

}