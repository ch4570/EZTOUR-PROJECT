package com.devcamp.eztour.dao.reserv;

import com.devcamp.eztour.domain.reserv.PageHandler;
import com.devcamp.eztour.domain.reserv.ReservDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class ReservDaoImplTest {
    @Autowired
    ReservDao reservDao;

    @Test
    public void insertReservTest() throws Exception {
        reservDao.deleteAllReserv();
//        reservDao.select

        ReservDto reservDto = new ReservDto("asdf123", "a001001", "asdf", "ahk",
                "010-1111-1111", "java@clang.com", 1000000, 1000000,
                "요청사항", null, 1, 0, 0);
        int lowCnt = reservDao.insertReserv(reservDto);
        assertTrue(lowCnt == 1);

        ReservDto reservDto1 = reservDao.selectReserv(reservDto.getRsvtNo());
        assertTrue(reservDto.getRsvtNo().equals(reservDto1.getRsvtNo()));
    }

    @Test
    public void selectReservTest() throws Exception{
        int lowCnt = 0;
        //전체삭제
        reservDao.deleteAllReserv();

//        입력
        ReservDto reservDto = new ReservDto("asdf123", "a001001", "asdf", "ahk",
                "010-1111-1111", "java@clang.com", 1000000, 1000000,
                "요청사항", null, 1, 0, 0);

        lowCnt = reservDao.insertReserv(reservDto);
        assertTrue(lowCnt==1);

//        select
        ReservDto reservDto1 = reservDao.selectReserv(reservDto.getRsvtNo());
        assertTrue(reservDto.getUsrId().equals(reservDto1.getUsrId()));
    }

    @Test
    public void selectReservListTest() throws Exception {
        reservDao.deleteAllReserv();

        ReservDto reservDto = new ReservDto("asdf123", "a001001", "asdf", "ahk",
                "010-1111-1111", "java@clang.com", 1000000, 1000000,
                "요청사항", null, 1, 0, 0);
        assertTrue(reservDao.insertReserv(reservDto)==1);

        ReservDto reservDto1 = new ReservDto("fsdf2344", "a002001", "asdf", "ahk",
                "010-1111-1111", "java@clang.com", 300000, 300000,
                "요청사항", null, 1, 0, 0);
        assertTrue(reservDao.insertReserv(reservDto1)==1);

        List<ReservDto> reservList = reservDao.selectReservList(reservDto.getUsrId());
        System.out.println("reservList = " + reservList);
        assertTrue(reservList.size()==2);
    }

    @Test
    public void selectAllReservTest() throws Exception {
        reservDao.deleteAllReserv();

        ReservDto reservDto = new ReservDto("asdf123", "a001001", "asdf", "ahk",
                "010-1111-1111", "java@clang.com", 1000000, 1000000,
                "요청사항", null, 1, 0, 0);
        assertTrue(reservDao.insertReserv(reservDto)==1);

        ReservDto reservDto1 = new ReservDto("fsdf2344", "a002001", "asdf", "ahk",
                "010-1111-1111", "java@clang.com", 300000, 300000,
                "요청사항", null, 1, 0, 0);
        assertTrue(reservDao.insertReserv(reservDto1)==1);

        ReservDto reservDto2 = new ReservDto("fswerfe3", "a002001", "aaaa", "aaa",
                "010-2311-2333", "java@clang.com", 300000, 300000,
                "요청사항", null, 1, 0, 0);
        assertTrue(reservDao.insertReserv(reservDto2)==1);

        List<ReservDto> reservList = reservDao.selectAllReserv();
        assertTrue(reservList.size()==3);
    }

    @Test
    public void deleteAllTest() throws Exception {
        reservDao.deleteAllReserv();

        ReservDto reservDto = new ReservDto("asdf123", "a001001", "asdf", "ahk",
                "010-1111-1111", "java@clang.com", 1000000, 1000000,
                "요청사항", null, 1, 0, 0);
        assertTrue(reservDao.insertReserv(reservDto)==1);

        ReservDto reservDto1 = new ReservDto("fsdf2344", "a002001", "asdf", "ahk",
                "010-1111-1111", "java@clang.com", 300000, 300000,
                "요청사항", null, 1, 0, 0);
        assertTrue(reservDao.insertReserv(reservDto1)==1);

        ReservDto reservDto2 = new ReservDto("fswerfe3", "a002001", "aaaa", "aaa",
                "010-2311-2333", "java@clang.com", 300000, 300000,
                "요청사항", null, 1, 0, 0);
        assertTrue(reservDao.insertReserv(reservDto2)==1);

        List<ReservDto> reservList = reservDao.selectAllReserv();
        assertTrue(reservList.size()==3);

        reservDao.deleteAllReserv();

        reservList = reservDao.selectAllReserv();
        assertTrue(reservList.size()==0);
    }

    @Test
    public void updateReservStatusTest() throws Exception {
        reservDao.deleteAllReserv();

        ReservDto reservDto = new ReservDto("asdf123", "a001001", "asdf", "ahk",
                "010-1111-1111", "java@clang.com", 1000000, 1000000,
                "요청사항", null, 1, 0, 0);
        assertTrue(reservDao.insertReserv(reservDto)==1);

        Map<String, String> map = new HashMap<>();
        String cmnCdRsvtStt = "6B";
        String rsvtNo = "asdf123";
        map.put("cmnCdRsvtStt", cmnCdRsvtStt);
        map.put("rsvtNo", rsvtNo);
        reservDao.updateReservStatus(map);

        ReservDto reservDto1 = reservDao.selectReserv(reservDto.getRsvtNo());
        assertTrue(reservDto1.getCmnCdRsvtStt().equals(cmnCdRsvtStt));
    }

    @Test
    public void selectReservListPageTest() throws Exception {
        reservDao.deleteAllReserv();
        Map<String, Integer> prdDtlCdMap = new HashMap<>();
        prdDtlCdMap.put("a001001", 1000000);
        prdDtlCdMap.put("a001002", 1000000);
        prdDtlCdMap.put("a001003", 1000000);
        prdDtlCdMap.put("a002001", 300000);
        prdDtlCdMap.put("a003001", 900000);
        prdDtlCdMap.put("a003002", 900000);


        String[] prdDtlCdArr = {"a001001", "a001002", "a001003", "a002001", "a003001", "a003002"};
        String usrId = "anony";
        int lowCnt = 0;

        for(int i=0; i < 6; i++){
            String rsvtNo = "asdf"+i;
//            int ranNum = (int)(Math.random() * 6);
            String prdDtlCd = prdDtlCdArr[i];

//            String mnRsvtNm = "여행자";
            String phn = "010-1111-1111";
            String email = "java@java.com";
            int sumPrc = prdDtlCdMap.get(prdDtlCd);
            int payFtrPrc = sumPrc;

            ReservDto reservDto = new ReservDto(rsvtNo, prdDtlCd, usrId, usrId,
                    phn, email, sumPrc, payFtrPrc,
                    "요청사항", null, 1, 0, 1);

            reservDao.insertReserv(reservDto);
        }
        lowCnt = reservDao.selectAllReserv().size();
        assertTrue(lowCnt==6);

        int page = 2;
        int pageSize = 5;
        PageHandler ph = new PageHandler(page, lowCnt);
        System.out.println("ph = " + ph);
        Map<String, Object> map = new HashMap<>();
        map.put("usrId", usrId);
        map.put("offset", (page - 1)  * pageSize);
        map.put("pageSize", ph.getPageSize());

        List list = reservDao.selectReservListPage(map);
        System.out.println("list = " + list);
        assertTrue(list.size()==1);
    }

}