package com.devcamp.eztour.domain.rvw;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class RvwLkAdmDto {
    private Integer rvw_lk_adm_no;
    private Integer rvw_no;
    private String usr_id;
    private boolean rvw_lk_yn;
    private Date lk_reg_date;
    private Date frs_reg_date;
    private String frs_rgs_no;
    private Date fnl_mod_date;
    private String fnl_mod_no;


    public RvwLkAdmDto() {}

    public RvwLkAdmDto(Integer rvw_lk_adm_no, Integer rvw_no, String usr_id, boolean rvw_lk_yn) {
        this.rvw_lk_adm_no = rvw_lk_adm_no;
        this.rvw_no = rvw_no;
        this.usr_id = usr_id;
        this.rvw_lk_yn = rvw_lk_yn;
    }
}
