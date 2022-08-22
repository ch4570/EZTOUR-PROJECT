package com.devcamp.eztour.domain.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class TrvPrdPrcDto {
    private int prd_prc_no;

    @NotBlank(message = "상품상세코드는 필수 입력 항목입니다.")
    private String prd_dtl_cd;

    @NotBlank(message = "상품코드는 필수 입력 항목입니다.")
    private String prd_cd;
    @Min(value = 1, message = "성인 금액은 1원보다 커야 합니다.")
    private int adt_prc;
    @Min(value = 1, message = "아동 금액은 1원보다 커야 합니다.")
    private int chd_prc;
    @Min(value = 1, message = "유아 금액은 1원보다 커야 합니다.")
    private int bb_prc;
    private String frs_reg_date;
    private String frs_rgs_no;
    private String fnl_mod_date;
    private String fnl_mod_no;
    public TrvPrdPrcDto(){}


    public TrvPrdPrcDto(int prd_prc_no, String prd_cd, String prd_dtl_cd, int adt_prc, int chd_prc, int bb_prc, String frs_reg_date) {
        this.prd_prc_no = prd_prc_no;
        this.prd_cd = prd_cd;
        this.prd_dtl_cd = prd_dtl_cd;
        this.adt_prc = adt_prc;
        this.chd_prc = chd_prc;
        this.bb_prc = bb_prc;
        this.frs_reg_date = frs_reg_date;
    }


}
