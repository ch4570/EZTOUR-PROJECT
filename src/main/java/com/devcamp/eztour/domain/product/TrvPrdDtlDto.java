package com.devcamp.eztour.domain.product;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class TrvPrdDtlDto {

    private final String prd_cd;
    private final String prd_dtl_cd;
    private final String prd_nm;
    private final Date dpr_date;
    private final Date fin_date;
    private final String arl_nm;
    private final boolean rsvt_yn;
    private final Integer prd_str_prc;

}
