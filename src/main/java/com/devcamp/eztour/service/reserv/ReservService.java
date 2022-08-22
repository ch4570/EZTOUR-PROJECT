package com.devcamp.eztour.service.reserv;

import com.devcamp.eztour.domain.reserv.AirlineReqDto;
import com.devcamp.eztour.domain.reserv.ReservDto;
import com.devcamp.eztour.domain.reserv.ReservInfoDto;
import com.devcamp.eztour.domain.reserv.TravelerInfoDto;

import java.util.List;
import java.util.Map;

public interface ReservService {
    ReservInfoDto readPrdInfo(String prd_dtl_cd) throws Exception;

    List<AirlineReqDto> readAirLineInfo(String prd_dtl_cd) throws Exception;

    int reserv(ReservDto reservDto) throws Exception;

    int saveTrvlrInfo(List<TravelerInfoDto> list) throws Exception;

    List getReservInfo(String prd_dtl_cd) throws Exception;

    List getReservConfInfo(String rsvt_no, String prd_dtl_cd);

    List getReservList(String usr_id) throws Exception;

    List getReservView(String rsvt_no, String prd_dtl_cd);

    List getReservListPage(Map<String, Object> map) throws Exception;

    int getUserMlg(String usr_id);

    int updateUserMlg(String option, Integer mlg, String usr_id);

    int updateRsvtStt(String cmn_cd_rsvt_stt, String cmn_cd_pay_stt, String rsvt_no);

    long getPayFtrPrc(String rsvt_no) throws Exception;
}
