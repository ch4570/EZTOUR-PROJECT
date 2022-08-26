package com.devcamp.eztour.service.reserv;

import com.devcamp.eztour.domain.reserv.*;

import java.util.List;
import java.util.Map;

public interface ReservService {
    ReservInfoDto readPrdInfo(String prd_dtl_cd) throws Exception;

    List<AirlineReqDto> readAirLineInfo(String prd_dtl_cd) throws Exception;

    int reserv(ReservDto reservDto) throws Exception;

    int saveTrvlrInfo(List<TravelerInfoDto> list) throws Exception;

    boolean saveReservInfo(ReservDto reservDto, List<TravelerInfoDto> list) throws Exception;

    ReservInfoDto getReservInfo(String prd_dtl_cd) throws Exception;

    List getReservConfInfo(String rsvt_no, String prd_dtl_cd);

    List getReservList(String usr_id) throws Exception;

    List getReservView(String rsvt_no);

    List getReservListPage(Map<String, Object> map) throws Exception;

    int getUserMlg(String usr_id);

    int updateUserMlg(String option, Integer mlg, String usr_id);

    int updateRsvtStt(String cmn_cd_rsvt_stt, String cmn_cd_pay_stt, String rsvt_no);

    long getPayFtrPrc(String rsvt_no) throws Exception;

    Map<String, Object>  getTheUnAppredList(Integer page, Integer pageSize);

    int getReservCnt(String usr_id);

    String guestReservCheck(String rsvt_no, String mn_rsvt_nm, String phn) throws Exception;

    int changeReservSttNCnt(ReservDto reservDto) throws Exception;

    int changeReservCount(String prd_dtl_cd, String rsvt_no, String option);
}
