package com.devcamp.eztour.domain.faq;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class FaqDto {

    private Integer faq_no;
    private final String qna_cd;
    private final String faq_qna_cont;
    private final String faq_ans_cont;
    private String reg_date;
    private String mdf_date;

}
