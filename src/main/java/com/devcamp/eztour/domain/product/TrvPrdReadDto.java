package com.devcamp.eztour.domain.product;

import lombok.*;
import java.util.Date;


@ToString
@Getter
@Setter
public class TrvPrdReadDto {

    private String prd_cd;          //상품코드
    private String dstn_cd;         //여행지코드
    private String cmn_cd_thm;      //테마상태
    private String prd_nm;          //상품명
    private String prd_dtl_desc;    //상품상세설명
    private String trv_per;         //여행기간
    private Integer prd_str_prc;    //상품시작가격
    private String dpr_str_date;    //출발시작일
    private String dpr_fin_date;    //출발마감일
    private int star;               //별점
    private int mn_img_yn;
    private String img_pth;
    private boolean evnt_yn;        //이벤트여부
    private boolean fin_yn;         //마감여부
    private boolean act_yn;         //활성화여부
    private boolean dc_yn;          //할인여부
    private int pr_prc;             //할인가
    private int add_sv_rt;          //추가적립비율
    private int vcnt;               //조회수
    private boolean lk_yn;          //좋아요여부
    private Date frs_reg_date;      //최초등록일자
    private String frs_rgs_no;      //최초등록자식별번호
    private Date fnl_mod_date;      //최종수정일자
    private String fnl_mod_no;      //최종수정자식별번호



    public TrvPrdReadDto(String prd_cd, String dstn_cd, String cmn_cd_thm, String prd_nm,
                         String prd_dtl_desc, String trv_per, Integer prd_str_prc,
                         String dpr_str_date, String dpr_fin_date) {

        this.prd_cd = prd_cd;
        this.dstn_cd = dstn_cd;
        this.cmn_cd_thm = cmn_cd_thm;
        this.prd_nm = prd_nm;
        this.prd_dtl_desc = prd_dtl_desc;
        this.trv_per = trv_per;
        this.prd_str_prc = prd_str_prc;
        this.dpr_str_date = dpr_str_date;
        this.dpr_fin_date = dpr_fin_date;
        this.img_pth = img_pth;
        this.mn_img_yn = mn_img_yn;
    }

    public TrvPrdReadDto(String prd_cd, String cmn_cd_thm, String prd_nm,
                         Integer prd_str_prc, Date frs_reg_date){
        this.prd_cd = prd_cd;
        this.cmn_cd_thm = cmn_cd_thm;
        this.prd_nm = prd_nm;
        this.prd_str_prc = prd_str_prc;
        this.frs_reg_date = frs_reg_date;
    }
}
