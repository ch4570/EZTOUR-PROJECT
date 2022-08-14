package com.devcamp.eztour.domain.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class TrvSchDto {

    private int sch_no;
    private String		prd_cd;
    private int			trv_date;
    private int			sch_ord;
    private String		st_nm;
    private String		sit_sh_desc;
    private String		sit_lo_desc;
    private String		ht_inf;
    private String		brk;
    private String		luh;
    private String		din;
    private String		dstnc_tm;
    private String		frs_reg_date;
    private String 		frs_rgs_no;
    private String		fnl_mod_no;

}
