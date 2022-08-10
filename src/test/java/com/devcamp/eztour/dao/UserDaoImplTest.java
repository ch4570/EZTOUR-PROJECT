package com.devcamp.eztour.dao;

import com.devcamp.eztour.dao.user.UserDao;
import com.devcamp.eztour.domain.user.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void insertTest() throws Exception {

        UserDto userDto = new UserDto("hoondal6", "a1", "b1", "1234", "훈달이");
        assertTrue(userDao.insertUser(userDto)==1);
//        assertTrue(userDao.insertUserHistory(userDto)==1);

    }
}
