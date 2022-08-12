package com.devcamp.eztour.dao.product;

import com.devcamp.eztour.domain.product.*;
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

    @Override
    public int insertProductDetail(Trv_prd_dtl_dto trv_prd_dtl_dto) {
        return session.insert(namespace+"insertProductDetail",trv_prd_dtl_dto);
    }

    @Override
    public int insertProductPrice(Trv_prd_prc_dto trv_prd_prc_dto) {
        return session.insert(namespace+"insertProductPrice",trv_prd_prc_dto);
    }

    @Override
    public int insertProductSchedule(Trv_sch_dto trv_sch_dto) {
        return session.insert(namespace+"insertProductSchedule",trv_sch_dto);
    }

    @Override
    public int insertProductImg(Prd_img_dto prd_img_dto) {
        return session.insert(namespace+"insertProductImage",prd_img_dto);
    }
}
