package com.devcamp.eztour.domain.reserv;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PayViewDto {
    private String pay_no; //merchant_uid
    private String imp_uid; //imp_uid
    private String rsvt_no;
    private String prd_dtl_cd; //name
    private String usr_nm; //buyer_name
    private Long pay_prc; // amount
    private Integer used_mlg;
    private String pay_mthd; //pay_method point
    private String email; // buyer_email
    private Long pay_ftr_prc;
    //pg_provider kakaopay
    //status paid
}
