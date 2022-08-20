package com.devcamp.eztour.dao.productDetail;

import com.devcamp.eztour.domain.product.TrvPrdDtlReadDto;
import com.devcamp.eztour.domain.productDetail.TrvPrdDetailDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDetailDaoImpl implements ProductDetailDao {

    private final SqlSession session;

    String namespace = "com.devcamp.eztour.dao.productDetailMapper.";

    @Override
    public List<TrvPrdDtlReadDto> selectAllProduct() throws Exception {
        return session.selectList(namespace + "selectAllProduct");
    }

}
