package com.devcamp.eztour.domain.reserv;

public class ReservInfoDto {
    private String prd_dtl_cd;
    private String prd_cd;
    private String dstn_cd; //여행지코드
    private String prd_nm;
    private String arl_nm;
    private String trv_per; //여행기간
    //일정
    private String go_dpr_tm; //한국-해외 한국 출발 시간
    private String go_dpr_arl_id;//한국-해외 출발하는 비행기 id
    private String go_arr_tm; //한국-해외현지 도착 시간
    private String go_arr_arl_id; //한국-해외 도착하는 비행기 id(경유가능성)

    private String cb_dpr_tm; //오는 비행기 현지 출발시간
    private String cb_dpr_arl_id; //해외-한국 출발하는 비행기id
    private String cb_arr_tm; //오는 비행기 한국 도착 시간
    private String cb_arr_arl_id; //해외-한국 도착하는 비행기 id(경유가능성)

    //요금
    private int adt_prc;
    private int chd_prc;
    private int bb_prc;
    //사용자 정보
    private String usr_nm;
    private String phn;
    private String email;

    public ReservInfoDto(){}
    public ReservInfoDto(String prd_dtl_cd, String prd_cd, String dstn_cd, String prd_nm, String arl_nm,
                         String trv_per, String go_dpr_tm, String go_dpr_arl_id, String go_arr_tm, String go_arr_arl_id,
                         String cb_dpr_tm, String cb_dpr_arl_id, String cb_arr_tm, String cb_arr_arl_id, int adt_prc,
                         int chd_prc, int bb_prc, String usr_nm, String phn, String email) {
        this.prd_dtl_cd = prd_dtl_cd;
        this.prd_cd = prd_cd;
        this.dstn_cd = dstn_cd;
        this.prd_nm = prd_nm;
        this.arl_nm = arl_nm;
        this.trv_per = trv_per;
        this.go_dpr_tm = go_dpr_tm;
        this.go_dpr_arl_id = go_dpr_arl_id;
        this.go_arr_tm = go_arr_tm;
        this.go_arr_arl_id = go_arr_arl_id;
        this.cb_dpr_tm = cb_dpr_tm;
        this.cb_dpr_arl_id = cb_dpr_arl_id;
        this.cb_arr_tm = cb_arr_tm;
        this.cb_arr_arl_id = cb_arr_arl_id;
        this.adt_prc = adt_prc;
        this.chd_prc = chd_prc;
        this.bb_prc = bb_prc;
        this.usr_nm = usr_nm;
        this.phn = phn;
        this.email = email;
    }

    @Override
    public String toString() {
        return "ReservInfoDto{" +
                "prd_dtl_cd='" + prd_dtl_cd + '\'' +
                ", prd_cd='" + prd_cd + '\'' +
                ", dstn_cd='" + dstn_cd + '\'' +
                ", prd_nm='" + prd_nm + '\'' +
                ", arl_nm='" + arl_nm + '\'' +
                ", trv_per='" + trv_per + '\'' +
                ", go_dpr_tm='" + go_dpr_tm + '\'' +
                ", go_dpr_arl_id='" + go_dpr_arl_id + '\'' +
                ", go_arr_tm='" + go_arr_tm + '\'' +
                ", go_arr_arl_id='" + go_arr_arl_id + '\'' +
                ", cb_dpr_tm='" + cb_dpr_tm + '\'' +
                ", cb_dpr_arl_id='" + cb_dpr_arl_id + '\'' +
                ", cb_arr_tm='" + cb_arr_tm + '\'' +
                ", cb_arr_arl_id='" + cb_arr_arl_id + '\'' +
                ", adt_prc=" + adt_prc +
                ", chd_prc=" + chd_prc +
                ", bb_prc=" + bb_prc +
                ", usr_nm='" + usr_nm + '\'' +
                ", phn='" + phn + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getPrd_dtl_cd() {
        return prd_dtl_cd;
    }

    public void setPrd_dtl_cd(String prd_dtl_cd) {
        this.prd_dtl_cd = prd_dtl_cd;
    }

    public String getPrd_cd() {
        return prd_cd;
    }

    public void setPrd_cd(String prd_cd) {
        this.prd_cd = prd_cd;
    }

    public String getDstn_cd() {
        return dstn_cd;
    }

    public void setDstn_cd(String dstn_cd) {
        this.dstn_cd = dstn_cd;
    }

    public String getPrd_nm() {
        return prd_nm;
    }

    public void setPrd_nm(String prd_nm) {
        this.prd_nm = prd_nm;
    }

    public String getArl_nm() {
        return arl_nm;
    }

    public void setArl_nm(String arl_nm) {
        this.arl_nm = arl_nm;
    }

    public String getTrv_per() {
        return trv_per;
    }

    public void setTrv_per(String trv_per) {
        this.trv_per = trv_per;
    }

    public String getGo_dpr_tm() {
        return go_dpr_tm;
    }

    public void setGo_dpr_tm(String go_dpr_tm) {
        this.go_dpr_tm = go_dpr_tm;
    }

    public String getGo_dpr_arl_id() {
        return go_dpr_arl_id;
    }

    public void setGo_dpr_arl_id(String go_dpr_arl_id) {
        this.go_dpr_arl_id = go_dpr_arl_id;
    }

    public String getGo_arr_tm() {
        return go_arr_tm;
    }

    public void setGo_arr_tm(String go_arr_tm) {
        this.go_arr_tm = go_arr_tm;
    }

    public String getGo_arr_arl_id() {
        return go_arr_arl_id;
    }

    public void setGo_arr_arl_id(String go_arr_arl_id) {
        this.go_arr_arl_id = go_arr_arl_id;
    }

    public String getCb_dpr_tm() {
        return cb_dpr_tm;
    }

    public void setCb_dpr_tm(String cb_dpr_tm) {
        this.cb_dpr_tm = cb_dpr_tm;
    }

    public String getCb_dpr_arl_id() {
        return cb_dpr_arl_id;
    }

    public void setCb_dpr_arl_id(String cb_dpr_arl_id) {
        this.cb_dpr_arl_id = cb_dpr_arl_id;
    }

    public String getCb_arr_tm() {
        return cb_arr_tm;
    }

    public void setCb_arr_tm(String cb_arr_tm) {
        this.cb_arr_tm = cb_arr_tm;
    }

    public String getCb_arr_arl_id() {
        return cb_arr_arl_id;
    }

    public void setCb_arr_arl_id(String cb_arr_arl_id) {
        this.cb_arr_arl_id = cb_arr_arl_id;
    }

    public int getAdt_prc() {
        return adt_prc;
    }

    public void setAdt_prc(int adt_prc) {
        this.adt_prc = adt_prc;
    }

    public int getChd_prc() {
        return chd_prc;
    }

    public void setChd_prc(int chd_prc) {
        this.chd_prc = chd_prc;
    }

    public int getBb_prc() {
        return bb_prc;
    }

    public void setBb_prc(int bb_prc) {
        this.bb_prc = bb_prc;
    }

    public String getUsr_nm() {
        return usr_nm;
    }

    public void setUsr_nm(String usr_nm) {
        this.usr_nm = usr_nm;
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
}
