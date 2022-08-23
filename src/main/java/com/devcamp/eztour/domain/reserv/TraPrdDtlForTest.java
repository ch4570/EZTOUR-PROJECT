package com.devcamp.eztour.domain.reserv;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TraPrdDtlForTest {
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
    private Date frs_reg_date;
    private String	frs_rgs_no;
    private Date  fnl_mod_date;
    private String	fnl_mod_no;
}
