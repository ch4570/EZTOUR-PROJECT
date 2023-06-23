package com.devcamp.eztour.domain.reserv;

import java.util.Date;

public class TravelerInfoDto {
    public int trvlr_inf_no;
    private String rsvt_no;
    private String trvlr_nm;
    private String trvlr_en_nm;
    private String cmn_cd_age;
    private long pay_ftr_prc;

    public TravelerInfoDto(){}
    public TravelerInfoDto(String rsvt_no, String trvlr_nm, String trvlr_en_nm, String cmn_cd_age, long pay_ftr_prc) {
        this.rsvt_no = rsvt_no;
        this.trvlr_nm = trvlr_nm;
        this.trvlr_en_nm = trvlr_en_nm;
        this.cmn_cd_age = cmn_cd_age;
        this.pay_ftr_prc = pay_ftr_prc;
    }

    public TravelerInfoDto(String rsvt_no, String trvlr_nm, String cmn_cd_age, long pay_ftr_prc) {
        this.rsvt_no = rsvt_no;
        this.trvlr_nm = trvlr_nm;
        this.cmn_cd_age = cmn_cd_age;
        this.pay_ftr_prc = pay_ftr_prc;
    }

    @Override
    public String toString() {
        return "TravelerInfoDto{" +
                "trvlr_inf_no=" + trvlr_inf_no +
                ", rsvt_no='" + rsvt_no + '\'' +
                ", trvlr_nm='" + trvlr_nm + '\'' +
                ", trvlr_en_nm='" + trvlr_en_nm + '\'' +
                ", cmn_cd_age='" + cmn_cd_age + '\'' +
                ", pay_ftr_prc=" + pay_ftr_prc +
                '}';
    }

    public int getTrvlr_inf_no() {
        return trvlr_inf_no;
    }

    public void setTrvlr_inf_no(int trvlr_inf_no) {
        this.trvlr_inf_no = trvlr_inf_no;
    }

    public String getRsvt_no() {
        return rsvt_no;
    }

    public void setRsvt_no(String rsvt_no) {
        this.rsvt_no = rsvt_no;
    }

    public String getTrvlr_nm() {
        return trvlr_nm;
    }

    public void setTrvlr_nm(String trvlr_nm) {
        this.trvlr_nm = trvlr_nm;
    }

    public String getTrvlr_en_nm() {
        return trvlr_en_nm;
    }

    public void setTrvlr_en_nm(String trvlr_en_nm) {
        this.trvlr_en_nm = trvlr_en_nm;
    }

    public String getCmn_cd_age() {
        return cmn_cd_age;
    }

    public void setCmn_cd_age(String cmn_cd_age) {
        this.cmn_cd_age = cmn_cd_age;
    }

    public long getPay_ftr_prc() {
        return pay_ftr_prc;
    }

    public void setPay_ftr_prc(long pay_ftr_prc) {
        this.pay_ftr_prc = pay_ftr_prc;
    }
}
