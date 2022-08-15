package com.devcamp.eztour.domain.rvw;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class RvwCmtDto {
    private Integer cmt_no;
    private Integer rvw_no;
    private String usr_id;
    private Integer pcmt_no;
    private String cmt_cont;
    private String usr_nm;
    private Date reg_date;
    private Date mdf_date;
    private Date frs_reg_date;
    private String frs_reg_no;
    private Date fnl_mod_date;
    private String fnl_mod_no;

    public RvwCmtDto() {}

    public RvwCmtDto(Integer rvw_no, Integer pcmt_no, String cmt_cont, String usr_nm) {
        this.rvw_no = rvw_no;
        this.pcmt_no = pcmt_no;
        this.cmt_cont = cmt_cont;
        this.usr_nm = usr_nm;
    }

    public RvwCmtDto(Integer rvw_no, String usr_id, Integer pcmt_no, String cmt_cont, String usr_nm) {
        this.rvw_no = rvw_no;
        this.usr_id = usr_id;
        this.pcmt_no = pcmt_no;
        this.cmt_cont = cmt_cont;
        this.usr_nm = usr_nm;
    }
}
