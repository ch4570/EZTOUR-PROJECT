package com.devcamp.eztour.domain.customercenter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.util.Date;

@ToString
@Getter
@Setter
public class CustomerInquiryDto {
    private int qna_no;
    private String usr_id;
    private String qna_cd;
    private String email;
    private String phn;
    private String qna_ttl;
    private String qna_cont;
    private int ans_yn;
    private Date frs_reg_date;
    private String frs_rgs_no;
    private Date fnl_mod_date;
    private String fml_mod_no;

    public CustomerInquiryDto() {
    }

    public CustomerInquiryDto(int qna_no, String usr_id, String qna_cd, String email,
                              String phn, String qna_ttl, String qna_cont, int ans_yn,
                              Date frs_reg_date, String frs_rgs_no, Date fnl_mod_date, String fml_mod_no) {
        this.qna_no = qna_no;
        this.usr_id = usr_id;
        this.qna_cd = qna_cd;
        this.email = email;
        this.phn = phn;
        this.qna_ttl = qna_ttl;
        this.qna_cont = qna_cont;
        this.ans_yn = ans_yn;
        this.frs_reg_date = frs_reg_date;
        this.frs_rgs_no = frs_rgs_no;
        this.fnl_mod_date = fnl_mod_date;
        this.fml_mod_no = fml_mod_no;
    }
}
