package com.devcamp.eztour.domain.reserv;

import java.util.Date;

public class PayDto {
    private int pay_no; //결제번호
    private String rsvt_no; //예약번호
    private String prd_dtl_cd; //상품상세번호
    private String usr_id;
    private long pay_prc; //지불금액
    private Date pay_date; //지불날짜
    private String cmn_cd_pay_appr; //결제승인코드
    private String cmn_cd_pay_stt; //결제상태코드
    private String pay_mthd;
    private int dvd_mnt = 0;
    private int used_mlg;

    public PayDto(){}
    public PayDto(String rsvt_no, String prd_dtl_cd, String usr_id, long pay_prc, Date pay_date,
                  String cmn_cd_pay_appr, String cmn_cd_pay_stt, String pay_mthd, int used_mlg) {
        this.rsvt_no = rsvt_no;
        this.prd_dtl_cd = prd_dtl_cd;
        this.usr_id = usr_id;
        this.pay_prc = pay_prc;
        this.pay_date = pay_date;
        this.cmn_cd_pay_appr = cmn_cd_pay_appr;
        this.cmn_cd_pay_stt = cmn_cd_pay_stt;
        this.pay_mthd = pay_mthd;
        this.used_mlg = used_mlg;
    }

    @Override
    public String toString() {
        return "PayDto{" +
                "pay_no=" + pay_no +
                ", rsvt_no='" + rsvt_no + '\'' +
                ", prd_dtl_cd='" + prd_dtl_cd + '\'' +
                ", usr_id='" + usr_id + '\'' +
                ", pay_prc=" + pay_prc +
                ", pay_date=" + pay_date +
                ", cmn_cd_pay_appr='" + cmn_cd_pay_appr + '\'' +
                ", cmn_cd_pay_stt='" + cmn_cd_pay_stt + '\'' +
                ", pay_mthd='" + pay_mthd + '\'' +
                ", dvd_mnt=" + dvd_mnt +
                ", used_mlg=" + used_mlg +
                '}';
    }

    public int getPay_no() {
        return pay_no;
    }

    public void setPay_no(int pay_no) {
        this.pay_no = pay_no;
    }

    public String getRsvt_no() {
        return rsvt_no;
    }

    public void setRsvt_no(String rsvt_no) {
        this.rsvt_no = rsvt_no;
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

    public long getPay_prc() {
        return pay_prc;
    }

    public void setPay_prc(long pay_prc) {
        this.pay_prc = pay_prc;
    }

    public Date getPay_date() {
        return pay_date;
    }

    public void setPay_date(Date pay_date) {
        this.pay_date = pay_date;
    }

    public String getCmn_cd_pay_appr() {
        return cmn_cd_pay_appr;
    }

    public void setCmn_cd_pay_appr(String cmn_cd_pay_appr) {
        this.cmn_cd_pay_appr = cmn_cd_pay_appr;
    }

    public String getCmn_cd_pay_stt() {
        return cmn_cd_pay_stt;
    }

    public void setCmn_cd_pay_stt(String cmn_cd_pay_stt) {
        this.cmn_cd_pay_stt = cmn_cd_pay_stt;
    }

    public String getPay_mthd() {
        return pay_mthd;
    }

    public void setPay_mthd(String pay_mthd) {
        this.pay_mthd = pay_mthd;
    }

    public int getDvd_mnt() {
        return dvd_mnt;
    }

    public void setDvd_mnt(int dvd_mnt) {
        this.dvd_mnt = dvd_mnt;
    }

    public int getUsed_mlg() {
        return used_mlg;
    }

    public void setUsed_mlg(int used_mlg) {
        this.used_mlg = used_mlg;
    }
}
