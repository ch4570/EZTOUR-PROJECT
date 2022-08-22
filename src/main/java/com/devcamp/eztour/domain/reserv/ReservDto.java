package com.devcamp.eztour.domain.reserv;

import java.util.Date;

public class ReservDto {
    //예약정보를 저장하기 위한 Dto
    //예약 list를 불러오기위한 Dto
    private String rsvt_no; //예약번호
    private String prd_dtl_cd; //상세상품번호
    private String usr_id; //유저아이디
    private String prd_cd; //상품코드
    private String mn_rsvt_nm; //대표예약자명
    private String phn; //폰번호
    private String email; //이메일
    private long sum_prc; //합계금액
    private long pay_ftr_prc; //결제예정금액
    private String req_cont; //요청사항
    private String cmn_cd_rsvt_stt = "6A"; //예약상태
    private String cmn_cd_pay_stt = "7E"; //결제 상태
    private Date rsvt_date; //예약일
    private Integer cnc_pay_no; //결제취소번호
    private Integer adt_cnt; //성인수
    private Integer chd_cnt; //아동수
    private Integer bb_cnt; //유아수

    private String prd_nm;
    private String prd_dtl_desc;

    public ReservDto(){}
    public ReservDto(String rsvt_no, String prd_dtl_cd, String usr_id, String prd_cd, String mn_rsvt_nm, String phn, String email, long sum_prc, long pay_ftr_prc, String req_cont, String cmn_cd_rsvt_stt, String cmn_cd_pay_stt, Date rsvt_date, Integer cnc_pay_no, Integer adt_cnt, Integer chd_cnt, Integer bb_cnt) {
        this(rsvt_no, prd_dtl_cd, usr_id, prd_cd, mn_rsvt_nm, phn, email, sum_prc, pay_ftr_prc, req_cont, cmn_cd_rsvt_stt, cmn_cd_pay_stt, rsvt_date, cnc_pay_no, adt_cnt, chd_cnt, bb_cnt, null, null);
    }
    public ReservDto(String rsvt_no, String prd_dtl_cd, String usr_id, String prd_cd, String mn_rsvt_nm, String phn, String email, long sum_prc, long pay_ftr_prc, String req_cont, String cmn_cd_rsvt_stt, String cmn_cd_pay_stt, Date rsvt_date, Integer cnc_pay_no, Integer adt_cnt, Integer chd_cnt, Integer bb_cnt, String prd_nm, String prd_dtl_desc) {
        this.rsvt_no = rsvt_no;
        this.prd_dtl_cd = prd_dtl_cd;
        this.usr_id = usr_id;
        this.prd_cd = prd_cd;
        this.mn_rsvt_nm = mn_rsvt_nm;
        this.phn = phn;
        this.email = email;
        this.sum_prc = sum_prc;
        this.pay_ftr_prc = pay_ftr_prc;
        this.req_cont = req_cont;
        this.cmn_cd_rsvt_stt = cmn_cd_rsvt_stt;
        this.cmn_cd_pay_stt = cmn_cd_pay_stt;
        this.rsvt_date = rsvt_date;
        this.cnc_pay_no = cnc_pay_no;
        this.adt_cnt = adt_cnt;
        this.chd_cnt = chd_cnt;
        this.bb_cnt = bb_cnt;
        this.prd_nm = prd_nm;
        this.prd_dtl_desc = prd_dtl_desc;
    }

    @Override
    public String toString() {
        return "ReservDto{" +
                "rsvt_no='" + rsvt_no + '\'' +
                ", prd_dtl_cd='" + prd_dtl_cd + '\'' +
                ", usr_id='" + usr_id + '\'' +
                ", prd_cd='" + prd_cd + '\'' +
                ", mn_rsvt_nm='" + mn_rsvt_nm + '\'' +
                ", phn='" + phn + '\'' +
                ", email='" + email + '\'' +
                ", sum_prc=" + sum_prc +
                ", pay_ftr_prc=" + pay_ftr_prc +
                ", req_cont='" + req_cont + '\'' +
                ", cmn_cd_rsvt_stt='" + cmn_cd_rsvt_stt + '\'' +
                ", cmn_cd_pay_stt='" + cmn_cd_pay_stt + '\'' +
                ", rsvt_date=" + rsvt_date +
                ", cnc_pay_no=" + cnc_pay_no +
                ", adt_cnt=" + adt_cnt +
                ", chd_cnt=" + chd_cnt +
                ", bb_cnt=" + bb_cnt +
                ", prd_nm='" + prd_nm + '\'' +
                ", prd_dtl_desc='" + prd_dtl_desc + '\'' +
                '}';
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

    public String getPrd_cd() {
        return prd_cd;
    }

    public void setPrd_cd(String prd_cd) {
        this.prd_cd = prd_cd;
    }

    public String getMn_rsvt_nm() {
        return mn_rsvt_nm;
    }

    public void setMn_rsvt_nm(String mn_rsvt_nm) {
        this.mn_rsvt_nm = mn_rsvt_nm;
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

    public long getSum_prc() {
        return sum_prc;
    }

    public void setSum_prc(long sum_prc) {
        this.sum_prc = sum_prc;
    }

    public long getPay_ftr_prc() {
        return pay_ftr_prc;
    }

    public void setPay_ftr_prc(long pay_ftr_prc) {
        this.pay_ftr_prc = pay_ftr_prc;
    }

    public String getReq_cont() {
        return req_cont;
    }

    public void setReq_cont(String req_cont) {
        this.req_cont = req_cont;
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

    public Date getRsvt_date() {
        return rsvt_date;
    }

    public void setRsvt_date(Date rsvt_date) {
        this.rsvt_date = rsvt_date;
    }

    public Integer getCnc_pay_no() {
        return cnc_pay_no;
    }

    public void setCnc_pay_no(Integer cnc_pay_no) {
        this.cnc_pay_no = cnc_pay_no;
    }

    public Integer getAdt_cnt() {
        return adt_cnt;
    }

    public void setAdt_cnt(Integer adt_cnt) {
        this.adt_cnt = adt_cnt;
    }

    public Integer getChd_cnt() {
        return chd_cnt;
    }

    public void setChd_cnt(Integer chd_cnt) {
        this.chd_cnt = chd_cnt;
    }

    public Integer getBb_cnt() {
        return bb_cnt;
    }

    public void setBb_cnt(Integer bb_cnt) {
        this.bb_cnt = bb_cnt;
    }

    public String getPrd_nm() {
        return prd_nm;
    }

    public void setPrd_nm(String prd_nm) {
        this.prd_nm = prd_nm;
    }

    public String getPrd_dtl_desc() {
        return prd_dtl_desc;
    }

    public void setPrd_dtl_desc(String prd_dtl_desc) {
        this.prd_dtl_desc = prd_dtl_desc;
    }
}
