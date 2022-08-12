package com.devcamp.eztour.domain.reserv;

public class ReservInfoDto {
    private String prdDtlCd;
    private String prdCd;
    private String prdNm;
    private String arlNm;
    private String trvPer; //여행기간
    //일정
//    private String goDprTm; //가는 비행기 한국 출발 시간
//    private String goArrTm; //가는 비행기 현지 도착 시간
//    private String cbDprTm; //오는 비행기 현지 출발시간
//    private String cbArrTm; //오는 비행기 한국 도착 시간
    //요금
    private int adtPrc;
    private int chdPrc;
    private int bbPrc;
    //사용자 정보
    private String usrNm;
    private String phn;
    private String email;

    public ReservInfoDto(){}
    public ReservInfoDto(String prdDtlCd, String prdCd, String prdNm, String arlNm, String trvPer, int adtPrc, int chdPrc, int bbPrc, String usrNm, String phn, String email) {
        this.prdDtlCd = prdDtlCd;
        this.prdCd = prdCd;
        this.prdNm = prdNm;
        this.arlNm = arlNm;
        this.trvPer = trvPer;
        this.adtPrc = adtPrc;
        this.chdPrc = chdPrc;
        this.bbPrc = bbPrc;
        this.usrNm = usrNm;
        this.phn = phn;
        this.email = email;
    }

    @Override
    public String toString() {
        return "ReservInfoDto{" +
                "prdDtlCd='" + prdDtlCd + '\'' +
                ", prdCd='" + prdCd + '\'' +
                ", prdNm='" + prdNm + '\'' +
                ", arlNm='" + arlNm + '\'' +
                ", trvPer='" + trvPer + '\'' +
                ", adtPrc=" + adtPrc +
                ", chdPrc=" + chdPrc +
                ", bbPrc=" + bbPrc +
                ", usrNm='" + usrNm + '\'' +
                ", phn='" + phn + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getPrdDtlCd() {
        return prdDtlCd;
    }

    public void setPrdDtlCd(String prdDtlCd) {
        this.prdDtlCd = prdDtlCd;
    }

    public String getPrdCd() {
        return prdCd;
    }

    public void setPrdCd(String prdCd) {
        this.prdCd = prdCd;
    }

    public String getPrdNm() {
        return prdNm;
    }

    public void setPrdNm(String prdNm) {
        this.prdNm = prdNm;
    }

    public String getArlNm() {
        return arlNm;
    }

    public void setArlNm(String arlNm) {
        this.arlNm = arlNm;
    }

    public String getTrvPer() {
        return trvPer;
    }

    public void setTrvPer(String trvPer) {
        this.trvPer = trvPer;
    }

    public int getAdtPrc() {
        return adtPrc;
    }

    public void setAdtPrc(int adtPrc) {
        this.adtPrc = adtPrc;
    }

    public int getChdPrc() {
        return chdPrc;
    }

    public void setChdPrc(int chdPrc) {
        this.chdPrc = chdPrc;
    }

    public int getBbPrc() {
        return bbPrc;
    }

    public void setBbPrc(int bbPrc) {
        this.bbPrc = bbPrc;
    }

    public String getUsrNm() {
        return usrNm;
    }

    public void setUsrNm(String usrNm) {
        this.usrNm = usrNm;
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
