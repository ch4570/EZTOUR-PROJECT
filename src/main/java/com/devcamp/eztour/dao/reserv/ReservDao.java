package com.devcamp.eztour.dao.reserv;

import com.devcamp.eztour.domain.reserv.ReservDto;
import com.devcamp.eztour.domain.reserv.ReservInfoDto;

import java.util.List;
import java.util.Map;

public interface ReservDao {
    int insertReserv(ReservDto reservDto) throws Exception;

    public ReservDto selectReserv(String rsvtNo) throws Exception;

    public List<ReservDto> selectReservList(String usrId) throws Exception;

    public List<Object> selectReservListPage(Map<String, Object> map) throws Exception;

    public List<ReservDto> selectAllReserv() throws Exception;

    public int updateReservStatus(Map<String, String> map) throws Exception;

    public int deleteAllReserv() throws Exception;

    public ReservInfoDto selectReservPrdInfo(String prdDtlCd) throws Exception;
}
