package com.devcamp.eztour.dao.product;

import com.devcamp.eztour.domain.product.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao{

    @Autowired
    private SqlSession session;

    private String namespace = "com.devcamp.eztour.dao.productMapper.";

    @Override
    public int insertProduct(TrvPrdWriteDto trvPrdWriteDto) {
        return session.insert(namespace+"insertProduct", trvPrdWriteDto);
    }

    @Override
    public int insertProductDetail(TrvPrdDtlDto trv_prdDtlDto) {
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
}
