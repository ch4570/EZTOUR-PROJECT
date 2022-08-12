package com.devcamp.eztour.service.faq;

import com.devcamp.eztour.dao.faq.FaqDao;
import com.devcamp.eztour.domain.faq.FaqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {

    private final FaqDao faqDao;

    @Override
    public List<FaqDto> getAllFaq() throws Exception {
        return faqDao.selectAllFaq();
    }

    @Override
    public int getCountFaq() throws Exception {
        return faqDao.countFaq();
    }

    @Override
    public List<FaqDto> getFaqPage(Map map) throws Exception {
        return faqDao.selectFaqPage(map);
    }

    @Override
    public int writeFaq(FaqDto faqDto) throws Exception {
        return faqDao.insertFaq(faqDto);
    }

}
