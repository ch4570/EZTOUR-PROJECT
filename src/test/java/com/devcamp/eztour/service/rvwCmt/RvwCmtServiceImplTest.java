package com.devcamp.eztour.service.rvwCmt;

import com.devcamp.eztour.dao.rvw.RvwDao;
import com.devcamp.eztour.dao.rvwCmt.RvwCmtDao;
import com.devcamp.eztour.domain.rvw.RvwCmtDto;
import com.devcamp.eztour.domain.rvw.RvwDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class RvwCmtServiceImplTest {
    @Autowired
    RvwCmtService rvwCmtService;
    @Autowired
    RvwCmtDao rvwCmtDao;
    @Autowired
    RvwDao rvwDao;



//    @Test
//    public void remove() throws Exception {
//        rvwDao.deleteAll();
//
//        RvwDto rvwDto = new RvwDto("to9251", "a100", "좋아요", "좋아요!", "푸틴", "to9251@naver.com");
//        assertTrue(rvwDao.insert(rvwDto) == 1);
//        Integer rvw_no = rvwDao.selectAll().get(0).getRvw_no();
//        System.out.println("rvw_no = " + rvw_no);
//
//        rvwCmtDao.deleteAll(rvw_no);
//        RvwCmtDto rvwCmtDto = new RvwCmtDto(bno,0,"hi","qwer");
//
//        assertTrue(rvwDao.select(bno).getComment_cnt() == 0);
//        assertTrue(commentService.write(commentDto)==1);
//        assertTrue(rvwDao.select(bno).getComment_cnt() == 1);
//
//        Integer cno = rvwCmtDao.selectAll(bno).get(0).getCno();
//
//        // 일부러 예외를 발생시키고 Tx가 취소되는지 확인해야.
//        int rowCnt = commentService.remove(cno, bno, commentDto.getCommenter());
//        assertTrue(rowCnt==1);
//        assertTrue(rvwDao.select(bno).getComment_cnt() == 0);
//    }
//
//    @Test
//    public void write() throws  Exception {
//        rvwDao.deleteAll();
//
//        RvwCmtDto RvwCmtDto = new RvwCmtDto("hello", "hello", "asdf");
//        assertTrue(rvwDao.insert(RvwCmtDto) == 1);
//        Integer bno = rvwDao.selectAll().get(0).getBno();
//        System.out.println("bno = " + bno);
//
//        rvwCmtDao.deleteAll(bno);
//        CommentDto commentDto = new CommentDto(bno,0,"hi","qwer");
//
//        assertTrue(rvwDao.select(bno).getComment_cnt() == 0);
//        assertTrue(commentService.write(commentDto)==1);
//
//        Integer cno = rvwCmtDao.selectAll(bno).get(0).getCno();
//        assertTrue(rvwDao.select(bno).getComment_cnt() == 1);
//    }
}