package com.devcamp.eztour.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
public class Trv_prd_dto {

    private String prd_cd;
    private String dstn_cd;
    private String cmn_cd_thm;
    private String prd_nm;
    private String prd_dtl_desc;
    private String trv_per;
    private Integer prd_str_prc;
    private String mn_img;
    private String dpr_str_date;
    private String dpr_fin_date;
    private int star;
    private boolean evnt_yn;
    private boolean fin_yn;
    private boolean act_yn;
    private boolean dc_yn;
    private int pr_prc;
    private int add_sv_rt;
    private int vcnt;
    private boolean lk_yn;
    private Date frs_reg_date;
    private String frs_rgs_no;
    private Date fnl_mod_date;
    private String fnl_mod_no;

    public Trv_prd_dto(String prd_cd, String dstn_cd, String cmn_cd_thm, String prd_nm,
                       String prd_dtl_desc, String trv_per, Integer prd_str_prc, String mn_img,
                       String dpr_str_date, String dpr_fin_date) {
        this.prd_cd = prd_cd;
        this.dstn_cd = dstn_cd;
        this.cmn_cd_thm = cmn_cd_thm;
        this.prd_nm = prd_nm;
        this.prd_dtl_desc = prd_dtl_desc;
        this.trv_per = trv_per;
        this.prd_str_prc = prd_str_prc;
        this.mn_img = mn_img;
        this.dpr_str_date = dpr_str_date;
        this.dpr_fin_date = dpr_fin_date;
    }
}
