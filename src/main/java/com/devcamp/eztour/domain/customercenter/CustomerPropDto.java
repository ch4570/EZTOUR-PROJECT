package com.devcamp.eztour.domain.customercenter;

import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class CustomerPropDto {
    private int prop_no;
    private String prd_dtl_cd;
    private String usr_id;
    private String prop_cont;
    private String phn;
    private String email;
    private Date reg_date;
    private Date frs_reg_date;
    private String frs_reg_no;
    private Date fnl_mod_date;
    private String fnl_mod_no;

    public CustomerPropDto() {
    }

    public CustomerPropDto(String prd_dtl_cd, String usr_id, String prop_cont, String phn, String email) {
        this.prd_dtl_cd = prd_dtl_cd;
        this.usr_id = usr_id;
        this.prop_cont = prop_cont;
        this.phn = phn;
        this.email = email;
    }

    public int getProp_no() {
        return prop_no;
    }

    public void setProp_no(int prop_no) {
        this.prop_no = prop_no;
    }

    public String getPrd_dtl_cd() {
        return prd_dtl_cd;
    }

    public void setPrd_dtl_cd(String prd_dtl_cd) {
        this.prd_dtl_cd = prd_dtl_cd;
    }

    public String getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(String usr_id) {
        this.usr_id = usr_id;
    }

    public String getProp_cont() {
        return prop_cont;
    }

    public void setProp_cont(String prop_cont) {
        this.prop_cont = prop_cont;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }

    public Date getFrs_reg_date() {
        return frs_reg_date;
    }

    public void setFrs_reg_date(Date frs_reg_date) {
        this.frs_reg_date = frs_reg_date;
    }

    public String getFrs_reg_no() {
        return frs_reg_no;
    }

    public void setFrs_reg_no(String frs_reg_no) {
        this.frs_reg_no = frs_reg_no;
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

    @Override
    public String toString() {
        return "CustomerPropDto{" +
                "prop_no=" + prop_no +
                ", prd_dtl_cd='" + prd_dtl_cd + '\'' +
                ", usr_id='" + usr_id + '\'' +
                ", prop_cont='" + prop_cont + '\'' +
                ", phn='" + phn + '\'' +
                ", email='" + email + '\'' +
                ", reg_date=" + reg_date +
                ", frs_reg_date=" + frs_reg_date +
                ", frs_reg_no='" + frs_reg_no + '\'' +
                ", fnl_mod_date=" + fnl_mod_date +
                ", fnl_mod_no='" + fnl_mod_no + '\'' +
                '}';
    }
}
