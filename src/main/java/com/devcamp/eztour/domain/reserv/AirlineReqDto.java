package com.devcamp.eztour.domain.reserv;

import java.util.Date;

public class AirlineReqDto {
    private int arl_req_cd;
    private String dpr_arl_id; //출발공항 아이디
    private String arr_arl_id; //도착공항아이디
    private Date dpr_date; //출발일
    private String arl_id; //항공사아이디
    private String arl_stt; //출항상태
    private String dpr_tm;
    private String arr_tm;
    private String arl_nm;

    public AirlineReqDto(){}
    public AirlineReqDto(int arl_req_cd, String dpr_arl_id, String arr_arl_id, Date dpr_date, String arl_id,
                         String arl_stt, String dpr_tm, String arr_tm, String arl_nm) {
        this.arl_req_cd = arl_req_cd;
        this.dpr_arl_id = dpr_arl_id;
        this.arr_arl_id = arr_arl_id;
        this.dpr_date = dpr_date;
        this.arl_id = arl_id;
        this.arl_stt = arl_stt;
        this.dpr_tm = dpr_tm;
        this.arr_tm = arr_tm;
        this.arl_nm = arl_nm;
    }

    @Override
    public String toString() {
        return "AirlineReqDto{" +
                "arl_req_cd=" + arl_req_cd +
                ", dpr_arl_id='" + dpr_arl_id + '\'' +
                ", arr_arl_id='" + arr_arl_id + '\'' +
                ", dpr_date=" + dpr_date +
                ", arl_id='" + arl_id + '\'' +
                ", arl_stt='" + arl_stt + '\'' +
                ", dpr_tm='" + dpr_tm + '\'' +
                ", arr_tm='" + arr_tm + '\'' +
                ", arl_nm='" + arl_nm + '\'' +
                '}';
    }

    public int getArl_req_cd() {
        return arl_req_cd;
    }

    public void setArl_req_cd(int arl_req_cd) {
        this.arl_req_cd = arl_req_cd;
    }

    public String getDpr_arl_id() {
        return dpr_arl_id;
    }

    public void setDpr_arl_id(String dpr_arl_id) {
        this.dpr_arl_id = dpr_arl_id;
    }

    public String getArr_arl_id() {
        return arr_arl_id;
    }

    public void setArr_arl_id(String arr_arl_id) {
        this.arr_arl_id = arr_arl_id;
    }

    public Date getDpr_date() {
        return dpr_date;
    }

    public void setDpr_date(Date dpr_date) {
        this.dpr_date = dpr_date;
    }

    public String getArl_id() {
        return arl_id;
    }

    public void setArl_id(String arl_id) {
        this.arl_id = arl_id;
    }

    public String getArl_stt() {
        return arl_stt;
    }

    public void setArl_stt(String arl_stt) {
        this.arl_stt = arl_stt;
    }

    public String getDpr_tm() {
        return dpr_tm;
    }

    public void setDpr_tm(String dpr_tm) {
        this.dpr_tm = dpr_tm;
    }

    public String getArr_tm() {
        return arr_tm;
    }

    public void setArr_tm(String arr_tm) {
        this.arr_tm = arr_tm;
    }

    public String getArl_nm() {
        return arl_nm;
    }

    public void setArl_nm(String arl_nm) {
        this.arl_nm = arl_nm;
    }
}
