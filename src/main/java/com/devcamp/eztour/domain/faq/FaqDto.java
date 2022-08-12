package com.devcamp.eztour.domain.faq;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class FaqDto {

    private String faq_no;
    private final String qna_cd;
    private final String faq_qna_cont;
    private final String faq_ans_cont;
    private String reg_date;
    private String mdf_date;
    private String frs_reg_date;
    private String frs_rgs_no;
    private String fnl_mod_date;
    private String fnl_mod_no;

}
