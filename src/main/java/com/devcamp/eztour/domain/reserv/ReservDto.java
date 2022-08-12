package com.devcamp.eztour.domain.reserv;

import java.util.Date;

public class ReservDto {
//    private String reservNo; //예약번호
//    private String prdDtlCode;
//    private String userId; //유저아이디
//    private String reservName; //대표예약자명
//    private String phoneNum; //폰번호
//    private String email; //이메일
//    private long priceSum; //합계금액
//    private long priceToPay; //결제예정금액
//    private String requestContent; //요청사항
//    private String reservStatus; //예약상태
//    private Date reservDate; //예약일
//    private Integer payCancelNo; //결제취소번호
//    private Integer adultCnt; //성인수
//    private Integer childCnt; //아동수
//    private Integer infantCnt; //유아수

    private String rsvtNo; //예약번호
    private String prdDtlCd; //상세상품번호
    private String usrId; //유저아이디
    private String mnRsvtNm; //대표예약자명
    private String phn; //폰번호
    private String email; //이메일
    private long sumPrc; //합계금액
    private long payFtrPrc; //결제예정금액
    private String reqCont; //요청사항
    private String cmnCdRsvtStt; //예약상태
    private Date rsvtDate; //예약일
    private Integer cncPayNo; //결제취소번호
    private Integer adtCnt; //성인수
    private Integer chdCnt; //아동수
    private Integer bbCnt; //유아수

    //ckd,
    public ReservDto(){}
    public ReservDto(String rsvtNo, String prdDtlCd, String usrId, String mnRsvtNm, String phn, String email, long sumPrc, long payFtrPrc, String reqCont, Integer cncPayNo, Integer adtCnt, Integer chdCnt, Integer bbCnt) {
        this.rsvtNo = rsvtNo;
        this.prdDtlCd = prdDtlCd;
        this.usrId = usrId;
        this.mnRsvtNm = mnRsvtNm;
        this.phn = phn;
        this.email = email;
        this.sumPrc = sumPrc;
        this.payFtrPrc = payFtrPrc;
        this.reqCont = reqCont;
        this.cmnCdRsvtStt = "6A";
        this.rsvtDate = new Date(System.currentTimeMillis());
        this.cncPayNo = cncPayNo;
        this.adtCnt = adtCnt;
        this.chdCnt = chdCnt;
        this.bbCnt = bbCnt;
    }

    @Override
    public String toString() {
        return "ReservDto{" +
                "rsvtNo='" + rsvtNo + '\'' +
                ", prdDtlCd='" + prdDtlCd + '\'' +
                ", usrId='" + usrId + '\'' +
                ", mnRsvtNm='" + mnRsvtNm + '\'' +
                ", phn='" + phn + '\'' +
                ", email='" + email + '\'' +
                ", sumPrc=" + sumPrc +
                ", payFtrPrc=" + payFtrPrc +
                ", reqCont='" + reqCont + '\'' +
                ", cmnCdRsvtStt='" + cmnCdRsvtStt + '\'' +
                ", rsvtDate=" + rsvtDate +
                ", cncPayNo=" + cncPayNo +
                ", adtCnt=" + adtCnt +
                ", chdCnt=" + chdCnt +
                ", bbCnt=" + bbCnt +
                '}';
    }

    public String getRsvtNo() {
        return rsvtNo;
    }

    public void setRsvtNo(String rsvtNo) {
        this.rsvtNo = rsvtNo;
    }

    public String getPrdDtlCd() {
        return prdDtlCd;
    }

    public void setPrdDtlCd(String prdDtlCd) {
        this.prdDtlCd = prdDtlCd;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getMnRsvtNm() {
        return mnRsvtNm;
    }

    public void setMnRsvtNm(String mnRsvtNm) {
        this.mnRsvtNm = mnRsvtNm;
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

    public long getSumPrc() {
        return sumPrc;
    }

    public void setSumPrc(long sumPrc) {
        this.sumPrc = sumPrc;
    }

    public long getPayFtrPrc() {
        return payFtrPrc;
    }

    public void setPayFtrPrc(long payFtrPrc) {
        this.payFtrPrc = payFtrPrc;
    }

    public String getReqCont() {
        return reqCont;
    }

    public void setReqCont(String reqCont) {
        this.reqCont = reqCont;
    }

    public String getCmnCdRsvtStt() {
        return cmnCdRsvtStt;
    }

    public void setCmnCdRsvtStt(String cmnCdRsvtStt) {
        this.cmnCdRsvtStt = cmnCdRsvtStt;
    }

    public Date getRsvtDate() {
        return rsvtDate;
    }

    public void setRsvtDate(Date rsvtDate) {
        this.rsvtDate = rsvtDate;
    }

    public Integer getCncPayNo() {
        return cncPayNo;
    }

    public void setCncPayNo(Integer cncPayNo) {
        this.cncPayNo = cncPayNo;
    }

    public Integer getAdtCnt() {
        return adtCnt;
    }

    public void setAdtCnt(Integer adtCnt) {
        this.adtCnt = adtCnt;
    }

    public Integer getChdCnt() {
        return chdCnt;
    }

    public void setChdCnt(Integer chdCnt) {
        this.chdCnt = chdCnt;
    }

    public Integer getBbCnt() {
        return bbCnt;
    }

    public void setBbCnt(Integer bbCnt) {
        this.bbCnt = bbCnt;
    }
}
