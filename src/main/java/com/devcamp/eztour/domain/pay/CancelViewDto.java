package com.devcamp.eztour.domain.pay;

import lombok.*;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CancelViewDto {
    private String rsvt_no;
    private Date rsvt_date;
    private String cmn_cd_pay_stt;
    private String pay_no;
    private String prd_dtl_cd;
    private String prd_nm;
    private String prd_dtl_desc;
    private String img_pth;
    private Long sum_prc;
    private String new_pay_no;
    private Integer used_mlg;
    private Long pay_prc;
    private String cnc_rsn;
}
