package com.devcamp.eztour.service.reserv;

import com.devcamp.eztour.domain.reserv.GuestDto;

public interface GuestService {
    int registerGuest(GuestDto guestDto);

    boolean checkGuestId(String gst_id);

    int deleteGuestId(String gst_id);
}
