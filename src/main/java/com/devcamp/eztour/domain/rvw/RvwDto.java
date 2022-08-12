package com.devcamp.eztour.domain.rvw;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Objects;

public class RvwDto {
    private int rvw_no;
    private String usr_id;
    private String prd_dtl_cd;
    private String rvw_ttl;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RvwDto rvwDto = (RvwDto) o;
        return rvw_no == rvwDto.rvw_no && Objects.equals(usr_id, rvwDto.usr_id) && Objects.equals(prd_dtl_cd, rvwDto.prd_dtl_cd) && Objects.equals(rvw_ttl, rvwDto.rvw_ttl) && Objects.equals(rvw_cont, rvwDto.rvw_cont) && Objects.equals(wrt_nm, rvwDto.wrt_nm) && Objects.equals(wrt_email, rvwDto.wrt_email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rvw_no, usr_id, prd_dtl_cd, rvw_ttl, rvw_cont, wrt_nm, wrt_email);
    }

    public RvwDto() {}
    public RvwDto(String rvw_ttl, String rvw_cont, String wrt_nm) {
        this.rvw_ttl = rvw_ttl;
        this.rvw_cont = rvw_cont;
        this.wrt_nm = wrt_nm;
    }

    public int getRvw_vcnt() {
        return rvw_vcnt;
    }

    public void setRvw_vcnt(int rvw_vcnt) {
        this.rvw_vcnt = rvw_vcnt;
    }

    public int getRvw_no() {
        return rvw_no;
    }

    public void setRvw_no(int rvw_no) {
        this.rvw_no = rvw_no;
    }

    public String getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(String usr_id) {
        this.usr_id = usr_id;
    }

    public String getPrd_dtl_cd() {
        return prd_dtl_cd;
    }

    public void setPrd_dtl_cd(String prd_dtl_cd) {
        this.prd_dtl_cd = prd_dtl_cd;
    }

    public String getRvw_ttl() {
        return rvw_ttl;
    }

    public void setRvw_ttl(String rvw_ttl) {
        this.rvw_ttl = rvw_ttl;
    }

    public String getRvw_cont() {
        return rvw_cont;
    }

    public void setRvw_cont(String rvw_cont) {
        this.rvw_cont = rvw_cont;
    }

    public Date getRvw_reg_date() {
        return rvw_reg_date;
    }

    public void setRvw_reg_date(Date rvw_reg_date) {
        this.rvw_reg_date = rvw_reg_date;
    }

    public Date getRvw_mdf_date() {
        return rvw_mdf_date;
    }

    public void setRvw_mdf_date(Date rvw_mdf_date) {
        this.rvw_mdf_date = rvw_mdf_date;
    }

    public String getWrt_nm() {
        return wrt_nm;
    }

    public void setWrt_nm(String wrt_nm) {
        this.wrt_nm = wrt_nm;
    }

    public String getWrt_email() {
        return wrt_email;
    }

    public void setWrt_email(String wrt_email) {
        this.wrt_email = wrt_email;
    }

    public int getLk_cnt() {
        return lk_cnt;
    }

    public void setLk_cnt(int lk_cnt) {
        this.lk_cnt = lk_cnt;
    }

    public Date getFrs_reg_date() {
        return frs_reg_date;
    }

    public void setFrs_reg_date(Date frs_reg_date) {
        this.frs_reg_date = frs_reg_date;
    }

    public String getFrs_rgs_no() {
        return frs_rgs_no;
    }

    public void setFrs_rgs_no(String frs_rgs_no) {
        this.frs_rgs_no = frs_rgs_no;
    }

    public Date getFnl_mod_date() {
        return fnl_mod_date;
    }

    public void setFnl_mod_date(Date fnl_mod_date) {
        this.fnl_mod_date = fnl_mod_date;
    }

    public String getFnl_mod_no() {
        return fnl_mod_no;
    }

    public void setFnl_mod_no(String fnl_mod_no) {
        this.fnl_mod_no = fnl_mod_no;
    }
}
