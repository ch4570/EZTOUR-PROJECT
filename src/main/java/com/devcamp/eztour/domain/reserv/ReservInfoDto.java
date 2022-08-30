package com.devcamp.eztour.domain.reserv;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservInfoDto {
    //예약페이지 정보 보여주기 위한 Dto
    private String prd_dtl_cd;
    private String prd_cd;
    private String dstn_cd; //여행지코드
    private String prd_nm;
    private String arl_nm;
    private String trv_per; //여행기간
    //일정
    private String go_dpr_tm; //한국-해외 한국 출발 시간
    private String go_dpr_arl_id;//한국-해외 출발하는 비행기 id
    private String go_arr_tm; //한국-해외현지 도착 시간
    private String go_arr_arl_id; //한국-해외 도착하는 비행기 id(경유가능성)

    private String cb_dpr_tm; //오는 비행기 현지 출발시간
    private String cb_dpr_arl_id; //해외-한국 출발하는 비행기id
    private String cb_arr_tm; //오는 비행기 한국 도착 시간
    private String cb_arr_arl_id; //해외-한국 도착하는 비행기 id(경유가능성)

    private Date dom_dpr_date;
    private Date dom_fin_date;
    private Date loc_dpr_date;
    private Date loc_fin_date;
    //요금
    private int adt_prc;
    private int chd_prc;
    private int bb_prc;
    //사용자 정보
    private String usr_nm;
    private String phn;
    private String email;
    //사진
    private String img_pth; //21
}
