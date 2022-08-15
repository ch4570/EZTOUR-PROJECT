package com.devcamp.eztour.dao.rvwCmt;

import com.devcamp.eztour.domain.rvw.RvwCmtDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class RvwCmtDaoImplTest {

    @Autowired
    private RvwCmtDao rvwCmtDao;

    @Test
    public void count() throws Exception {
        rvwCmtDao.deleteAll(1);
        assertTrue(rvwCmtDao.count(1)==0);
    }

    @Test
    public void delete() throws Exception {
        rvwCmtDao.deleteAll(1);
        RvwCmtDto rvwCmtDto = new RvwCmtDto(1, "to9251", 0, "comment", "asdf");
        assertTrue(rvwCmtDao.insert(rvwCmtDto)==1);
        assertTrue(rvwCmtDao.count(1)==1);
    }

    @Test
    public void insert() throws Exception {
        rvwCmtDao.deleteAll(1);
        RvwCmtDto rvwCmtDto = new RvwCmtDto(1, "to9251", 0, "comment", "asdf");
        assertTrue(rvwCmtDao.insert(rvwCmtDto)==1);
        assertTrue(rvwCmtDao.count(1)==1);

        rvwCmtDto = new RvwCmtDto(1, "to9251", 0, "comment", "asdf");
        assertTrue(rvwCmtDao.insert(rvwCmtDto)==1);
        assertTrue(rvwCmtDao.count(1)==2);
    }

    @Test
    public void selectAll() throws Exception {
        rvwCmtDao.deleteAll(1);
        RvwCmtDto rvwCmtDto = new RvwCmtDto(1, "to9251", 0, "comment", "asdf");
        assertTrue(rvwCmtDao.insert(rvwCmtDto)==1);
        assertTrue(rvwCmtDao.count(1)==1);

        List<RvwCmtDto> list = rvwCmtDao.selectAll(1);
        assertTrue(list.size()==1);

        rvwCmtDto = new RvwCmtDto(1, "to9251", 0, "comment", "asdf");
        assertTrue(rvwCmtDao.insert(rvwCmtDto)==1);
        assertTrue(rvwCmtDao.count(1)==2);

        list = rvwCmtDao.selectAll(1);
        assertTrue(list.size()==2);
    }

    @Test
    public void select() throws Exception {
        rvwCmtDao.deleteAll(1);
        RvwCmtDto rvwCmtDto = new RvwCmtDto(1, "to9251", 0, "comment", "asdf");
        assertTrue(rvwCmtDao.insert(rvwCmtDto)==1);
        assertTrue(rvwCmtDao.count(1)==1);

        List<RvwCmtDto> list = rvwCmtDao.selectAll(1);
        String cmt_cont = list.get(0).getCmt_cont();
        String usr_nm = list.get(0).getUsr_nm();
        assertTrue(cmt_cont.equals(rvwCmtDto.getCmt_cont()));
        assertTrue(usr_nm.equals(rvwCmtDto.getUsr_nm()));
    }

    @Test
    public void update() throws Exception {
        rvwCmtDao.deleteAll(1);
        RvwCmtDto commentDto = new RvwCmtDto(1, "to9251", 0, "comment", "asdf");
        assertTrue(rvwCmtDao.insert(commentDto)==1);
        assertTrue(rvwCmtDao.count(1)==1);

        List<RvwCmtDto> list = rvwCmtDao.selectAll(1);
        commentDto.setCmt_no(list.get(0).getCmt_no());
        commentDto.setCmt_cont("comment2");
        assertTrue(rvwCmtDao.update(commentDto)==1);

//        list = commentDao.selectAll(1);
//        String comment = list.get(0).getComment();
//        String commenter = list.get(0).getCommenter();
//        assertTrue(comment.equals(commentDto.getComment()));
//        assertTrue(commenter.equals(commentDto.getCommenter()));
    }
}