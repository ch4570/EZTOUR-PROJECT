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

    public PrdImgDto(String prd_dtl_cd, String img_path) {
        this.prd_cd = prd_dtl_cd;
        this.img_pth = img_path;
    }
}
