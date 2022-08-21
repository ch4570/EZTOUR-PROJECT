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

//    @Test
//    public void insertTest() throws Exception {
//        UserDto userDto = new UserDto("hoondal8", "1234", "훈달이", "19950101", "여성", "aaa@aaa.com", "01000000000");
//        assertTrue(userDao.insertUsr(userDto)==1);
//        assertTrue(userDao.insertUsrHis(userDto)==1);
//    }

    @Test
    public void selectTest() throws Exception {
        UserDto userDto = new UserDto();
        String usr_id = "hoondal8";
        UserDto user = userDao.selectUsr(usr_id);

        System.out.println(user);
    }

    @Test
    public void updateTest() throws Exception {
        UserDto user = new UserDto();
        user.setUsr_id("hoondal8");
        user.setPwd("4567");
        user.setEmail("bbb@bbb.com");
        user.setPhn("010-1111-1111");
        user.setCmn_cd_prf_img("3e");
        userDao.updateUsr(user);
        userDao.updateUsrHst(user);

        UserDto user2 = userDao.selectUsr("hoondal8");
        System.out.println(user2);
    }

    @Test
    public void deleteTest() throws Exception {
        UserDto user = new UserDto();
        user.setUsr_id("hoondal8");
        user.setCmn_cd_drp("4c");
        userDao.deleteUsr(user.getUsr_id());

        UserDto user2 = userDao.selectUsr("hoondal8");
        System.out.println(user2);
    }

    }
