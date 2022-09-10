package com.devcamp.eztour.service.productDetail;

import com.devcamp.eztour.dao.product.ProductDetailDao;
import com.devcamp.eztour.domain.product.PrdDtlPageDto;
import com.devcamp.eztour.domain.product.TrvPrdDtlDto;
import com.devcamp.eztour.domain.product.TrvPrdDtlReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {

    private final ProductDetailDao productDetailDao;

    @Override
    public List<TrvPrdDtlReadDto> getAllProduct() throws Exception {
        return productDetailDao.selectAllProduct();
    }
    @Override
    public List<TrvPrdDtlDto> getAllDetailProduct(String prd_cd) throws Exception {
        return productDetailDao.selectAllDetailProduct(prd_cd);
    }

    @Override
    public List<TrvPrdDtlReadDto> getAllProductCategory(Map map) throws Exception {
        return productDetailDao.selectAllProductCategory(map);
    }

    @Override
    public List<TrvPrdDtlReadDto> getAllProductOrder(Map map) throws Exception {
        return productDetailDao.selectAllProductOrder(map);
    }

    @Override
    public List<TrvPrdDtlReadDto> getUserLike() throws Exception {
        return productDetailDao.selectUserLike();
    }

    @Override
    public PrdDtlPageDto getProductDetailPage(String prd_dtl_cd) throws Exception {
        return productDetailDao.selectProductDetailPage(prd_dtl_cd);
    }

    @Override
    public TrvPrdDtlReadDto getOneProductDetail(Map map) throws Exception {
        return productDetailDao.selectOneProduct(map);
    }

    @Override
    public List<TrvPrdDtlReadDto> getProductAttractive(String usr_id) throws Exception {
        return productDetailDao.selectProductAttractive(usr_id);
    }

    @Override
    public int getProductAttractiveCnt(String usr_id) throws Exception {
        return productDetailDao.selectProductAttractiveCnt(usr_id);
    }

    @Override
    public int removeAllProductAttractive(String usr_id) throws Exception {
        return productDetailDao.deleteAllProductAttractive(usr_id);
    }

    @Override
    public int removeProductAttractive(Map map) throws Exception {
        return productDetailDao.deleteProductAttractive(map);
    }

    @Override
    public List<TrvPrdDtlReadDto> getUserSearch(Map map) throws Exception {
        return productDetailDao.selectUserSearch(map);
    }


}