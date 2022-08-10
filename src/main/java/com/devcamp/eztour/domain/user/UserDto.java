package com.devcamp.eztour.domain.user;

public class UserDto {

    private String usr_id;
    private String cmn_cd_prf_img;
    private String cmn_cd_usr_stt;
    private String pwd;
    private String usr_nm;
    private String brth;
    private String gndr;
    private String email;
    private String phn;
    private String rl;
    private String mlg;

    public UserDto(String usr_id, String cmn_cd_prf_img, String cmn_cd_usr_stt, String pwd, String usr_nm) {
        this.usr_id = usr_id;
        this.cmn_cd_prf_img = cmn_cd_prf_img;
        this.cmn_cd_usr_stt = cmn_cd_usr_stt;
        this.pwd = pwd;
        this.usr_nm = usr_nm;
    }

    public String getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(String usr_id) {
        this.usr_id = usr_id;
    }

    public String getCmn_cd_prf_img() {
        return cmn_cd_prf_img;
    }

    public void setCmn_cd_prf_img(String cmn_cd_prf_img) {
        this.cmn_cd_prf_img = cmn_cd_prf_img;
    }

    public String getCmn_cd_usr_stt() {
        return cmn_cd_usr_stt;
    }

    public void setCmn_cd_usr_stt(String cmn_cd_usr_stt) {
        this.cmn_cd_usr_stt = cmn_cd_usr_stt;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsr_nm() {
        return usr_nm;
    }

    public void setUsr_nm(String usr_nm) {
        this.usr_nm = usr_nm;
    }

    public String getBrth() {
        return brth;
    }

    public void setBrth(String brth) {
        this.brth = brth;
    }

    public String getGndr() {
        return gndr;
    }

    public void setGndr(String gndr) {
        this.gndr = gndr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public String getRl() {
        return rl;
    }

    public void setRl(String rl) {
        this.rl = rl;
    }

    public String getMlg() {
        return mlg;
    }

    public void setMlg(String mlg) {
        this.mlg = mlg;
    }
}
