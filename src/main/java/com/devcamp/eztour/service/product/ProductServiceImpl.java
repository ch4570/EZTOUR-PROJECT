package com.devcamp.eztour.service.product;

import com.devcamp.eztour.dao.product.ProductDao;
import com.devcamp.eztour.domain.product.Trv_prd_dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductDao productDao;


    @Override
    public int insertProduct(Trv_prd_dto trv_prd_dto) {
        return productDao.insertProduct(trv_prd_dto);
    }
}
