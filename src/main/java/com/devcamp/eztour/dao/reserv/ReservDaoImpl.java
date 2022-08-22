package com.devcamp.eztour.dao.reserv;

import com.devcamp.eztour.domain.reserv.AirlineReqDto;
import com.devcamp.eztour.domain.reserv.ReservConfInfoDto;
import com.devcamp.eztour.domain.reserv.ReservDto;
import com.devcamp.eztour.domain.reserv.ReservInfoDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ReservDaoImpl implements ReservDao {
    @Autowired
    SqlSession session;

    String namespace = "com.devcamp.eztour.rsvtMapper.";

    @Override
    public int insertReserv(ReservDto reservDto) throws Exception {
        return session.insert(namespace+"insertReserv", reservDto);
    }

    @Override
    public ReservDto selectReserv(String rsvt_no) throws Exception {
        return session.selectOne(namespace+"selectReserv", rsvt_no);
    }

    @Override
    public List<ReservDto> selectReservList(String usr_id) throws Exception {
        return  session.selectList(namespace+"selectReservList", usr_id);
    }

    @Override
    public List<Object> selectReservListPage(Map<String, Object> map) throws Exception{
        return session.selectList(namespace+"selectReservListPage", map);
    }

    @Override
    public List<ReservDto> selectAllReserv() throws Exception {
        return session.selectList(namespace+"selectAllReserv");
    }

    @Override
    public ReservConfInfoDto selectReservConfInfo(String rsvt_no) throws Exception {
        return session.selectOne(namespace+"selectReservConfInfo", rsvt_no);
    }

    @Override
    public int updateReservStatus(Map<String, String> map) throws Exception {
        //예약변호와 변경할 코드를 map으로 줌
        return session.update(namespace+"updateReservStatus", map);
    }

    @Override
    public int deleteAllReserv() throws Exception {
        return session.delete(namespace+"deleteAllReserv");
    }

    @Override
    public ReservInfoDto selectPrdInfo(String prd_dtl_cd) throws Exception{
        return session.selectOne(namespace+"selectPrdInfo", prd_dtl_cd);
    }

    @Override
    public List<AirlineReqDto> selectArlReqInfo(String prd_dtl_cd) throws Exception{
        return session.selectList(namespace+"selectAirInfo", prd_dtl_cd);
    }

    @Override
    public int selectUserMlg(String usr_id) throws Exception {
        return session.selectOne(namespace+"selectUserMlg", usr_id);
    }

    @Override
    public int updateUserMlg(Map map) throws Exception {
        return session.update(namespace+"updateUserMlg", map);
    }

    @Override
    public int updateRsvtStt(Map map) throws Exception {
        return session.update(namespace+"updateRsvtStt", map);
    }

    @Override
    public long selectPayFtrPrc(String rsvt_no) throws Exception {
        return session.selectOne(namespace+"selectPayFtrPrc", rsvt_no);
    }

    @Override
    public int deleteReserv(String rsvt_no) throws Exception {
        return session.delete(namespace+"deleteReserv", rsvt_no);
    }

}
