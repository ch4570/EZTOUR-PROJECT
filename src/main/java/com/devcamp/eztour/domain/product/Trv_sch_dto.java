package com.devcamp.eztour.domain.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Trv_sch_dto {

    int 		sch_no;
    String		prd_cd;
    int			trv_date;
    int			sch_ord;
    String		st_nm;
    String		sit_sh_desc;
    String		sit_lo_desc;
    String		ht_inf;
    String		brk;
    String		luh;
    String		din;
    String		dstnc_tm;
    Date		frs_reg_date;
    String 		frs_rgs_no;
    Date        fnl_mod_date;
    String		fnl_mod_no;

    public Trv_sch_dto(int sch_no, String prd_cd, int trv_date, int sch_ord,
                       String st_nm, String sit_sh_desc, String sit_lo_desc, String ht_inf,
                       String brk, String luh, String din, String dstnc_tm, Date frs_reg_date,
                       String frs_rgs_no, String fnl_mod_no) {
        this.sch_no = sch_no;
        this.prd_cd = prd_cd;
        this.trv_date = trv_date;
        this.sch_ord = sch_ord;
        this.st_nm = st_nm;
        this.sit_sh_desc = sit_sh_desc;
        this.sit_lo_desc = sit_lo_desc;
        this.ht_inf = ht_inf;
        this.brk = brk;
        this.luh = luh;
        this.din = din;
        this.dstnc_tm = dstnc_tm;
        this.frs_reg_date = frs_reg_date;
        this.frs_rgs_no = frs_rgs_no;
        this.fnl_mod_no = fnl_mod_no;
    }
}
