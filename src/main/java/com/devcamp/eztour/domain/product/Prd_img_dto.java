package com.devcamp.eztour.domain.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Prd_img_dto {
    int			prd_img_no;
    String		prd_dtl_cd;
    String		img_path;
    boolean	    mn_img_yn;
    Date        frs_reg_date;
    String		frs_rgs_no;
    Date		fnl_mod_date;
    String		fnl_mod_no;

    public Prd_img_dto(int prd_img_no, String prd_dtl_cd, String img_path,
                       Date frs_reg_date, String frs_rgs_no, String fnl_mod_no) {
        this.prd_img_no = prd_img_no;
        this.prd_dtl_cd = prd_dtl_cd;
        this.img_path = img_path;
        this.frs_reg_date = frs_reg_date;
        this.frs_rgs_no = frs_rgs_no;
        this.fnl_mod_no = fnl_mod_no;
    }
}
