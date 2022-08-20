package com.devcamp.eztour.domain.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class TrvPrdDtlWriteDto {

    @NotBlank(message = "상품 상세코드는 필수 입력 항목입니다.")
    private String prd_dtl_cd;

    @NotBlank(message = "상품 코드는 필수 입력 항목입니다.")
    private String prd_cd;

    @Min(value = 1, message = "상품 시작가격은 1원보다 커야 합니다.")
    private int	    prd_str_prc;

    @NotBlank(message = "항공사명은 필수 입력 항목입니다.")
    private String	arl_nm;

    private boolean	dpr_yn;
    private boolean rsvt_yn;
    private int		pr_rsvt_cnt;

    @Min(value = 1, message = "최소 출발인원은 1명 이상이여야 합니다.")
    private int		min_stt_cnt;

    @Min(value = 1, message = "최대 출발인원은 1명 이상이어야 합니다.")
    private int		max_stt_cnt;

    @NotBlank(message = "출발일은 필수 입력 항목입니다.")
    private String	dpr_date;

    @NotBlank(message = "마감일은 필수 입력 항목입니다.")
    private String  fin_date;

    @NotBlank(message = "상품 이름은 필수 입력 항목입니다.")
    private String prd_nm;

    private String	frs_reg_date;
    private String	frs_rgs_no;
    private String  fnl_mod_date;
    private String	fnl_mod_no;



}
