package com.devcamp.eztour.domain.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@ToString
public class PrdPcrDto {
    int 		prd_prc_no;
    String		prd_dtl_cd;
    int			adt_prc;
    int			chd_prc;
    int			bb_prc;


    Date        frs_reg_date;
    String		frs_rgs_no;
    Date		fnl_mod_date;
    String		fnl_mod_no;


}
