package com.devcamp.eztour.service.product;

import com.devcamp.eztour.dao.product.ProductDao;
import com.devcamp.eztour.domain.product.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductDao productDao;

    @Override
    public int insertProduct(TrvPrdWriteDto trvPrdWriteDto) throws Exception {
        return productDao.insertProduct(trvPrdWriteDto);
    }

    @Override
    public int insertProductDetail(TrvPrdDtlWriteDto trv_prdDtlDto) throws Exception {
        return productDao.insertProductDetail(trv_prdDtlDto);
    }

    @Override
    public int insertProductPrice(TrvPrdPrcDto trv_prdPrcDto) throws Exception{
        return productDao.insertProductPrice(trv_prdPrcDto);
    }

    @Override
    public int insertProductSchedule(TrvSchDto trv_schDto) throws Exception {
        return productDao.insertProductSchedule(trv_schDto);
    }

    @Override
    public int insertProductImg(PrdImgDto prd_imgDto) throws Exception{
        return productDao.insertProductImg(prd_imgDto);
    }

    @Override
    public int insertScheduleImage(TrvSchImgDto trv_schImgDto) throws Exception{
        return productDao.insertScheduleImage(trv_schImgDto);
    }

    @Override
    public List<TrvPrdReadDto> selectProductAdmin(PageHandlerProduct pageHandlerProduct) throws Exception{
        return productDao.selectProductAdmin(pageHandlerProduct);
    }

    @Override
    public int selectProductAdminCnt() throws Exception{
        return productDao.selectProductAdminCnt();
    }

    @Override
    public List<TrvPrdReadDto> searchSelectProductAdmin(PageHandlerProduct pageHandlerProduct) throws Exception{
        return productDao.searchSelectProductAdmin(pageHandlerProduct);
    }

    @Override
    public int searchSelectProductAdminCnt(PageHandlerProduct pageHandlerProduct) throws Exception{
        return productDao.searchSelectProductAdminCnt(pageHandlerProduct);
    }

    @Override
    public TrvPrdReadDto selectProduct(String prd_cd) throws Exception{
        return productDao.selectProduct(prd_cd);
    }

    @Override
    public int updateProduct(TrvPrdWriteDto trvPrdWriteDto) throws Exception{
        return productDao.updateProduct(trvPrdWriteDto);
    }

    @Override
    public int deleteProduct(String prd_cd) throws Exception{
        return productDao.deleteProduct(prd_cd);
    }

    @Override
    public List<TrvPrdDtlReadDto> selectProductAdminDetail(PageHandlerProduct pageHandlerProduct) throws Exception{
        return productDao.selectProductAdminDetail(pageHandlerProduct);
    }

    @Override
    public int selectProductAdminDetailCnt() throws Exception{
        return productDao.selectProductAdminDetailCnt();
    }

    @Override
    public int searchSelectProductAdminDetailCnt(PageHandlerProduct pageHandlerProduct) throws Exception{
        return productDao.searchSelectProductAdminDetailCnt(pageHandlerProduct);
    }

    @Override
    public List<TrvPrdDtlReadDto> searchSelectProductAdminDetail(PageHandlerProduct pageHandlerProduct) throws Exception{
        return productDao.searchSelectProductAdminDetail(pageHandlerProduct);
    }

    @Override
    public int deleteAll() throws Exception {
        return productDao.deleteAll();
    }

    @Override
    public List<TrvPrdWriteDto> selectAllProduct() throws Exception {
        return productDao.selectAllProduct();
    }

    @Override
    public TrvPrdDtlReadDto selectProductDetail(String prd_dtl_cd) throws Exception{
        return productDao.selectProductDetail(prd_dtl_cd);
    }

    @Override
    public int updateProductDetail(TrvPrdDtlWriteDto trvPrdDtlWriteDto) throws Exception{
        return productDao.updateProductDetail(trvPrdDtlWriteDto);
    }

    @Override
    public int deleteProductDetail(String prd_dtl_cd) throws Exception{
        return productDao.deleteProductDetail(prd_dtl_cd);
    }

    @Override
    public List<TrvPrdReadDto> selectProductImage(PageHandlerProduct pageHandlerProduct) throws Exception{
        return productDao.selectProductImage(pageHandlerProduct);
    }

    @Override
    public int selectProductImageCnt() throws Exception{
        return productDao.selectProductImageCnt();
    }

    @Override
    public List<TrvPrdReadDto> searchSelectProductImage(PageHandlerProduct pageHandlerProduct) throws Exception{
        return productDao.searchSelectProductImage(pageHandlerProduct);
    }

    @Override
    public int searchSelectProductImageCnt(PageHandlerProduct pageHandlerProduct) throws Exception{
        return productDao.searchSelectProductImageCnt(pageHandlerProduct);
    }

    @Override
    public int deleteProductImage(int prd_img_no) throws Exception {
        return productDao.deleteProductImage(prd_img_no);
    }

    @Override
    public int updateProductImage(PrdImgDto prdImgDto) throws Exception {
        return productDao.updateProductImage(prdImgDto);
    }

    @Override
    public List<TrvSchDto> getScheduleList(PageHandlerProduct pageHandlerProduct) throws Exception {
        return productDao.selectProductSchedule(pageHandlerProduct);
    }

    @Override
    public int getScheduleCnt() throws Exception {
        return productDao.selectProductScheduleCnt();
    }

    @Override
    public TrvSchDto getSchedule(int sch_no) throws Exception {
        return productDao.selectOneProductSchedule(sch_no);
    }

    @Override
    public List<TrvSchDto> getSearchSchedule(PageHandlerProduct pageHandlerProduct) throws Exception {
        return productDao.searchSelectSchedule(pageHandlerProduct);
    }

    @Override
    public int getSearchScheduleCnt(PageHandlerProduct pageHandlerProduct) throws Exception {
        return productDao.searchSelectScheduleCnt(pageHandlerProduct);
    }

    @Override
    public int removeSchedule(int sch_no) throws Exception {
        return productDao.deleteSchedule(sch_no);
    }

    @Override
    public int updateSchedule(TrvSchDto trvSchDto) throws Exception {
        return productDao.updateSchedule(trvSchDto);
    }
}
