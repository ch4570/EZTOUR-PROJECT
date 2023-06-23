package com.devcamp.eztour.dao.guest;

import com.devcamp.eztour.domain.guest.GuestDto;

import java.util.List;

public interface GuestDao {
    int insertGuest(GuestDto guestDto) throws Exception;

    GuestDto selectGuest(String gst_id) throws Exception;

    int checkGuestId(String gst_id) throws Exception;

    int deleteGuest(String gst_id) throws Exception;

    int deleteAllGuest() throws Exception;

    List<GuestDto> selectAllGuest() throws Exception;
}
