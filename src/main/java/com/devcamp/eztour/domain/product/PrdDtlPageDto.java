package com.devcamp.eztour.domain.product;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PrdDtlPageDto {

    private String prd_cd;
    private String prd_nm;
    private String dstn_cd;
    private String trv_per;
    private Date dpr_str_date;
    private Date dpr_fin_date;
    private String arl_nm;
    private Integer pr_rsvt_cnt;
    private Integer min_stt_cnt;
    private Integer max_stt_cnt;
    private Date loc_dpr_date;
    private Date loc_fin_date;
    private String prd_dtl_cd;
    private Integer adt_prc;
    private Integer chd_prc;
    private Integer bb_prc;
    private String img_pth;
    private Integer mn_img_yn;
    private Date dom_dpr_date;
    private Date dom_fin_date;

}
