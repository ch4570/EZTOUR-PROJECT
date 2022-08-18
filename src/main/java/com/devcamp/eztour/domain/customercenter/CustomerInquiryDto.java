package com.devcamp.eztour.domain.customercenter;

import org.springframework.stereotype.Repository;

@Repository
public class CustomerInquiryDto {
    private Integer qna_no;
    private String usr_id;
    private String qna_cd;
    private String email;
    private String phn;
    private String qna_ttl;
    private String qna_cont;
    private Integer ans_yn;

    public CustomerInquiryDto() {
    }

    public CustomerInquiryDto(String usr_id, String email, String phn, String qna_ttl, String qna_cont, Integer ans_yn) {
        this.usr_id = usr_id;
        this.email = email;
        this.phn = phn;
        this.qna_ttl = qna_ttl;
        this.qna_cont = qna_cont;
        this.ans_yn = ans_yn;
    }

    public Integer getQna_no() {
        return qna_no;
    }

    public void setQna_no(Integer qna_no) {
        this.qna_no = qna_no;
    }

    public String getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(String usr_id) {
        this.usr_id = usr_id;
    }

    public String getQna_cd() {
        return qna_cd;
    }

    public void setQna_cd(String qna_cd) {
        this.qna_cd = qna_cd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public String getQna_ttl() {
        return qna_ttl;
    }

    public void setQna_ttl(String qna_ttl) {
        this.qna_ttl = qna_ttl;
    }

    public String getQna_cont() {
        return qna_cont;
    }

    public void setQna_cont(String qna_cont) {
        this.qna_cont = qna_cont;
    }

    public Integer getAns_yn() {
        return ans_yn;
    }

    public void setAns_yn(Integer ans_yn) {
        this.ans_yn = ans_yn;
    }

    @Override
    public String toString() {
        return "CustomerInquryDto{" +
                "qna_no=" + qna_no +
                ", usr_id='" + usr_id + '\'' +
                ", qna_cd='" + qna_cd + '\'' +
                ", email='" + email + '\'' +
                ", phn='" + phn + '\'' +
                ", qna_ttl='" + qna_ttl + '\'' +
                ", qna_cont='" + qna_cont + '\'' +
                ", ans_yn=" + ans_yn +
                '}';
    }
}
