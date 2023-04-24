package com.devcamp.eztour.dao.reserv;

import com.devcamp.eztour.domain.reserv.CancelViewDto;
import com.devcamp.eztour.domain.reserv.PayDto;
import com.devcamp.eztour.domain.reserv.PayResultDto;
import com.devcamp.eztour.domain.reserv.PayViewDto;

import java.util.Map;

public interface PayDao {
    PayDto selectPay(String rsvt_no) throws Exception;

    String selectPayStatus(Map<String, String> map)throws Exception;

    int insertPay(PayDto payDto) throws Exception;

    int deletePayAdmin() throws Exception;

    CancelViewDto selectCancelInfo(String rsvt_no) throws Exception;

    PayDto selectPayById(Map<String, String> map) throws Exception;

    PayViewDto selectMlgAndPrdInfo(String pay_no) throws Exception;

    int updatePayAndRsvtResult(PayResultDto payResultDto) throws Exception;
}
