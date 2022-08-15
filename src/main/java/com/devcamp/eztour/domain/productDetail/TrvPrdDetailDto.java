package com.devcamp.eztour.domain.productDetail;

import lombok.*;

import java.util.Date;

@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class TrvPrdDetailDto {

    private final String prd_cd;
    private final String dstn_cd;
    private final String cmn_cd_thm;
    private final String prd_nm;
    private final String prd_dtl_desc;
    private final String trv_per;
    private final Integer prd_str_prc;
    private final Date dpr_str_date;
    private final Date dpr_fin_date;
    private final boolean evnt_yn;
    private final boolean fin_yn;
    private final boolean act_yn;
    private final boolean dc_yn;
    private final Integer vcnt;
    private final boolean lk_yn;
    private final String img_pth;
    private final boolean mn_img_yn;
    private final String prd_dtl_cd;
    private final String arl_nm;
    private final boolean dpr_yn;
    private final boolean rsvt_yn;

}
