package com.devcamp.eztour.service.reserv;

import com.devcamp.eztour.dao.reserv.GuestDao;
import com.devcamp.eztour.dao.reserv.PayDao;
import com.devcamp.eztour.dao.reserv.ReservDao;
import com.devcamp.eztour.dao.reserv.TravelerInfoDao;
import com.devcamp.eztour.domain.reserv.*;
import com.devcamp.eztour.service.reserv.ReservService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservServiceImpl implements ReservService {
    @Autowired
    ReservDao reservDao;
    @Autowired
    TravelerInfoDao travelerInfoDao;
    @Autowired
    PayDao payDao;
    @Autowired
    GuestDao guestDao;
    @Autowired
    DataSource ds;

    @Override
    public ReservInfoDto readPrdInfo(String prd_dtl_cd) throws Exception{
        return reservDao.selectPrdInfo(prd_dtl_cd);
        //유효성검사 필요? 예외를 던져야하는가?
    }

    @Override
    public List<AirlineReqDto> readAirLineInfo(String prd_dtl_cd) throws Exception {
        return reservDao.selectArlReqInfo(prd_dtl_cd);
    }

    @Override
    public int reserv(ReservDto reservDto) throws Exception {
        return reservDao.insertReserv(reservDto);
    }

    @Override
    public int saveTrvlrInfo(List<TravelerInfoDto> list) throws Exception{
        return travelerInfoDao.insertTrvlrInfo(list);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean saveReservInfo(ReservDto reservDto, List<TravelerInfoDto> list) throws Exception {
        int rowCntForReserv = reservDao.insertReserv(reservDto); //1
        int rowCntForTrvlrInfo = travelerInfoDao.insertTrvlrInfo(list); //최소 1

        if(rowCntForReserv+rowCntForTrvlrInfo < 2){
            throw new Exception("여행예약정보를 저장하는데 실패했습니다.");
        }

        return true;
    }

    @Override
    public ReservInfoDto getReservInfo(String prd_dtl_cd) throws Exception{
        return reservDao.selectPrdInfo(prd_dtl_cd);
    }

    @Override
    public List getReservConfInfo(String rsvt_no, String prd_dtl_cd) {
        List<Object> list = new ArrayList<>();
        //트랜잭션 처리!!
        //예외 되던지기
        try {
            list.add(reservDao.selectReservConfInfo(rsvt_no));
            list.addAll(travelerInfoDao.selectTrvlrInfoList(rsvt_no));
//            list.addAll(reservDao.selectArlReqInfo(prd_dtl_cd));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List getReservList(String usr_id) throws Exception{
        return reservDao.selectReservList(usr_id);
    }

    @Override
    public List getReservView(String rsvt_no){
        List<Object> list = new ArrayList<>();
            //트랜잭션 처리!!
        try {
            ReservConfInfoDto rcid = reservDao.selectReservConfInfo(rsvt_no);
            list.add(rcid);
            list.addAll(travelerInfoDao.selectTrvlrInfoList(rsvt_no));
//            list.addAll(reservDao.selectArlReqInfo(rcid.getPrd_dtl_cd()));
            list.add(payDao.selectPay(rsvt_no));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List getReservListPage(Map<String, Object> map) throws Exception{
        return reservDao.selectReservListPage(map);
    }

    @Override
    public int getUserMlg(String usr_id) {
        int usr_mlg = 0;
        try {
            usr_mlg = reservDao.selectUserMlg(usr_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usr_mlg;
    }

    @Override
    public int updateUserMlg(String option, Integer mlg, String usr_id){
        Map map = new HashMap();
        map.put("option", option);
        map.put("mlg", mlg);
        map.put("usr_id", usr_id);
        int rowCnt = 0;
        try {
            rowCnt = reservDao.updateUserMlg(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowCnt;
    }

    @Override
    public int updateRsvtStt(String cmn_cd_rsvt_stt, String cmn_cd_pay_stt, String rsvt_no){
        Map map = new HashMap();
        map.put("cmn_cd_rsvt_stt", cmn_cd_rsvt_stt);
        map.put("cmn_cd_pay_stt", cmn_cd_pay_stt);
        map.put("rsvt_no", rsvt_no);
        int rowCnt = 0;
        try {
            rowCnt = reservDao.updateRsvtStt(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCnt;
    }

    @Override
    public long getPayFtrPrc(String rsvt_no) throws Exception {
        return reservDao.selectPayFtrPrc(rsvt_no);
    }

    @Override
    public Map<String, Object> getTheUnAppredList(Integer page, Integer pageSize) {
        Map<String, Object> result = new HashMap<>();

        try {
            int theUnAppredCnt = reservDao.selectTheUnAppredListCnt();

            PageHandler ph = new PageHandler(page, theUnAppredCnt);
            result.put("pageHandler", ph);

            Map<String, Integer> map = new HashMap<>();
            map.put("offset", (page-1) * pageSize);
            map.put("pageSize", pageSize);

            List<ReservDto> list = reservDao.selectTheUnAppredListPage(map);
            result.put("unAppredList", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int getReservCnt(String usr_id) {
        int rowCnt = 0;
        try {
            rowCnt = reservDao.selectReservCnt(usr_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCnt;
    }

    @Transactional(rollbackFor = Exception.class)
    public String guestReservCheck(String rsvt_no, String mn_rsvt_nm, String phn) throws Exception{
        Map<String, String> map = new HashMap<>();
        map.put("rsvt_no", rsvt_no);
        map.put("mn_rsvt_nm", mn_rsvt_nm);
        map.put("phn", phn);

        return reservDao.selectGuestReserv(map);
    }

    @Override
    public int changeReservSttNCnt(ReservDto reservDto) throws Exception{
        return reservDao.updateReservCancel(reservDto);
    }

    @Override
    public int changeReservCount(String prd_dtl_cd, String rsvt_no, String option){
        Map<String, Object> map = new HashMap<>();
        map.put("prd_dtl_cd", prd_dtl_cd);
        map.put("rsvt_no", rsvt_no);
        map.put("option", option);
        int rowCnt = 0;
        try {
            rowCnt = reservDao.updateReservCnt(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCnt;
    }

    @Override
    public ReservDto checkReservInfo(Map<String, String> map) throws Exception {
        return reservDao.selectFtrPrcAndStt(map);
    }
}
