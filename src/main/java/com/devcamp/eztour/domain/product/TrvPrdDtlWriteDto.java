package com.devcamp.eztour.domain.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
public class TrvPrdDtlWriteDto {

    @NotBlank(message = "상품 상세코드는 필수 입력 항목입니다.")
    @Length(max = 30, message = "상품 상세코드는 15자까지 입력가능 합니다.")
    private String prd_dtl_cd;

    @NotBlank(message = "상품 코드는 필수 입력 항목입니다.")
    @Length(max = 15, message = "상품 코드는 15자까지 입력가능 합니다.")
    private String prd_cd;

    @Min(value = 1, message = "상품 시작가격은 1원보다 커야 합니다.")
    private int	    prd_str_prc;

    @NotBlank(message = "항공사명은 필수 입력 항목입니다.")
    @Length(max = 20, message = "항공사명은 20자까지 입력가능 합니다.")
    private String	arl_nm;

    private boolean	dpr_yn;
    private boolean rsvt_yn;
    private int		pr_rsvt_cnt;

    @Min(value = 1, message = "최소 출발인원은 1명 이상이여야 합니다.")
    private int		min_stt_cnt;

    @Min(value = 1, message = "최대 출발인원은 1명 이상이어야 합니다.")
    private int		max_stt_cnt;

    @NotBlank(message = "현지 출발일은 필수 입력 항목입니다.")
    private String loc_dpr_date;

    @NotBlank(message = "현지 도착일은 필수 입력 항목입니다.")
    private String loc_fin_date;

    @NotBlank(message = "국내 출발일은 필수 입력 항목입니다.")
    private String dom_dpr_date;

    @NotBlank(message = "국내 도착일은 필수 입력 항목입니다.")
    private String dom_fin_date;

    @NotBlank(message = "상품 이름은 필수 입력 항목입니다.")
    @Length(max = 100, message = "상품이름은 100자까지 입력가능 합니다.")
    private String prd_nm;

    private String	frs_reg_date;
    private String	frs_rgs_no;
    private String  fnl_mod_date;
    private String	fnl_mod_no;

}
