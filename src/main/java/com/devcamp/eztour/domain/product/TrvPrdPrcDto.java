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

}
