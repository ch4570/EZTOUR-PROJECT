package com.devcamp.eztour.domain.reserv;

import lombok.*;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CancelViewDto {
    private Date rsvt_date;
    private String cmn_cd_pay_stt;
    private Long sum_prc;
    private String prd_nm;
    private String prd_dtl_desc;
    private String pay_no;
    private Long pay_prc;
    private Integer used_mlg;
    private String img_pth;
    private String new_pay_no;
    private String rsvt_no;
    private String cnc_rsn;
}
