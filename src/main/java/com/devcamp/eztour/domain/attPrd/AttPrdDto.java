package com.devcamp.eztour.domain.attPrd;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class AttPrdDto {
    private Integer att_prd_no;
    private String usr_id;
    private String prd_dtl_cd;
    private String prd_nm;
    private boolean lk_yn;
    private Integer prd_str_prc;
    private Date frs_reg_date;
    private String frs_rgs_no;
    private Date fnl_mod_date;
    private String fnl_mod_no;

    private String img_pth;

    public AttPrdDto() {}

    public AttPrdDto(Integer att_prd_no, String usr_id, String prd_dtl_cd, String prd_nm, boolean lk_yn, Integer prd_str_prc, Date frs_reg_date, String frs_rgs_no, Date fnl_mod_date, String fnl_mod_no) {
        this.att_prd_no = att_prd_no;
        this.usr_id = usr_id;
        this.prd_dtl_cd = prd_dtl_cd;
        this.prd_nm = prd_nm;
        this.lk_yn = lk_yn;
        this.prd_str_prc = prd_str_prc;
        this.frs_reg_date = frs_reg_date;
        this.frs_rgs_no = frs_rgs_no;
        this.fnl_mod_date = fnl_mod_date;
        this.fnl_mod_no = fnl_mod_no;
    }
}
