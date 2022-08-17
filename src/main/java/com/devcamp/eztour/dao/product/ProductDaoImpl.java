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
    public int insertProductDetail(TrvPrdDtlReadDto trv_prdDtlDto) {
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

}
