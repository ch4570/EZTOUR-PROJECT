package com.devcamp.eztour.domain.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Prd_pcr_dto {
    int 		prd_prc_no;
    String		prd_dtl_cd;
    int			adt_prc;
    int			chd_prc;
    int			bb_prc;
    Date        frs_reg_date;
    String		frs_rgs_no;
    Date		fnl_mod_date;
    String		fnl_mod_no;


    public Prd_pcr_dto(int prd_prc_no, String prd_dtl_cd, int adt_prc, int chd_prc, int bb_prc,
                       Date frs_reg_date, String frs_rgs_no, String fnl_mod_no) {
        this.prd_prc_no = prd_prc_no;
        this.prd_dtl_cd = prd_dtl_cd;
        this.adt_prc = adt_prc;
        this.chd_prc = chd_prc;
        this.bb_prc = bb_prc;
        this.frs_reg_date = frs_reg_date;
        this.frs_rgs_no = frs_rgs_no;
        this.fnl_mod_no = fnl_mod_no;
    }
}
