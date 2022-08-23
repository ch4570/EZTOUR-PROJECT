package com.devcamp.eztour.domain.customercenter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.util.Date;

@ToString
@Getter
@Setter
public class CustomerPropDto {
    private int prop_no;
    private String prop_ttl;
    private String prop_cont;
    private String usr_id;
    private String phn;
    private String email;
    private Date reg_date;
    private String prd_dtl_cd;
    private Date frs_reg_date;
    private String frs_rgs_no;
    private Date fnl_mod_date;
    private String fnl_mod_no;

//    public CustomerPropDto() {
//    }

    public CustomerPropDto(String prop_ttl, String prop_cont) {
        this.prop_ttl = prop_ttl;
        this.prop_cont = prop_cont;
    }

    public CustomerPropDto(int prop_no, String prop_ttl, String prop_cont, String usr_id,
                           String phn, String email, Date reg_date, String prd_dtl_cd,
                           Date frs_reg_date, String frs_rgs_no, Date fnl_mod_date, String fnl_mod_no) {
        this.prop_no = prop_no;
        this.prop_ttl = prop_ttl;
        this.prop_cont = prop_cont;
        this.usr_id = usr_id;
        this.phn = phn;
        this.email = email;
        this.reg_date = reg_date;
        this.prd_dtl_cd = prd_dtl_cd;
        this.frs_reg_date = frs_reg_date;
        this.frs_rgs_no = frs_rgs_no;
        this.fnl_mod_date = fnl_mod_date;
        this.fnl_mod_no = fnl_mod_no;
    }
}
