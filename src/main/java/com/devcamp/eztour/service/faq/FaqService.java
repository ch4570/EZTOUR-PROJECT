package com.devcamp.eztour.service.faq;

import com.devcamp.eztour.domain.faq.FaqDto;

import java.util.List;
import java.util.Map;

public interface FaqService {

    List<FaqDto> getAllFaq() throws Exception;

    int getCountFaq() throws Exception;

    List<FaqDto> getFaqPage(Map map) throws Exception;

    int writeFaq(FaqDto faqDto) throws Exception;

    FaqDto getFaq(Integer faq_no) throws Exception;

    int removeFaq(Integer faq_no) throws Exception;

    int modifyFaq(FaqDto faqDto) throws Exception;

}
