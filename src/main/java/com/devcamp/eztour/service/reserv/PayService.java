package com.devcamp.eztour.service.reserv;

import com.devcamp.eztour.domain.reserv.CancelViewDto;
import com.devcamp.eztour.domain.reserv.GuestDto;
import com.devcamp.eztour.domain.reserv.PayDto;
import com.devcamp.eztour.domain.reserv.Payment;
import com.devcamp.eztour.domain.user.UserDto;

import java.util.Map;

public interface PayService {
    GuestDto getGuestInfo(String gst_id);

    UserDto getUserInfo(String usr_id);

    String getPayStatus(String rsvt_no, String usr_id);

    int savePayInfo(PayDto payDto);

    String getToken(String imp_uid, String merchant_uid);

    Map<String, Object> getPaymentData(String imp_uid, String access_token);

    CancelViewDto getCancelInfo(String rsvt_no);

    PayDto getPayInfo(String rsvt_no,String usr_id) throws Exception;

    Map<String, Object> cancelPay(PayDto payDto, String access_token) throws Exception;

    int deleteTrvlrList(String rsvt_no);
}