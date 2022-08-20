package com.devcamp.eztour.dao.product;

import com.devcamp.eztour.domain.product.*;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao{

    private final SqlSession session;

    private String namespace = "com.devcamp.eztour.dao.productMapper.";

    @Override
    public int insertProduct(TrvPrdWriteDto trvPrdWriteDto){
        return session.insert(namespace+"insertProduct", trvPrdWriteDto);
    }

    @Override
    public int insertProductDetail(TrvPrdDtlWriteDto trv_prdDtlDto) {
        return session.insert(namespace+"insertProductDetail", trv_prdDtlDto);
    }

    @Override
    public int insertProductPrice(TrvPrdPrcDto trv_prdPrcDto) {
        return session.insert(namespace+"insertProductPrice", trv_prdPrcDto);
    }

    @Override
    public int insertProductSchedule(TrvSchDto trv_schDto) {
        return session.insert(namespace+"insertProductSchedule", trv_schDto);
    }

    @Override
    public int insertProductImg(PrdImgDto prd_imgDto) {
        return session.insert(namespace+"insertProductImage", prd_imgDto);
    }

    @Override
    public int insertScheduleImage(TrvSchImgDto trv_schImgDto) {
        return session.insert(namespace+"insertScheduleImage", trv_schImgDto);
    }

    @Override
    public List<TrvPrdReadDto> selectProductAdmin(PageHandlerProduct pageHandlerProduct) {
        return session.selectList(namespace+"selectProductAdmin",pageHandlerProduct);
    }

    @Override
    public int selectProductAdminCnt() {
        return session.selectOne(namespace+"selectProductAdminCnt");
    }

    @Override
    public List<TrvPrdReadDto> searchSelectProductAdmin(PageHandlerProduct pageHandlerProduct) {
        return session.selectList(namespace+"searchSelectProductAdmin",pageHandlerProduct);
    }

    @Override
    public int searchSelectProductAdminCnt(PageHandlerProduct pageHandlerProduct) {
        return session.selectOne(namespace+"searchSelectProductAdminCnt",pageHandlerProduct);
    }

    @Override
    public TrvPrdReadDto selectProduct(String prd_cd) {
        return session.selectOne(namespace+"selectProduct",prd_cd);
    }

    @Override
    public int updateProduct(TrvPrdWriteDto trvPrdWriteDto) {
        return session.insert(namespace+"updateProduct",trvPrdWriteDto);
    }

    @Override
    public int deleteProduct(String prd_cd) {
        return session.delete(namespace+"deleteProduct",prd_cd);
    }

    @Override
    public List<TrvPrdDtlReadDto> selectProductAdminDetail(PageHandlerProduct pageHandlerProduct) {
        return session.selectList(namespace+"selectProductAdminDetail",pageHandlerProduct);
    }

    @Override
    public int selectProductAdminDetailCnt() {
        return session.selectOne(namespace+"selectProductAdminDetailCnt");
    }

    @Override
    public int searchSelectProductAdminDetailCnt(PageHandlerProduct pageHandlerProduct) {
        return session.selectOne(namespace+"searchSelectProductAdminDetailCnt",pageHandlerProduct);
    }

    @Override
    public List<TrvPrdDtlReadDto> searchSelectProductAdminDetail(PageHandlerProduct pageHandlerProduct) {
        return session.selectList(namespace+"searchSelectProductAdminDetail",pageHandlerProduct);
    }

    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace+"deleteAllProdcut");
    }

    @Override
    public List<TrvPrdWriteDto> selectAllProduct() throws Exception {
        return session.selectList(namespace+"selectAllProduct");
    }

    @Override
    public TrvPrdDtlReadDto selectProductDetail(String prd_dtl_cd) {
        return session.selectOne(namespace+"selectProductDetail",prd_dtl_cd);
    }

    @Override
    public int updateProductDetail(TrvPrdDtlWriteDto trvPrdDtlWriteDto) {
        return session.update(namespace+"updateProductDetail",trvPrdDtlWriteDto);
    }

    @Override
    public int deleteProductDetail(String prd_dtl_cd) {
        return session.delete(namespace+"deleteProductDetail",prd_dtl_cd);
    }

    @Override
    public List<TrvPrdReadDto> selectProductImage(PageHandlerProduct pageHandlerProduct) {
        return session.selectList(namespace+"selectProductImage",pageHandlerProduct);
    }

    @Override
    public int selectProductImageCnt() {
        return session.selectOne(namespace+"selectProductImageCnt");
    }

    @Override
    public List<TrvPrdReadDto> searchSelectProductImage(PageHandlerProduct pageHandlerProduct) {
        return session.selectList(namespace+"searchSelectProductImage",pageHandlerProduct);
    }

    @Override
    public int searchSelectProductImageCnt(PageHandlerProduct pageHandlerProduct) {
        return session.selectOne(namespace+"searchSelectProductImageCnt",pageHandlerProduct);
    }

    @Override
    public int deleteProductImage(int prd_img_no) {
        return session.delete(namespace+"deleteProductImage",prd_img_no);
    }

    @Override
    public int updateProductImage(PrdImgDto prdImgDto) throws Exception {
        return session.update(namespace+"updateProductImage",prdImgDto);
    }

    @Override
    public List<TrvSchDto> selectProductSchedule(PageHandlerProduct pageHandlerProduct) throws Exception {
        return session.selectList(namespace+"selectProductSchedule",pageHandlerProduct);
    }

    @Override
    public int selectProductScheduleCnt() throws Exception {
        return session.selectOne(namespace+"selectProductScheduleCnt");
    }

    @Override
    public TrvSchDto selectOneProductSchedule(int sch_no) throws Exception{
        return session.selectOne(namespace+"selectOneProductSchedule",sch_no);
    }

    @Override
    public List<TrvSchDto> searchSelectSchedule(PageHandlerProduct pageHandlerProduct) throws Exception {
        return session.selectList(namespace+"searchSelectSchedule",pageHandlerProduct);
    }

    @Override
    public int searchSelectScheduleCnt(PageHandlerProduct pageHandlerProduct) throws Exception {
        return session.selectOne(namespace+"searchSelectScheduleCnt",pageHandlerProduct);
    }

    @Override
    public int deleteSchedule(int sch_no) throws Exception {
        return session.delete(namespace+"deleteSchedule",sch_no);
    }

    @Override
    public int updateSchedule(TrvSchDto trvSchDto) throws Exception {
        return session.update(namespace+"updateSchedule",trvSchDto);
    }

    @Override
    public List<PrdPcrDto> selectProductPrice(PageHandlerProduct pageHandlerProduct) throws Exception {
        return session.selectList(namespace+"selectProductPrice",pageHandlerProduct);
    }

    @Override
    public int selectProductPriceCnt() throws Exception {
        return session.selectOne(namespace+"selectProductPriceCnt");
    }

    @Override
    public List<TrvPrdPrcDto> searchProductPrice(PageHandlerProduct pageHandlerProduct) throws Exception {
        return session.selectList(namespace+"searchProductPrice",pageHandlerProduct);
    }

    @Override
    public int searchProductPriceCnt(PageHandlerProduct pageHandlerProduct) throws Exception {
        return session.selectOne(namespace+"searchProductPriceCnt",pageHandlerProduct);
    }

    @Override
    public TrvPrdPrcDto selectOneProductPrice(int prd_prc_no) throws Exception {
        return session.selectOne(namespace+"selectOneProductPrice",prd_prc_no);
    }

    @Override
    public int deleteProductPrice(int prd_prc_no) throws Exception {
        return session.delete(namespace+"deleteProductPrice",prd_prc_no);
    }

    @Override
    public int updateProductPrice(TrvPrdPrcDto trvPrdPrcDto) throws Exception {
        return session.update(namespace+"updateProductPrice",trvPrdPrcDto);
    }

    @Override
    public List<TrvSchImgDto> selectAllScheduleImage(PageHandlerProduct pageHandlerProduct) throws Exception {
        return session.selectList(namespace+"selectAllScheduleImage",pageHandlerProduct);
    }

    @Override
    public int selectAllScheduleImageCnt() throws Exception {
        return session.selectOne(namespace+"selectAllScheduleImageCnt");
    }

    @Override
    public List<TrvSchImgDto> selectScheduleImage(String prd_cd) throws Exception {
        return session.selectList(namespace+"selectScheduleImage",prd_cd);
    }

    @Override
    public int deleteScheduleImage(String prd_cd) throws Exception {
        return session.delete(namespace+"deleteScheduleImage",prd_cd);
    }

    @Override
    public List<TrvSchImgDto> searchSelectScheduleImage(PageHandlerProduct pageHandlerProduct) throws Exception {
        return session.selectList(namespace+"searchSelectScheduleImage",pageHandlerProduct);
    }

    @Override
    public int searchSelectScheduleImageCnt(PageHandlerProduct pageHandlerProduct) throws Exception {
        return session.selectOne(namespace+"searchSelectScheduleImageCnt",pageHandlerProduct);
    }

}
