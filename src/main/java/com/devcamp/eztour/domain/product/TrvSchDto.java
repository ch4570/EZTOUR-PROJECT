package com.devcamp.eztour.domain.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@ToString
public class TrvSchDto {

    private int sch_no;
    @NotBlank(message = "상품코드는 필수 입력 항목입니다.")
    private String		prd_cd;
    @Min(value = 1, message = "여행 일차는 1보다 커야 합니다.")
    private int			trv_date;
    @Min(value = 1, message = "일정 순번은 1보다 커야 합니다.")
    private int			sch_ord;
    private String		st_nm;
    private String		sit_sh_desc;
    private String		sit_lo_desc;
    private String		ht_inf;
    private String		brk;
    private String		luh;
    private String		din;
    private String		dstnc_tm;
    private String		frs_reg_date;
    private String 		frs_rgs_no;
    private String		fnl_mod_no;

    public TrvSchDto(int sch_no, String prd_cd, int trv_date, int sch_ord, String st_nm, String sit_sh_desc,
                     String sit_lo_desc, String ht_inf, String brk, String luh, String din, String dstnc_tm, String frs_reg_date) {
        this.sch_no = sch_no;
        this.prd_cd = prd_cd;
        this.trv_date = trv_date;
        this.sch_ord = sch_ord;
        this.st_nm = st_nm;
        this.sit_sh_desc = sit_sh_desc;
        this.sit_lo_desc = sit_lo_desc;
        this.ht_inf = ht_inf;
        this.brk = brk;
        this.luh = luh;
        this.din = din;
        this.dstnc_tm = dstnc_tm;
        this.frs_reg_date = frs_reg_date;
    }

    public TrvSchDto(){}

}
