package com.devcamp.eztour.service.guest;

import com.devcamp.eztour.dao.guest.GuestDao;
import com.devcamp.eztour.domain.guest.GuestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    GuestDao guestDao;

    @Override
    public int registerGuest(GuestDto guestDto){
        int rowCnt = 0;
        try {
            rowCnt = guestDao.insertGuest(guestDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowCnt;
    }

    @Override
    public boolean checkGuestId(String gst_id){
        boolean result = false;

        try {
            if(guestDao.checkGuestId(gst_id)==1){
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int deleteGuestId(String gst_id){
        int lowCnt = 0;

        try {
            lowCnt = guestDao.deleteGuest(gst_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lowCnt;
        // 0이면 이미 존재하지 않는 아이디
    }
}
