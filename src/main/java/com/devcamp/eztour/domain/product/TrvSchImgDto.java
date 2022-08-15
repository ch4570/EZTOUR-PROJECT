package com.devcamp.eztour.domain.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
public class TrvSchImgDto {
    private int sch_img_no;
    private int sch_no;
    private String prd_cd;
    private String prf_img_pth;
    private String frs_reg_date;
    private String frs_rgs_no;
    private String fnl_mod_date;
    private String fnl_mod_no;

    public TrvSchImgDto(int sch_no, String prd_cd, String prf_img_pth) {
        this.sch_no = sch_no;
        this.prd_cd = prd_cd;
        this.prf_img_pth = prf_img_pth;
    }
}
