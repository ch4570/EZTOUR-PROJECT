package com.devcamp.eztour.dao.reserv;

import com.devcamp.eztour.dao.product.ProductDao;
import com.devcamp.eztour.dao.product.ProductDetailDao;
import com.devcamp.eztour.dao.user.UserDao;
import com.devcamp.eztour.domain.reserv.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class ReservDaoImplTest {
    @Autowired
    ReservDao reservDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    ProductDetailDao productDetailDao;


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

    @Test
    public void selectPrdInfoTest() throws Exception {
//        // trv_prd 상품 더미데이터
//        TrvPrdDtoForTest pdft = new TrvPrdDtoForTest("a001", "mg", "mongol", "몽골의 드넓은 초원을 경험해보세요", "몽골은... 상세설명", "1박 2일", 1000000, "2022-10-19 00:00:00", "2022-10-20 00:00:00", 0, false, false, false, false, 0, 0, 0, false, new Date(), "adminahk", new Date(), "adminahk");
//        TrvPrdDtoForTest pdft2 = new TrvPrdDtoForTest("a003", "usa", "healing", "미국 사막속에서 힐링을", "미국은... 상세설명", "10박 12일", 5000000, "2022-10-19 00:00:00", "2022-10-27 00:00:00", 0, false, false, false, false, 0, 0, 0, false, new Date(), "adminahk", new Date(), "adminahk");
//        // trv_prd_dtl 상품상세코드 더미데이터
//        TraPrdDtlForTest tpdft = new TraPrdDtlForTest("a001001", "a001", 1000000, "mongolAir", true, true, 0, 15, 25, "2022-10-19 00:00:00", new Date(), "adminAHK", new Date(), "adminAHK");
//        TraPrdDtlForTest tpdft2 = new TraPrdDtlForTest("a001002", "a001", 1500000, "mongolAir", true, true, 0, 15, 25, "2022-10-20 00:00:00", new Date(), "adminAHK", new Date(), "adminAHK");
//        // prd_prc 상품가격 더미데이터
//        PrdPrcForTest ppft = new PrdPrcForTest("a001001", 1000000, 1000000, 800000, new Date(), "adminAHK", "adminAHK");

//        insert into trv_prd values ("a001", "mg", "mongol", "몽골의 드넓은 초원을 경험해보세요", "몽골은... 상세설명", "1박 2일", 1000000, "2022-10-19 00:00:00", "2022-10-20 00:00:00", 0, 0, 0, 0, 0, 0, 0, 0, 0, now(), "asdf", now(), "asdf");
//        insert into trv_prd_dtl values("a001001", "a001", 1000000, "mongolAir", true, true, 0, 15, 25, "2022-10-19 00:00:00", now(), "adminAHK", now(), "adminAHK");
//        insert into trv_prd_dtl values("a001002", "a001", 1500000, "mongolAir", true, true, 0, 15, 25, "2022-10-20 00:00:00", now(), "adminAHK", now(), "adminAHK");
//        insert into prd_prc(prd_dtl_cd, prd_cd, adt_prc, chd_prc, bb_prc, frs_reg_date, frs_rgs_no, fnl_mod_no) values ("a001001", "a001", 1000000, 1000000, 800000, now(), "adminAHK", "adminAHK");
//        insert into prd_prc(prd_dtl_cd, prd_cd, adt_prc, chd_prc, bb_prc, frs_reg_date, frs_rgs_no, fnl_mod_no) values ("a001002", "a001", 1200000, 1000000, 800000, now(), "adminAHK", "adminAHK");
    }

    @Test
    public void selectUserMlgTest() throws Exception{
        String usr_id = "asdf";
        int mlg = reservDao.selectUserMlg(usr_id);
        assertTrue(mlg == 0);
    }

    @Test
    public void updateUserMlgTest() throws Exception {
        String usr_id = "asdf";
        Map map = new HashMap<>();
        map.put("option", "plus");
        map.put("mlg", 100);
        map.put("usr_id", usr_id);

        reservDao.updateUserMlg(map);
    }

    @Test
    public void updateRsvtSttTest() throws Exception{
        Map map = new HashMap();
//        map.put("cmn_cd_rsvt_stt", "6E");
//        map.put("rsvt_no", "it1660462401002");
        reservDao.updateRsvtStt(map);
    }

    @Test
    public void selectPayFtrPrcTest() throws Exception{
        String rsvt_no = "12312333312";
        reservDao.deleteReserv(rsvt_no);
        assertTrue(reservDao.selectReserv(rsvt_no)==null);

        ReservDto reservDto = new ReservDto("12312333312", "a001001", "asdf", "asdf", "asdf","01111111111", "asdf@sdf.com", 1000000, 1000000, "aaaaa", "6A", "7A", new Date(), null, 1, 0, 0);
        assertTrue(reservDao.insertReserv(reservDto)==1);
    }

    @Test
    public void selectReservByRsvtNoTest() throws Exception {
        String rsvt_no = "A010011661104869005";
        ReservDto reservDto = reservDao.selectReservByRsvtNo(rsvt_no);
        assertTrue(rsvt_no.equals(reservDto.getRsvt_no()));
    }

    @Test
    public void selectTheUnAppredListCntTest() throws Exception{
        int cnt = reservDao.selectTheUnAppredListCnt();
        assertTrue(cnt == 1);
    }

    @Test
    public void selectTheUnAppredListPageTest() throws Exception {
        PageHandler ph = new PageHandler(1,1);
        Map<String, Integer> map = new HashMap<>();
        map.put("pageSize", ph.getPageSize());
        map.put("offset", ph.getBeginPage()-1);

        List<ReservDto> list = reservDao.selectTheUnAppredListPage(map);
        assertTrue(list.size()==1);
    }
}