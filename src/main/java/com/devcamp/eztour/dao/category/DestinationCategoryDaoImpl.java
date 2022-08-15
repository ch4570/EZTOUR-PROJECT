package com.devcamp.eztour.dao.category;

import com.devcamp.eztour.domain.category.DestinationCategoryDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DestinationCategoryDaoImpl implements DestinationCategoryDao {

    private final SqlSession session;
    private String namespace = "com.devcamp.eztour.dao.destinationCategoryMapper.";

    @Override
    public List<DestinationCategoryDto> selectCategory() throws Exception {
        return session.selectList(namespace + "selectCategory");
    }

}
