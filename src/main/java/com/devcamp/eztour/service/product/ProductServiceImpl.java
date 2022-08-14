package com.devcamp.eztour.service.product;

import com.devcamp.eztour.dao.product.ProductDao;
import com.devcamp.eztour.domain.product.*;
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

    @Override
    public int insertProductDetail(Trv_prd_dtl_dto trv_prd_dtl_dto) {
        return productDao.insertProductDetail(trv_prd_dtl_dto);
    }

    @Override
    public int insertProductPrice(Trv_prd_prc_dto trv_prd_prc_dto) {
        return productDao.insertProductPrice(trv_prd_prc_dto);
    }

    @Override
    public int insertProductSchedule(Trv_sch_dto trv_sch_dto) {
        return productDao.insertProductSchedule(trv_sch_dto);
    }

    @Override
    public int insertProductImg(Prd_img_dto prd_img_dto) {
        return productDao.insertProductImg(prd_img_dto);
    }

    @Override
    public int insertScheduleImage(Trv_sch_img_dto trv_sch_img_dto) {
        return productDao.insertScheduleImage(trv_sch_img_dto);
    }
}
