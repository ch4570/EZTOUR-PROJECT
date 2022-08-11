package com.devcamp.eztour.domain.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Trv_prd_dtl_dto {

    private String prd_dtl_cd;
    private String prd_cd;
    private int	    prd_str_prc;
    private String	arl_nm;
    private boolean	dpr_yn;
    private boolean rsvt_yn;
    private int		pr_rsvt_cnt;
    private int		min_stt_cnt;
    private int		max_stt_cnt;
    private Date	dpr_date;
    private Date	frs_reg_date;
    private String	frs_rgs_no;
    private Date    fnl_mod_date;
    private String	fnl_mod_no;

    public Trv_prd_dtl_dto(String prd_dtl_cd, String prd_cd, int prd_str_prc, String arl_nm,
                           int min_stt_cnt, int max_stt_cnt, Date frs_reg_date, String frs_rgs_no, String fnl_mod_no) {
        this.prd_dtl_cd = prd_dtl_cd;
        this.prd_cd = prd_cd;
        this.prd_str_prc = prd_str_prc;
        this.arl_nm = arl_nm;
        this.min_stt_cnt = min_stt_cnt;
        this.max_stt_cnt = max_stt_cnt;
        this.frs_reg_date = frs_reg_date;
        this.frs_rgs_no = frs_rgs_no;
        this.fnl_mod_no = fnl_mod_no;
    }
}
