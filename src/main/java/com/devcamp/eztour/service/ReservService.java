package com.devcamp.eztour.service;

import com.devcamp.eztour.domain.reserv.ReservInfoDto;

public interface ReservService {
    ReservInfoDto readPrdInfo(String prdDtlCd) throws Exception;
}
