package com.devcamp.eztour.domain.reserv;

import java.util.Date;

public class ReservConfInfoDto {
    //예약 확인 페이지 정보를 보여주기 위한 Dto
    private Date rsvt_date; //예약일
    private String rsvt_no; //예약번호
    private String prd_dtl_cd; //상품상세번호
    private int adt_cnt; //성인수
    private int chd_cnt; //아동수
    private int bb_cnt; //유아수
    private long sum_prc; //총금액
    private String cmn_cd_rsvt_stt; //예약상태
    private String cmn_cd_pay_stt;
    private String pay_ftr_prc; //결제예정금액
    private String prd_nm; //상품이름
    private String prd_cd; //상품코드
    private String trv_per; //여행기간

    //일정
    private String go_dpr_tm; //한국-해외 한국 출발 시간
    private String go_dpr_arl_id;//한국-해외 출발하는 비행기 id
    private String cb_arr_tm; //오는 비행기 한국 도착 시간
    private String cb_arr_arl_id; //해외-한국 도착하는 비행기 id(경유가능성)

    public ReservConfInfoDto(){}
    public ReservConfInfoDto(Date rsvt_date, String rsvt_no, String prd_dtl_cd, int adt_cnt, int chd_cnt, int bb_cnt,
                             long sum_prc, String cmn_cd_rsvt_stt, String cmn_cd_pay_stt, String pay_ftr_prc,
                             String prd_nm, String prd_cd, String trv_per, String go_dpr_tm, String go_dpr_arl_id,
                             String cb_arr_tm, String cb_arr_arl_id) {
        this.rsvt_date = rsvt_date;
        this.rsvt_no = rsvt_no;
        this.prd_dtl_cd = prd_dtl_cd;
        this.adt_cnt = adt_cnt;
        this.chd_cnt = chd_cnt;
        this.bb_cnt = bb_cnt;
        this.sum_prc = sum_prc;
        this.cmn_cd_rsvt_stt = cmn_cd_rsvt_stt;
        this.cmn_cd_pay_stt = cmn_cd_pay_stt;
        this.pay_ftr_prc = pay_ftr_prc;
        this.prd_nm = prd_nm;
        this.prd_cd = prd_cd;
        this.trv_per = trv_per;
        this.go_dpr_tm = go_dpr_tm;
        this.go_dpr_arl_id = go_dpr_arl_id;
        this.cb_arr_tm = cb_arr_tm;
        this.cb_arr_arl_id = cb_arr_arl_id;
    }

    @Override
    public String toString() {
        return "ReservConfInfoDto{" +
                "rsvt_date=" + rsvt_date +
                ", rsvt_no='" + rsvt_no + '\'' +
                ", prd_dtl_cd='" + prd_dtl_cd + '\'' +
                ", adt_cnt=" + adt_cnt +
                ", chd_cnt=" + chd_cnt +
                ", bb_cnt=" + bb_cnt +
                ", sum_prc=" + sum_prc +
                ", cmn_cd_rsvt_stt='" + cmn_cd_rsvt_stt + '\'' +
                ", cmn_cd_pay_stt='" + cmn_cd_pay_stt + '\'' +
                ", pay_ftr_prc='" + pay_ftr_prc + '\'' +
                ", prd_nm='" + prd_nm + '\'' +
                ", prd_cd='" + prd_cd + '\'' +
                ", trv_per='" + trv_per + '\'' +
                ", go_dpr_tm='" + go_dpr_tm + '\'' +
                ", go_dpr_arl_id='" + go_dpr_arl_id + '\'' +
                ", cb_arr_tm='" + cb_arr_tm + '\'' +
                ", cb_arr_arl_id='" + cb_arr_arl_id + '\'' +
                '}';
    }

    public Date getRsvt_date() {
        return rsvt_date;
    }

    public void setRsvt_date(Date rsvt_date) {
        this.rsvt_date = rsvt_date;
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

    public int getAdt_cnt() {
        return adt_cnt;
    }

    public void setAdt_cnt(int adt_cnt) {
        this.adt_cnt = adt_cnt;
    }

    public int getChd_cnt() {
        return chd_cnt;
    }

    public void setChd_cnt(int chd_cnt) {
        this.chd_cnt = chd_cnt;
    }

    public int getBb_cnt() {
        return bb_cnt;
    }

    public void setBb_cnt(int bb_cnt) {
        this.bb_cnt = bb_cnt;
    }

    public long getSum_prc() {
        return sum_prc;
    }

    public void setSum_prc(long sum_prc) {
        this.sum_prc = sum_prc;
    }

    public String getCmn_cd_rsvt_stt() {
        return cmn_cd_rsvt_stt;
    }

    public void setCmn_cd_rsvt_stt(String cmn_cd_rsvt_stt) {
        this.cmn_cd_rsvt_stt = cmn_cd_rsvt_stt;
    }

    public String getCmn_cd_pay_stt() {
        return cmn_cd_pay_stt;
    }

    public void setCmn_cd_pay_stt(String cmn_cd_pay_stt) {
        this.cmn_cd_pay_stt = cmn_cd_pay_stt;
    }

    public String getPay_ftr_prc() {
        return pay_ftr_prc;
    }

    public void setPay_ftr_prc(String pay_ftr_prc) {
        this.pay_ftr_prc = pay_ftr_prc;
    }

    public String getPrd_nm() {
        return prd_nm;
    }

    public void setPrd_nm(String prd_nm) {
        this.prd_nm = prd_nm;
    }

    public String getPrd_cd() {
        return prd_cd;
    }

    public void setPrd_cd(String prd_cd) {
        this.prd_cd = prd_cd;
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
}
