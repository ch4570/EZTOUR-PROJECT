package com.devcamp.eztour.domain.product;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.*;
import java.util.Date;


@ToString
@Getter
@Setter
@EqualsAndHashCode
public class TrvPrdWriteDto {

    @NotBlank(message = "상품 코드는 필수 입력 항목입니다.")
    @Length(max = 15, message = "상품 코드는 15자까지 입력가능 합니다.")
    private String prd_cd;

    private String prd_cd_mod;

    @Length(max = 15, message = "여행지 코드는 15자까지 입력가능 합니다.")
    @NotBlank(message = "여행지 코드는 필수 입력 항목입니다.")
    private String dstn_cd;

    @NotBlank(message = "테마 코드는 필수 입력 항목입니다.")
    @Length(max = 15, message = "테마 코드는 15자까지 입력가능 합니다.")
    private String cmn_cd_thm;

    @NotBlank(message = "상품 이름은 필수 입력 항목입니다.")
    @Length(max = 100, message = "상품 코드는 15자까지 입력가능 합니다.")
    private String prd_nm;

    @NotBlank(message = "상품 설명은 필수 입력 항목 입니다.")
    private String prd_dtl_desc;

    @NotBlank(message = "여행기간은 필수 입력 항목입니다.")
    @Length(max = 30, message = "여행기간은 15자까지 입력가능 합니다.")
    private String trv_per;

    @Min(value = 1 , message = "상품시작가격은 1원보다 커야합니다.")
    private int prd_str_prc;

    private String mn_img;

    @Future(message = "출발 시작 날짜는 현재 날짜보다 커야합니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "출발 시작 날짜는 필수 입력 항목입니다.")
    private Date dpr_str_date;


    @Future(message = "출발 마감 날짜는 현재 날짜보다 커야합니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "출발 마감 날짜는 필수 입력 항목입니다.")
    private Date dpr_fin_date;

    @NotBlank(message = "출발 요일은 필수 입력 항목입니다.")
    @Length(max = 50, message = "출발 요일은 50자까지 입력가능합니다.")
    private String dpr_day;


    private int star;
    private boolean evnt_yn;
    private boolean fin_yn;
    private boolean act_yn;
    private boolean dc_yn;
    private int pr_prc;
    private int add_sv_rt;
    private int vcnt;
    private boolean lk_yn;
    private Date frs_reg_date;
    private String frs_rgs_no;
    private Date fnl_mod_date;
    private String fnl_mod_no;

    @NotBlank(message = "대륙코드는 필수 입력 항목입니다.")
    @Length(max = 10, message = "대륙코드는 필수 입력 항목입니다.")
    private String  cntn_cd;

    private String  nt_cd;

}
