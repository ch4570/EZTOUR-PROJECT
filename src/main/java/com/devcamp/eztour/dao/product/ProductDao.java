package com.devcamp.eztour.dao.product;

import com.devcamp.eztour.domain.product.*;

public interface ProductDao {

    public int insertProduct(Trv_prd_dto trv_prd_dto);
    public int insertProductDetail(Trv_prd_dtl_dto trv_prd_dtl_dto);
    public int insertProductPrice(Trv_prd_prc_dto trv_prd_prc_dto);
    public int insertProductSchedule(Trv_sch_dto trv_sch_dto);
    public int insertProductImg(Prd_img_dto prd_img_dto);
}
