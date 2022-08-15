package com.devcamp.eztour.domain.faq;

import lombok.*;

import java.util.Objects;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class FaqDto {

    private Integer faq_no;
    private String qna_cd;
    private String faq_qna_cont;
    private String faq_ans_cont;
    private String reg_date;
    private String mdf_date;

}
