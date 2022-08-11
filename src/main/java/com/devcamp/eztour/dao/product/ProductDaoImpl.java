package com.devcamp.eztour.dao.product;

import com.devcamp.eztour.domain.product.Trv_prd_dto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao{

    @Autowired
    private SqlSession session;

    private String namespace = "com.devcamp.eztour.dao.productMapper.";

    @Override
    public int insertProduct(Trv_prd_dto trv_prd_dto) {
        return session.insert(namespace+"insertProduct",trv_prd_dto);
    }
}
