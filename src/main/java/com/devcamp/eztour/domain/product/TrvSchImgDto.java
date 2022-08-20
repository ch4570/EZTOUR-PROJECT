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

    public TrvSchImgDto(int sch_no, String prd_cd, String prf_img_pth,String frs_rgs_no) {
        this.sch_no = sch_no;
        this.prd_cd = prd_cd;
        this.prf_img_pth = prf_img_pth;
        this.frs_rgs_no = frs_rgs_no;
    }

    public TrvSchImgDto(int sch_img_no, int sch_no, String prd_cd, String prf_img_pth, String frs_reg_date, String fnl_mod_date){
        this.sch_img_no = sch_img_no;
        this.sch_no = sch_no;
        this.prd_cd = prd_cd;
        this.prf_img_pth = prf_img_pth;
        this.frs_reg_date = frs_reg_date;
        this.fnl_mod_date = fnl_mod_date;
    }

}
