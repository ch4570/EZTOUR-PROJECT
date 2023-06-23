package com.devcamp.eztour.domain.pay;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayResultDto {
    @NonNull
    private String pay_no;
    private long pay_prc;
    @NonNull
    private Date pay_date; //지불날짜
    @NonNull
    private String pay_mthd;
    @NonNull
    private String cmn_cd_pay_stt; //결제상태코드
    @NonNull
    private String cmn_cd_pay_appr; //결제승인코드
    @NonNull
    private String cmn_cd_rsvt_stt; //결제상태코드
    @NonNull
    private String imp_uid;
}
