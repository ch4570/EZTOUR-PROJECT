package com.devcamp.eztour.domain.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrvPrdPrcDto {
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

    public TrvPrdPrcDto(int prd_prc_no, String prd_dtl_cd, int adt_prc, int chd_prc, int bb_prc, String frs_reg_date) {
        this.prd_prc_no = prd_prc_no;
        this.prd_dtl_cd = prd_dtl_cd;
        this.adt_prc = adt_prc;
        this.chd_prc = chd_prc;
        this.bb_prc = bb_prc;
        this.frs_reg_date = frs_reg_date;
    }
}
