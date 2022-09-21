package com.devcamp.eztour.domain.reserv;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReservConfInfoDto {
    //예약 확인 페이지 정보를 보여주기 위한 Dto
    private Date rsvt_date; //예약일
    private String rsvt_no; //예약번호
    private String prd_dtl_cd; //상품상세번호
    private int adt_cnt; //성인수
    private int chd_cnt; //아동수
    private int bb_cnt; //유아수
    private long sum_prc; //총금액
    private String cmn_cd_rsvt_stt; //예약상태
    private String cmn_cd_pay_stt;
    private String pay_ftr_prc; //결제예정금액
    private String prd_nm; //상품이름
    private String prd_cd; //상품코드
    private String trv_per; //여행기간
    private String img_pth; //상품이미지
    private String trv_dtl_per; //여행기간

    //일정
    private String go_dpr_tm; //한국-해외 한국 출발 시간
    private String go_dpr_arl_id;//한국-해외 출발하는 비행기 id
    private String cb_arr_tm; //오는 비행기 한국 도착 시간
    private String cb_arr_arl_id; //해외-한국 도착하는 비행기 id(경유가능성)

    private String arl_nm;
    private Date dom_dpr_date;
    private Date dom_fin_date;
    private Date loc_dpr_date;
    private Date loc_fin_date;
}
