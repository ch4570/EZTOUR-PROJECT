package com.devcamp.eztour.domain.productDetail;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class TrvPrdDetailDto {

    private final String prd_cd;
    private final String dstn_cd;
    private String cmn_cd_thm;
    private final String prd_nm;
    private final String prd_dtl_desc;
    private final String trv_per;
    private final Integer prd_str_prc;
    private final Date dpr_str_date;
    private final Date dpr_fin_date;
    private boolean evnt_yn;
    private boolean fin_yn;
    private boolean act_yn;
    private boolean dc_yn;
    private Integer vcnt;
    private boolean lk_yn;
    private final String img_pth;
    private boolean mn_img_yn;
    private final String prd_dtl_cd;
    private final String arl_nm;
    private boolean dpr_yn;
    private boolean rsvt_yn;

}
