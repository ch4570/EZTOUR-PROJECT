package com.devcamp.eztour.service.product;


import com.devcamp.eztour.domain.product.*;

import java.util.List;

public interface ProductService {
    public int insertProduct(TrvPrdWriteDto trvPrdWriteDto) throws Exception;
    public int insertProductDetail(TrvPrdDtlReadDto trv_prdDtlDto);
    public int insertProductPrice(TrvPrdPrcDto trv_prdPrcDto);
    public int insertProductSchedule(TrvSchDto trv_schDto);
    public int insertProductImg(PrdImgDto prd_imgDto);
    public int insertScheduleImage(TrvSchImgDto trv_schImgDto);
    public List<TrvPrdReadDto> selectProductAdmin(PageHandlerProduct pageHandlerProduct);
    public int selectProductAdminCnt();
    public List<TrvPrdReadDto> searchSelectProductAdmin(PageHandlerProduct pageHandlerProduct);
    public int searchSelectProductAdminCnt(PageHandlerProduct pageHandlerProduct);
    public TrvPrdReadDto selectProduct(String prd_cd);
    public int updateProduct(TrvPrdWriteDto trvPrdWriteDto);
    public int deleteProduct(String prd_cd);
    public List<TrvPrdDtlReadDto> selectProductAdminDetail(PageHandlerProduct pageHandlerProduct);
    public int selectProductAdminDetailCnt();
    public int searchSelectProductAdminDetailCnt(PageHandlerProduct pageHandlerProduct);
    public List<TrvPrdDtlReadDto> searchSelectProductAdminDetail(PageHandlerProduct pageHandlerProduct);
    public int deleteAll() throws Exception;
    public List<TrvPrdWriteDto> selectAllProduct() throws Exception;
    public TrvPrdDtlReadDto selectProductDetail(String prd_dtl_cd);
}
