package com.devcamp.eztour.domain.reserv;

import lombok.*;

import java.util.Date;

@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class GuestDto {
    @NonNull
    private String gst_id;
    @NonNull
    private String gst_nm;
    @NonNull
    private String phn;
    private Date frs_reg_date;
    private String frs_rgs_no;
    private Date fnl_mod_date;
    private String fnl_mod_no;
}
