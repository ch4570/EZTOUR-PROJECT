package com.devcamp.eztour.dao.reserv;

import com.devcamp.eztour.domain.reserv.PayDto;

public interface PayDao {
    PayDto selectPay(String rsvt_no) throws Exception;

    int insertPay(PayDto payDto) throws Exception;

    int deletePayAdmin() throws Exception;
}
