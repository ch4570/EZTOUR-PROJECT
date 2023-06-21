package com.devcamp.eztour.service.guest;

import com.devcamp.eztour.domain.guest.GuestDto;

public interface GuestService {
    int registerGuest(GuestDto guestDto);

    boolean checkGuestId(String gst_id);

    int deleteGuestId(String gst_id);
}
