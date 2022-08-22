package com.devcamp.eztour.domain.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PrdOptionDto {

    private String dstn_cd;
    private String cntn_cd;
    private String cntn_cd_nm;
    private final String nt_cd;
    private final String nt_cd_nm;
    private String cty_cd;
    private String cty_cd_nm;
    private String use_yn;
    private String frs_reg_date;
    private String frs_rgs_no;
    private String fnl_mod_date;
    private String fnl_mod_no;

}
