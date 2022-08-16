package com.devcamp.eztour.dao.reserv;

import com.devcamp.eztour.domain.reserv.AirlineReqDto;
import com.devcamp.eztour.domain.reserv.PageHandler;
import com.devcamp.eztour.domain.reserv.ReservConfInfoDto;
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
//        reservDao.deleteAllReserv();
////        reservDao.select
//
//        ReservDto reservDto = new ReservDto("asdf123", "a001001", "asdf", "ahk",
//                "010-1111-1111", "java@clang.com", 1000000, 1000000,
//                "요청사항", null, 1, 0, 0);
//        int lowCnt = reservDao.insertReserv(reservDto);
//        assertTrue(lowCnt == 1);
//
//        ReservDto reservDto1 = reservDao.selectReserv(reservDto.getRsvt_no());
//        assertTrue(reservDto.getRsvt_no().equals(reservDto1.getRsvt_no()));
    }

    @Test
    public void selectReservTest() throws Exception{
//        int lowCnt = 0;
//        //전체삭제
//        reservDao.deleteAllReserv();
//
////        입력
//        ReservDto reservDto = new ReservDto("asdf123", "a001001", "asdf", "ahk",
//                "010-1111-1111", "java@clang.com", 1000000, 1000000,
//                "요청사항", null, 1, 0, 0);
//
//        lowCnt = reservDao.insertReserv(reservDto);
//        assertTrue(lowCnt==1);
//
////        select
//        ReservDto reservDto1 = reservDao.selectReserv(reservDto.getRsvt_no());
//        assertTrue(reservDto.getUsr_id().equals(reservDto1.getUsr_id()));
    }

    @Test
    public void selectReservListTest() throws Exception {
//        reservDao.deleteAllReserv();

//        ReservDto reservDto = new ReservDto("asdf123", "a001001", "asdf", "ahk",
//                "010-1111-1111", "java@clang.com", 1000000, 1000000,
//                "요청사항", null, 1, 0, 0);
//        assertTrue(reservDao.insertReserv(reservDto)==1);
//
//        ReservDto reservDto1 = new ReservDto("fsdf2344", "a002001", "asdf", "ahk",
//                "010-1111-1111", "java@clang.com", 300000, 300000,
//                "요청사항", null, 1, 0, 0);
//        assertTrue(reservDao.insertReserv(reservDto1)==1);
//
//        List<ReservDto> reservList = reservDao.selectReservList(reservDto.getUsr_id());
//        System.out.println("reservList = " + reservList);
//        assertTrue(reservList.size()==2);


    }

    @Test
    public void selectAllReservTest() throws Exception {
//        reservDao.deleteAllReserv();
//
//        ReservDto reservDto = new ReservDto("asdf123", "a001001", "asdf", "ahk",
//                "010-1111-1111", "java@clang.com", 1000000, 1000000,
//                "요청사항", null, 1, 0, 0);
//        assertTrue(reservDao.insertReserv(reservDto)==1);
//
//        ReservDto reservDto1 = new ReservDto("fsdf2344", "a002001", "asdf", "ahk",
//                "010-1111-1111", "java@clang.com", 300000, 300000,
//                "요청사항", null, 1, 0, 0);
//        assertTrue(reservDao.insertReserv(reservDto1)==1);
//
//        ReservDto reservDto2 = new ReservDto("fswerfe3", "a002001", "aaaa", "aaa",
//                "010-2311-2333", "java@clang.com", 300000, 300000,
//                "요청사항", null, 1, 0, 0);
//        assertTrue(reservDao.insertReserv(reservDto2)==1);
//
//        List<ReservDto> reservList = reservDao.selectAllReserv();
//        assertTrue(reservList.size()==3);
      }
//
    @Test
    public void selectReservConfInfoTest() throws Exception {
//        String rsvt_no = "it16603758712938596";
////        ReservConfInfoDto rcid = ;
//        assertNotNull(reservDao.selectReservConfInfo(rsvt_no));
    }
//
    @Test
    public void deleteAllTest() throws Exception {
//        reservDao.deleteAllReserv();
//
//        ReservDto reservDto = new ReservDto("asdf123", "a001001", "asdf", "ahk",
//                "010-1111-1111", "java@clang.com", 1000000, 1000000,
//                "요청사항", null, 1, 0, 0);
//        assertTrue(reservDao.insertReserv(reservDto)==1);
//
//        ReservDto reservDto1 = new ReservDto("fsdf2344", "a002001", "asdf", "ahk",
//                "010-1111-1111", "java@clang.com", 300000, 300000,
//                "요청사항", null, 1, 0, 0);
//        assertTrue(reservDao.insertReserv(reservDto1)==1);
//
//        ReservDto reservDto2 = new ReservDto("fswerfe3", "a002001", "aaaa", "aaa",
//                "010-2311-2333", "java@clang.com", 300000, 300000,
//                "요청사항", null, 1, 0, 0);
//        assertTrue(reservDao.insertReserv(reservDto2)==1);
//
//        List<ReservDto> reservList = reservDao.selectAllReserv();
//        assertTrue(reservList.size()==3);
//
//        reservDao.deleteAllReserv();
//
//        reservList = reservDao.selectAllReserv();
//        assertTrue(reservList.size()==0);
    }
//
    @Test
    public void updateReservStatusTest() throws Exception {
//        reservDao.deleteAllReserv();
//
//        ReservDto reservDto = new ReservDto("asdf123", "a001001", "asdf", "ahk",
//                "010-1111-1111", "java@clang.com", 1000000, 1000000,
//                "요청사항", null, 1, 0, 0);
//        assertTrue(reservDao.insertReserv(reservDto)==1);
//
//        Map<String, String> map = new HashMap<>();
//        String cmn_cd_rsvt_stt = "6B";
//        String rsvt_no = "asdf123";
//        map.put("cmnCdRsvtStt", cmn_cd_rsvt_stt);
//        map.put("rsvtNo", rsvt_no);
//        reservDao.updateReservStatus(map);
//
//        ReservDto reservDto1 = reservDao.selectReserv(reservDto.getRsvt_no());
//        assertTrue(reservDto1.getCmn_cd_rsvt_stt().equals(cmn_cd_rsvt_stt));
    }
//
    @Test
    public void selectReservListPageTest() throws Exception {
//        reservDao.deleteAllReserv();
//        Map<String, Integer> prdDtlCdMap = new HashMap<>();
//        prdDtlCdMap.put("a001001", 1000000);
//        prdDtlCdMap.put("a001002", 1000000);
//        prdDtlCdMap.put("a001003", 1000000);
//        prdDtlCdMap.put("a002001", 300000);
//        prdDtlCdMap.put("a003001", 900000);
//        prdDtlCdMap.put("a003002", 900000);
//
//
//        String[] prdDtlCdArr = {"a001001", "a001002", "a001003", "a002001", "a003001", "a003002"};
//        String usr_id = "anony";
//        int lowCnt = 0;
//
//        for(int i=0; i < 6; i++){
//            String rsvt_no = "asdf"+i;
////            int ranNum = (int)(Math.random() * 6);
//            String prd_dtl_cd = prdDtlCdArr[i];
//
////            String mnRsvtNm = "여행자";
//            String phn = "010-1111-1111";
//            String email = "java@java.com";
//            int sum_prc = prdDtlCdMap.get(prd_dtl_cd);
//            int pay_ftr_prc = sum_prc;
//
//            ReservDto reservDto = new ReservDto(rsvt_no, prd_dtl_cd, usr_id, usr_id,
//                    phn, email, sum_prc, pay_ftr_prc,
//                    "요청사항", null, 1, 0, 1);
//
//            reservDao.insertReserv(reservDto);
//        }
//        lowCnt = reservDao.selectAllReserv().size();
//        assertTrue(lowCnt==6);
//
//        int page = 2;
//        int pageSize = 5;
//        PageHandler ph = new PageHandler(page, lowCnt);
//        System.out.println("ph = " + ph);
//        Map<String, Object> map = new HashMap<>();
//        map.put("usr_id", usr_id);
//        map.put("offset", (page - 1)  * pageSize);
//        map.put("pageSize", ph.getPageSize());
//
//        List list = reservDao.selectReservListPage(map);
//        System.out.println("list = " + list);
//        assertTrue(list.size()==1);

        Map<String, Object> map = new HashMap<>();
            map.put("usr_id", "asdf");
            map.put("offset", 0);
            map.put("pageSize", 10);
        List<Object> list = reservDao.selectReservListPage(map);
        assertTrue(list.size()==10);
    }

    @Test
    public void selectArlReqInfoTest() throws Exception {
        String prd_dtl_cd = "a001001";
        List<AirlineReqDto> list = reservDao.selectArlReqInfo(prd_dtl_cd);
        assertTrue(list.size()==2);
    }
}