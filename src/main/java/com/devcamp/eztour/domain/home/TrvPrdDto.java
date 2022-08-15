package com.devcamp.eztour.domain.home;

import lombok.*;

@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class TrvPrdDto {

    private final String prd_cd;          //상품코드
    private final String dstn_cd;         //여행지코드
    private final String prd_nm;          //상품명
    private final String prd_dtl_desc;    //상품상세설명
    private final Integer prd_str_prc;    //상품시작가격

    private final String img_pth;         //이미지경로
    private final int mn_img_yn;          //대표이미지

}
