package com.devcamp.eztour.service.faq;

import com.devcamp.eztour.dao.faq.FaqDao;
import com.devcamp.eztour.domain.faq.FaqDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FaqServiceTest {

    /*
     - Mockito 사용하기 -
    Mockito-core, Mockito-junit-jupiter(jUnit과 연동) 의존 추가

    Mock: 진짜 객체와 비슷하게 동작하지만 프로그래머가 직접 그 객체의 행동을 관리하는 객체
    Mockito: Mock 객체를 쉽게 만들고 관리하고 검증할수 있는 방법을 제시하는 프레임워크
    */

    // 1. Mock 객체 만들기
    @Mock
    FaqDao faqDao;

    @Test
    public void 질문한개() throws Exception {
    // 2. Mock의 동작 관리(stubbing)
    // @InjectMocks : @Mock이 붙은 목객체를 @InjectMocks이 붙은 객체에 주입시킬 수 있다.
        FaqService faqService = new FaqServiceImpl(faqDao);
        assertNotNull(faqService);

        FaqDto faqDto = new FaqDto("기타사항", "ㅇㅇ", "ㄴㄴ");
        faqDto.setFaq_no(1);

        // 1번 FAQ를 호출할 때, faqDto를 리턴한다
        when(faqService.getFaq(1)).thenReturn(faqDto);

        FaqDto faqDto2 = faqService.getFaq(1);
        assertEquals("ㅇㅇ", faqService.getFaq(1).getFaq_qna_cont());

        // 몇번 호출되는지 확인
//        verify(faqService, times(3)).getFaq(1);
//        verify(faqService, never()).getFaq(any());
    }

    @Test
    public void 질문여러개() throws Exception {
        FaqService faqService = new FaqServiceImpl(faqDao);
        assertNotNull(faqService);

        FaqDto faqDto = new FaqDto("기타사항", "ㅇㅇ", "ㄴㄴ");
        List<FaqDto> list = faqService.getAllFaq();
        list.add(faqDto);

        when(faqService.getAllFaq()).thenReturn(list);

        assertEquals(1, faqService.getAllFaq().size());
    }

    @Test
    public void 질문하기() throws Exception {
        FaqService faqService = new FaqServiceImpl(faqDao);
        assertNotNull(faqService);

        FaqDto faqDto = new FaqDto("기타사항", "ㅇㅇ", "ㄴㄴ");

        int cnt = faqService.writeFaq(faqDto);
        when(faqService.writeFaq(faqDto)).thenReturn(cnt);

        assertEquals(cnt, faqService.writeFaq(faqDto));
    }

    @Test
    public void 질문삭제() throws Exception {
        FaqService faqService = new FaqServiceImpl(faqDao);
        assertNotNull(faqService);

        FaqDto faqDto = new FaqDto("기타사항", "ㅇㅇ", "ㄴㄴ");
        faqDto.setFaq_no(1);
        int cnt = faqService.writeFaq(faqDto);
        assertEquals(cnt, faqService.writeFaq(faqDto));

        faqService.removeFaq(1);
        assertEquals(0, faqService.removeFaq(1));
    }

    @Test
    public void 질문수정() throws Exception {
        FaqService faqService = new FaqServiceImpl(faqDao);
        assertNotNull(faqService);

        FaqDto faqDto = new FaqDto("기타사항", "ㅇㅇ", "ㄴㄴ");
        faqDto.setFaq_no(1);

        List<FaqDto> list = new ArrayList<>();
        list.add(faqDto);

        assertEquals(1, list.size());

        faqDto.setFaq_no(list.get(0).getFaq_no());
        faqDto.setMdf_date("2022-01-01");
        list.add(faqDto);
        int cnt = faqService.modifyFaq(faqDto);
        assertEquals(cnt, faqService.modifyFaq(faqDto));
        assertEquals("2022-01-01", list.get(0).getMdf_date());
    }

}