//package com.devcamp.eztour.dao.customercenter;
//
//import com.devcamp.eztour.service.customerCenter.CustomerInquiryService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
//public class CustomerInquiryServiceTest {
//    @Autowired
//    CustomerInquiryService customerInquiryService;
//
//    @Test
//    public void write() throws  Exception {
//        customerInquiryDao.();
//
//        BoardDto boardDto = new BoardDto("hello", "hello", "asdf");
//        assertTrue(boardDao.insert(boardDto) == 1);
//        Integer bno = boardDao.selectAll().get(0).getBno();
//        System.out.println("bno = " + bno);
//
//        commentDao.deleteAll(bno);
//        CommentDto commentDto = new CommentDto(bno,0,"hi","qwer");
//
//        assertTrue(boardDao.select(bno).getComment_cnt() == 0);
//        assertTrue(commentService.write(commentDto)==1);
//
//        Integer cno = commentDao.selectAll(bno).get(0).getCno();
//        assertTrue(boardDao.select(bno).getComment_cnt() == 1);
//    }
//}
