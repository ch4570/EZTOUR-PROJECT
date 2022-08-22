package com.devcamp.eztour.dao.reserv;

import com.devcamp.eztour.domain.reserv.GuestDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GuestDaoImpl implements GuestDao {
    @Autowired
    SqlSession session;
    String namespace = "com.devcamp.eztour.guestMapper.";

    @Override
    public int insertGuest(GuestDto guestDto) throws Exception{
        return session.insert(namespace+"insertGuest", guestDto);
    }

    @Override
    public GuestDto selectGuest(String gst_id) throws Exception{
        return session.selectOne(namespace+"selectGuest", gst_id);
    }

    @Override
    public int deleteGuest(String gst_id) throws Exception {
        return session.delete(namespace+"deleteGuest", gst_id);
    }

    @Override
    public int deleteAllGuest() throws Exception{
        return session.delete(namespace+"deleteAllGuest");
    }

    @Override
    public List<GuestDto> selectAllGuest() throws Exception{
        return session.selectList(namespace+"selectAllGuest");
    }
}
