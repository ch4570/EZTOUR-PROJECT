package com.devcamp.eztour.service;

import com.devcamp.eztour.domain.reserv.AirlineReqDto;
import com.devcamp.eztour.domain.reserv.ReservDto;
import com.devcamp.eztour.domain.reserv.ReservInfoDto;
import com.devcamp.eztour.domain.reserv.TravelerInfoDto;

import java.util.List;

public interface ReservService {
    ReservInfoDto readPrdInfo(String prd_dtl_cd) throws Exception;

    List<AirlineReqDto> readAirLineInfo(String prd_dtl_cd) throws Exception;

    int reserv(ReservDto reservDto) throws Exception;

    int saveTrvlrInfo(List<TravelerInfoDto> list) throws Exception;

    List getReservConfInfo(String rsvt_no, String prd_dtl_cd);

    List getReservList(String usr_id) throws Exception;

    List getReservView(String rsvt_no, String prd_dtl_cd);
}
