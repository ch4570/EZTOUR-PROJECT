package com.devcamp.eztour.service.productDetail;

import com.devcamp.eztour.dao.product.ProductDetailDao;
import com.devcamp.eztour.domain.product.TrvPrdDtlDto;
import com.devcamp.eztour.domain.product.TrvPrdDtlReadDto;
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
    @Override
    public List<TrvPrdDtlDto> getAllDetailProduct(String prd_cd) throws Exception {
        return productDetailDao.selectAllDetailProduct(prd_cd);
    }

}