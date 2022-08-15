package com.devcamp.eztour.service.productDetail;

import com.devcamp.eztour.dao.productDetail.ProductDetailDao;
import com.devcamp.eztour.domain.productDetail.TrvPrdDetailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {

    private final ProductDetailDao productDetailDao;

    @Override
    public List<TrvPrdDetailDto> getAllProduct() throws Exception {
        return productDetailDao.selectAllProduct();
    }

}
