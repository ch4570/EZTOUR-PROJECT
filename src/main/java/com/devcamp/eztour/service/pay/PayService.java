package com.devcamp.eztour.service.pay;

import com.devcamp.eztour.domain.guest.GuestDto;
import com.devcamp.eztour.domain.pay.CancelViewDto;
import com.devcamp.eztour.domain.pay.PayDto;
import com.devcamp.eztour.domain.pay.PayResultDto;
import com.devcamp.eztour.domain.pay.PayViewDto;
import com.devcamp.eztour.domain.reserv.*;
import com.devcamp.eztour.domain.user.UserDto;

import java.util.List;
import java.util.Map;

public interface PayService {
    GuestDto getGuestInfo(String gst_id);

    UserDto getUserInfo(String usr_id);

    String getPayStatus(String rsvt_no, String usr_id);

    int savePayInfo(PayDto payDto);

    CancelViewDto getCancelInfo(String rsvt_no);

    PayDto getPayInfo(String rsvt_no,String usr_id) throws Exception;

    Map<String, Object> cancelPay(PayDto payDto, String access_token) throws Exception;

    int deleteTrvlrList(String rsvt_no);

    PayViewDto getMlgAndPrdInfo(String pay_no) throws Exception;

    void savePayResult(PayResultDto payResultDto) throws Exception;

    List<StatsGndrAndAgePerHourDto> getGndrAndAgePerHour();

    List<StatsTopListDto> getTopNList(int limitNum);

    List<StatsTopListDto> getTopNPrdLikelyPay(int limitNum);
}