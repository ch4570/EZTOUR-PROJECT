package com.devcamp.eztour.dao.pay;

import com.devcamp.eztour.domain.pay.CancelViewDto;
import com.devcamp.eztour.domain.pay.PayDto;
import com.devcamp.eztour.domain.pay.PayResultDto;
import com.devcamp.eztour.domain.pay.PayViewDto;
import com.devcamp.eztour.domain.reserv.*;

import java.util.List;
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

    List<StatsGndrAndAgePerHourDto> selectGndrAndAgePerHour() throws Exception;

    List<StatsTopListDto> selectTopNList(int limitNum) throws Exception;

    List<StatsTopListDto> selectTopNPrdLikelyPay(int limitNum) throws Exception;
}
