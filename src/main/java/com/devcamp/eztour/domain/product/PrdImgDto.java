package com.devcamp.eztour.domain.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class PrdImgDto {
    int			prd_img_no;
    String		prd_cd;
    String		img_pth;
    boolean	    mn_img_yn;
    Date        frs_reg_date;
    String		frs_rgs_no;
    Date		fnl_mod_date;
    String		fnl_mod_no;
    Integer     prd_str_prc;
    String      cmn_cd_thm;
    String      prd_nm;

    public PrdImgDto(String prd_dtl_cd, String img_pth) {
        this.prd_cd = prd_dtl_cd;
        this.img_pth = img_pth;
    }

    public PrdImgDto(String img_pth, int prd_img_no, String prd_cd) {
        this.prd_img_no = prd_img_no;
        this.prd_cd = prd_cd;
        this.img_pth = img_pth;
    }

    public PrdImgDto(String prd_cd, String prd_nm, String cmn_cd_thm, Date frs_rgs_date, String img_pth, int prd_img_no) {
        this.prd_cd = prd_cd;
        this.img_pth = img_pth;
        this.frs_reg_date = frs_rgs_date;
        this.prd_nm = prd_nm;
        this.cmn_cd_thm = cmn_cd_thm;
        this.prd_img_no = prd_img_no;
    }
}
