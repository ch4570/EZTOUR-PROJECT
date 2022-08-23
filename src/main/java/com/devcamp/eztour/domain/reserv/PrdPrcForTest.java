package com.devcamp.eztour.domain.reserv;

import java.util.Date;

public class PrdPrcForTest {
    private int prd_prc_no;
    private String prd_dtl_cd;
    private int adt_prc;
    private int	chd_prc;
    private int	bb_prc;
    private Date frs_reg_date;
    private String frs_rgs_no;
    private Date fnl_mod_date;
    private String fnl_mod_no;

    public PrdPrcForTest(){}
    public PrdPrcForTest(String prd_dtl_cd, int adt_prc, int chd_prc, int bb_prc, Date frs_reg_date, String frs_rgs_no, String fnl_mod_no) {
        this.prd_prc_no = prd_prc_no;
        this.prd_dtl_cd = prd_dtl_cd;
        this.adt_prc = adt_prc;
        this.chd_prc = chd_prc;
        this.bb_prc = bb_prc;
        this.frs_reg_date = frs_reg_date;
        this.frs_rgs_no = frs_rgs_no;
        this.fnl_mod_date = fnl_mod_date;
        this.fnl_mod_no = fnl_mod_no;
    }

    @Override
    public String toString() {
        return "PrdPrcForTest{" +
                "prd_prc_no=" + prd_prc_no +
                ", prd_dtl_cd='" + prd_dtl_cd + '\'' +
                ", adt_prc=" + adt_prc +
                ", chd_prc=" + chd_prc +
                ", bb_prc=" + bb_prc +
                ", frs_reg_date=" + frs_reg_date +
                ", frs_rgs_no='" + frs_rgs_no + '\'' +
                ", fnl_mod_date=" + fnl_mod_date +
                ", fnl_mod_no='" + fnl_mod_no + '\'' +
                '}';
    }

    public int getPrd_prc_no() {
        return prd_prc_no;
    }

    public void setPrd_prc_no(int prd_prc_no) {
        this.prd_prc_no = prd_prc_no;
    }

    public String getPrd_dtl_cd() {
        return prd_dtl_cd;
    }

    public void setPrd_dtl_cd(String prd_dtl_cd) {
        this.prd_dtl_cd = prd_dtl_cd;
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

    public Date getFrs_reg_date() {
        return frs_reg_date;
    }

    public void setFrs_reg_date(Date frs_reg_date) {
        this.frs_reg_date = frs_reg_date;
    }

    public String getFrs_rgs_no() {
        return frs_rgs_no;
    }

    public void setFrs_rgs_no(String frs_rgs_no) {
        this.frs_rgs_no = frs_rgs_no;
    }

    public Date getFnl_mod_date() {
        return fnl_mod_date;
    }

    public void setFnl_mod_date(Date fnl_mod_date) {
        this.fnl_mod_date = fnl_mod_date;
    }

    public String getFnl_mod_no() {
        return fnl_mod_no;
    }

    public void setFnl_mod_no(String fnl_mod_no) {
        this.fnl_mod_no = fnl_mod_no;
    }
}
