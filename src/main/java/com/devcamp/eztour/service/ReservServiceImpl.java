package com.devcamp.eztour.service;

import com.devcamp.eztour.dao.reserv.PayDao;
import com.devcamp.eztour.dao.reserv.ReservDao;
import com.devcamp.eztour.dao.reserv.TravelerInfoDao;
import com.devcamp.eztour.domain.reserv.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservServiceImpl implements ReservService {
    @Autowired
    ReservDao reservDao;
    @Autowired
    TravelerInfoDao travelerInfoDao;
    @Autowired
    PayDao payDao;

    @Override
    public ReservInfoDto readPrdInfo(String prd_dtl_cd) throws Exception{
        return reservDao.selectPrdInfo(prd_dtl_cd);
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

    @Override
    public List getReservConfInfo(String rsvt_no, String prd_dtl_cd) {
        List<Object> list = new ArrayList<>();
        //트랜잭션 처리!!
        try {
            list.add(reservDao.selectReservConfInfo(rsvt_no));
            list.addAll(travelerInfoDao.selectTrvlrInfoList(rsvt_no));
            list.addAll(reservDao.selectArlReqInfo(prd_dtl_cd));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List getReservList(String usr_id) throws Exception{
        return reservDao.selectReservList(usr_id);
    }

    public List getReservView(String rsvt_no, String prd_dtl_cd){
        List<Object> list = new ArrayList<>();
            //트랜잭션 처리!!
        try {
            ReservConfInfoDto rcid = reservDao.selectReservConfInfo(rsvt_no);
            list.add(rcid);
            list.addAll(travelerInfoDao.selectTrvlrInfoList(rsvt_no));
            list.addAll(reservDao.selectArlReqInfo(rcid.getPrd_dtl_cd()));
            list.add(payDao.selectPay(rsvt_no));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
