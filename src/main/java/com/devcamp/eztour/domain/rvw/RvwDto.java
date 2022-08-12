package com.devcamp.eztour.domain.rvw;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
public class RvwDto {
    private int rvw_no;
    private String usr_id;
    private String prd_dtl_cd;
    private String rvw_ttl;
    private String trv_nm;
    private int rvw_vcnt;
    private String rvw_cont;
    private Date rvw_reg_date;
    private Date rvw_mdf_date;
    private String wrt_nm;
    private String wrt_email;
    private int lk_cnt;
    private Date frs_reg_date;
    private String frs_rgs_no;
    private Date fnl_mod_date;
    private String fnl_mod_no;


    public RvwDto() {}
    public RvwDto(String wrt_email,String rvw_ttl, String rvw_cont, String wrt_nm,String trv_nm) {
        this.rvw_ttl = rvw_ttl;
        this.rvw_cont = rvw_cont;
        this.wrt_nm = wrt_nm;
        this.wrt_email = wrt_email;
        this.trv_nm = trv_nm;
    }

    public RvwDto(String rvw_ttl, String rvw_cont) {
        this.rvw_ttl = rvw_ttl;
        this.rvw_cont = rvw_cont;
    }

}
