package com.devcamp.eztour.dao.product;

import com.devcamp.eztour.domain.product.*;

import java.util.List;

public interface ProductDao {

    public int insertProduct(TrvPrdWriteDto trvPrdWriteDto) throws Exception;
    public int insertProductDetail(TrvPrdDtlReadDto trvprdDtlDto);
    public int insertProductPrice(TrvPrdPrcDto trvprdPrcDto);
    public int insertProductSchedule(TrvSchDto trvschDto);
    public int insertProductImg(PrdImgDto prdimgDto);
    public int insertScheduleImage(TrvSchImgDto trvschImgDto);
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
