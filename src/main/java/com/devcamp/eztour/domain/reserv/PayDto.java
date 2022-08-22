package com.devcamp.eztour.domain.reserv;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class PayDto {
    @NonNull
    private String pay_no; //결제번호 o
    @NonNull
    private String rsvt_no; //예약번호 0
    @NonNull
    private String prd_dtl_cd; //상품상세번호 0
    @NonNull
    private String usr_id;
    private String imp_uid; // 0
    @NonNull
    private Long pay_prc; //지불금액
    @NonNull
    private Date pay_date; //지불날짜
    @NonNull
    private String cmn_cd_pay_appr; //결제승인코드
    @NonNull
    private String cmn_cd_pay_stt; //결제상태코드
    @NonNull
    private String pay_mthd;
    private int dvd_mnt = 0;
    @NonNull
    private int used_mlg; //0
    private String cnc_rsn;
}
