package com.devcamp.eztour.domain.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Trv_prd_prc_dto {
    private int prd_prc_no;
    private String prd_dtl_cd;
    private String prd_cd;
    private int adt_prc;
    private int chd_prc;
    private int bb_prc;
    private String frs_reg_date;
    private String frs_rgs_no;
    private String fnl_mod_date;
    private String fnl_mod_no;

    public Trv_prd_prc_dto(String prd_dtl_cd, String prd_cd, int adt_prc, int chd_prc, int bb_prc) {
        this.prd_dtl_cd = prd_dtl_cd;
        this.adt_prc = adt_prc;
        this.chd_prc = chd_prc;
        this.bb_prc = bb_prc;
        this.prd_cd = prd_cd;
    }
}
