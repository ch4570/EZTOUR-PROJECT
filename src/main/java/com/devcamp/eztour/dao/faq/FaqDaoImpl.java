package com.devcamp.eztour.dao.faq;

import com.devcamp.eztour.domain.faq.FaqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class FaqDaoImpl implements FaqDao {

    private final SqlSession session;

    private String namespace = "com.devcamp.eztour.dao.FaqMapper.";

    @Override
    public List<FaqDto> selectAllFaq() throws Exception {
        return session.selectList(namespace + "selectAllFaq");
    }

    @Override
    public int countFaq() throws Exception {
        return session.selectOne(namespace + "countFaq");
    }

    @Override
    public List<FaqDto> selectFaqPage(Map map) throws Exception {
        return session.selectList(namespace + "selectFaqPage", map);
    }

    @Override
    public int insertFaq(FaqDto faqDto) throws Exception {
        return session.insert(namespace + "insertFaq", faqDto);
    }

}
