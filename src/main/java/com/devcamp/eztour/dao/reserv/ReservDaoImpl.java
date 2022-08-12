package com.devcamp.eztour.dao.reserv;

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
    public ReservDto selectReserv(String rsvtNo) throws Exception {
        return session.selectOne(namespace+"selectReserv", rsvtNo);
    }

    @Override
    public List<ReservDto> selectReservList(String usrId) throws Exception {
        return  session.selectList(namespace+"selectReservList", usrId);
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
    public int updateReservStatus(Map<String, String> map) throws Exception {
        //예약변호와 변경할 코드를 map으로 줌
        return session.update(namespace+"updateReservStatus", map);
    }

    @Override
    public int deleteAllReserv() throws Exception {
        return session.delete(namespace+"deleteAllReserv");
    }

    @Override
    public ReservInfoDto selectReservPrdInfo(String prdDtlCd) throws Exception{
        return session.selectOne(namespace+"selectReservPrdInfo", prdDtlCd);
    }
}
