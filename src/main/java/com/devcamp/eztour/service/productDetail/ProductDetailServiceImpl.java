package com.devcamp.eztour.service.productDetail;

import com.devcamp.eztour.dao.product.ProductDetailDao;
import com.devcamp.eztour.domain.product.TrvPrdDtlDto;
import com.devcamp.eztour.domain.product.TrvPrdDtlReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<TrvPrdDtlReadDto> getAllProductCategory(Map map) throws Exception {
        return productDetailDao.selectAllProductCategory(map);
    }

    @Override
    public List<TrvPrdDtlReadDto> getAllProductOrder(Map map) throws Exception {
        return productDetailDao.selectAllProductOrder(map);
    }

}