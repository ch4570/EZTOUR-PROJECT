package com.devcamp.eztour.domain.reserv;

import java.util.Date;

public class AirlineReqDto {
    private int arlReqCd;
    private String dprArlId; //출발공항 아이디
    private String arrArlId; //도착공항아이디
    private Date dprDate; //출발일
    private String arlId; //항공사아이디
    private String arlStt; //출항상태
    private String dprTm;
    private String arrTm;
    private String arlNm;

    public AirlineReqDto(){}
    public AirlineReqDto(int arlReqCd, String dprArlId, String arrArlId, Date dprDate, String arlId, String arlStt, String dprTm, String arrTm, String arlNm) {
        this.arlReqCd = arlReqCd;
        this.dprArlId = dprArlId;
        this.arrArlId = arrArlId;
        this.dprDate = dprDate;
        this.arlId = arlId;
        this.arlStt = arlStt;
        this.dprTm = dprTm;
        this.arrTm = arrTm;
        this.arlNm = arlNm;
    }

    public int getArlReqCd() {
        return arlReqCd;
    }

    public void setArlReqCd(int arlReqCd) {
        this.arlReqCd = arlReqCd;
    }

    public String getDprArlId() {
        return dprArlId;
    }

    public void setDprArlId(String dprArlId) {
        this.dprArlId = dprArlId;
    }

    public String getArrArlId() {
        return arrArlId;
    }

    public void setArrArlId(String arrArlId) {
        this.arrArlId = arrArlId;
    }

    public Date getDprDate() {
        return dprDate;
    }

    public void setDprDate(Date dprDate) {
        this.dprDate = dprDate;
    }

    public String getArlId() {
        return arlId;
    }

    public void setArlId(String arlId) {
        this.arlId = arlId;
    }

    public String getArlStt() {
        return arlStt;
    }

    public void setArlStt(String arlStt) {
        this.arlStt = arlStt;
    }

    public String getDprTm() {
        return dprTm;
    }

    public void setDprTm(String dprTm) {
        this.dprTm = dprTm;
    }

    public String getArrTm() {
        return arrTm;
    }

    public void setArrTm(String arrTm) {
        this.arrTm = arrTm;
    }

    public String getArlNm() {
        return arlNm;
    }

    public void setArlNm(String arlNm) {
        this.arlNm = arlNm;
    }
}
