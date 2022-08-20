package com.devcamp.eztour.service.product;

import com.devcamp.eztour.domain.product.*;
import java.util.List;
import java.util.Map;

public interface ProductService {
    public int insertProduct(TrvPrdWriteDto trvPrdWriteDto) throws Exception;
    public int insertProductDetail(TrvPrdDtlWriteDto trv_prdDtlDto) throws Exception;
    public int insertProductPrice(TrvPrdPrcDto trv_prdPrcDto) throws Exception;
    public int insertProductSchedule(TrvSchDto trv_schDto) throws Exception;
    public int insertProductImg(PrdImgDto prd_imgDto) throws Exception;
    public int insertScheduleImage(TrvSchImgDto trv_schImgDto) throws Exception;
    public List<TrvPrdReadDto> selectProductAdmin(PageHandlerProduct pageHandlerProduct) throws Exception;
    public int selectProductAdminCnt() throws Exception;
    public List<TrvPrdReadDto> searchSelectProductAdmin(PageHandlerProduct pageHandlerProduct) throws Exception;
    public int searchSelectProductAdminCnt(PageHandlerProduct pageHandlerProduct) throws Exception;
    public TrvPrdReadDto selectProduct(String prd_cd) throws Exception;
    public int updateProduct(TrvPrdWriteDto trvPrdWriteDto) throws Exception;
    public int deleteProduct(String prd_cd) throws Exception;
    public List<TrvPrdDtlReadDto> selectProductAdminDetail(PageHandlerProduct pageHandlerProduct) throws Exception;
    public int selectProductAdminDetailCnt() throws Exception;
    public int searchSelectProductAdminDetailCnt(PageHandlerProduct pageHandlerProduct) throws Exception;
    public List<TrvPrdDtlReadDto> searchSelectProductAdminDetail(PageHandlerProduct pageHandlerProduct) throws Exception;
    public int deleteAll() throws Exception;
    public List<TrvPrdWriteDto> selectAllProduct() throws Exception;
    public TrvPrdDtlReadDto selectProductDetail(String prd_dtl_cd) throws Exception;
    public int updateProductDetail(TrvPrdDtlWriteDto trvPrdDtlWriteDto) throws Exception;
    public int deleteProductDetail(String prd_dtl_cd) throws Exception;
    public List<TrvPrdReadDto> selectProductImage(PageHandlerProduct pageHandlerProduct) throws Exception;
    public int selectProductImageCnt() throws Exception;
    public List<TrvPrdReadDto> searchSelectProductImage(PageHandlerProduct pageHandlerProduct) throws Exception;
    public int searchSelectProductImageCnt(PageHandlerProduct pageHandlerProduct) throws Exception;
    public int deleteProductImage(int prd_img_no) throws Exception;
    public int updateProductImage(PrdImgDto prdImgDto) throws Exception;
    public List<TrvSchDto> getScheduleList(PageHandlerProduct pageHandlerProduct) throws Exception;
    public int getScheduleCnt() throws Exception;
    public TrvSchDto getSchedule(int sch_no) throws Exception;
    public List<TrvSchDto> getSearchSchedule(PageHandlerProduct pageHandlerProduct) throws Exception;
    public int getSearchScheduleCnt(PageHandlerProduct pageHandlerProduct) throws Exception;
    public int removeSchedule(int sch_no) throws Exception;
    public int updateSchedule(TrvSchDto trvSchDto) throws Exception;
    public List<PrdPcrDto> getProductPrice(PageHandlerProduct pageHandlerProduct) throws Exception;
    public int getProductPriceCnt() throws Exception;
    public List<TrvPrdPrcDto> getSearchProductPrice(PageHandlerProduct pageHandlerProduct) throws Exception;
    public int getSearchProductPriceCnt(PageHandlerProduct pageHandlerProduct) throws Exception;
    public TrvPrdPrcDto getOneSearchProductPrice(int prd_prc_no) throws Exception;
    public int removeProductPrice(int prd_prc_no) throws Exception;
    public int modifyProductPrice(TrvPrdPrcDto trvPrdPrcDto) throws Exception;
    public List<TrvSchImgDto> getAllScheduleImage(PageHandlerProduct pageHandlerProduct) throws Exception;
    public int getAllScheduleImageCnt() throws Exception;
    public List<TrvSchImgDto> getScheduleImage(String prd_cd) throws Exception;
    public int removeScheduleImage(String prd_cd) throws Exception;
    public List<TrvSchImgDto> getSearchScheduleImage(PageHandlerProduct pageHandlerProduct) throws Exception;
    public int getSearchScheduleImageCnt(PageHandlerProduct pageHandlerProduct) throws Exception;
    public TrvPrdReadDto getProductRecognize(String prd_cd) throws Exception;
    public int modifyActivateStatus(Map map) throws Exception;
}
