package com.devcamp.eztour.dao.rvw;

import com.devcamp.eztour.domain.rvw.RvwDto;
import com.devcamp.eztour.domain.rvw.SearchCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class RvwDaoImplTest {
    @Autowired
    private RvwDao rvwDao;

    @Test
    public  void searchSelectPageTest() throws Exception {
        SearchCondition sc = new SearchCondition(1, 10, "title", "T");
        List<RvwDto> list = rvwDao.searchSelectPage(sc);
        System.out.println("list = " + list);
    }

    @Test
    public void count() {
        assertTrue(rvwDao != null);
        System.out.println("rvwDao = " + rvwDao);
    }

}