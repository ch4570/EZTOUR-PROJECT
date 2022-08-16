package com.devcamp.eztour.domain.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrvPrdDtlReadDto {

    private String prd_dtl_cd;
    private String prd_cd;
    private int	    prd_str_prc;
    private String	arl_nm;
    private boolean	dpr_yn;
    private boolean rsvt_yn;
    private int		pr_rsvt_cnt;
    private int		min_stt_cnt;
    private int		max_stt_cnt;
    private String	dpr_date;
    private String	frs_reg_date;
    private String	frs_rgs_no;
    private String  fnl_mod_date;
    private String	fnl_mod_no;
    private String prd_nm;

    public TrvPrdDtlReadDto(String prd_dtl_cd, String prd_cd, String arl_nm, int prd_str_prc, String dpr_date, String prd_nm) {
        this.prd_dtl_cd = prd_dtl_cd;
        this.prd_cd = prd_cd;
        this.prd_str_prc = prd_str_prc;
        this.arl_nm = arl_nm;
        this.dpr_date = dpr_date;
        this.prd_nm = prd_nm;
    }

    public TrvPrdDtlReadDto(String prd_dtl_cd, String prd_cd, int prd_str_prc, String arl_nm, int min_stt_cnt, int max_stt_cnt, String dpr_date, String prd_nm) {
        this.prd_dtl_cd = prd_dtl_cd;
        this.prd_cd = prd_cd;
        this.prd_str_prc = prd_str_prc;
        this.arl_nm = arl_nm;
        this.min_stt_cnt = min_stt_cnt;
        this.max_stt_cnt = max_stt_cnt;
        this.dpr_date = dpr_date;
        this.prd_nm = prd_nm;
    }
}
