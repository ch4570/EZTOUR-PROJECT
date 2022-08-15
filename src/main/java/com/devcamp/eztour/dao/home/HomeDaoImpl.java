package com.devcamp.eztour.dao.home;

import com.devcamp.eztour.domain.home.TrvPrdDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HomeDaoImpl implements HomeDao {

    private final SqlSession session;
    private String namespace = "com.devcamp.eztour.dao.homeMapper.";


    @Override
    public List<TrvPrdDto> searchSelect(String keyword) throws Exception {
        return session.selectList(namespace + "searchSelect", keyword);

    }

    @Override
    public int searchResultCnt(String keyword) throws Exception {
        return session.selectOne(namespace + "searchResultCnt", keyword);
    }


}
