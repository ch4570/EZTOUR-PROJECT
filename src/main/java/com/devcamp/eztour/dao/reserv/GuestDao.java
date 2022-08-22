package com.devcamp.eztour.dao.reserv;

import com.devcamp.eztour.domain.reserv.GuestDto;

import java.util.List;

public interface GuestDao {
    int insertGuest(GuestDto guestDto) throws Exception;

    GuestDto selectGuest(String gst_id) throws Exception;

    int deleteGuest(String gst_id) throws Exception;

    int deleteAllGuest() throws Exception;

    List<GuestDto> selectAllGuest() throws Exception;
}
