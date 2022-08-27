package com.devcamp.eztour.dao.reserv;

import com.devcamp.eztour.domain.reserv.AirlineReqDto;
import com.devcamp.eztour.domain.reserv.ReservConfInfoDto;
import com.devcamp.eztour.domain.reserv.ReservDto;
import com.devcamp.eztour.domain.reserv.ReservInfoDto;

import java.util.List;
import java.util.Map;

public interface ReservDao {
    int insertReserv(ReservDto reservDto) throws Exception;

    ReservDto selectReserv(String rsvt_no) throws Exception;

    List<ReservDto> selectReservList(String usr_id) throws Exception;

    List<Object> selectReservListPage(Map<String, Object> map) throws Exception;

    ReservDto selectReservByRsvtNo(String rsvt_no) throws Exception;

    List<ReservDto> selectAllReserv() throws Exception;

    ReservConfInfoDto selectReservConfInfo(String rsvt_no) throws Exception;

    List<ReservDto> selectTheUnAppredListPage(Map<String, Integer> map) throws Exception;

    int selectTheUnAppredListCnt() throws Exception;

    int updateReservStatus(Map<String, String> map) throws Exception;

    int deleteAllReserv() throws Exception;

    ReservInfoDto selectPrdInfo(String prd_dtl_cd) throws Exception;

    List<AirlineReqDto> selectArlReqInfo(String prd_dtl_cd) throws Exception;

    int selectUserMlg(String usr_id) throws Exception;

    int updateUserMlg(Map map) throws Exception;

    int updateRsvtStt(Map map) throws Exception;

    long selectPayFtrPrc(String rsvt_no) throws Exception;

    int deleteReserv(String rsvt_no) throws Exception;

    int selectReservCnt(String usr_id) throws Exception;

    String selectGuestReserv(Map<String, String> map) throws Exception;

    int updateReservCancel(ReservDto reservDto) throws Exception;

    int updateReservCnt(Map<String, Object> map) throws Exception;
}
