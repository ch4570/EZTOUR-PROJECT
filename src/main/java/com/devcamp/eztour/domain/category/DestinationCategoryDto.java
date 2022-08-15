package com.devcamp.eztour.domain.category;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class DestinationCategoryDto {

    private final String dstn_cd;
    private final String cntn_cd;
    private final String cntn_cd_nm;
    private final String nt_cd;
    private final String nt_cd_nm;
    private final String cty_cd;
    private final String cty_cd_nm;
    private final boolean use_yn;

}