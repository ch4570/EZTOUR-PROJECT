package com.devcamp.eztour.domain.product;


import lombok.*;

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
    private String dpr_str_date;
    private String dpr_fin_date;
    private String arl_nm;
    private Integer pr_rsvt_cnt;
    private Integer min_stt_cnt;
    private Integer max_stt_cnt;
    private String loc_dpr_date;
    private String loc_fin_date;
    private String prd_dtl_cd;
    private Integer adt_prc;
    private Integer chd_prc;
    private Integer bb_prc;
    private String img_pth;
    private Integer mn_img_yn;
    private String dom_dpr_date;
    private String dom_fin_date;

}
