package com.devcamp.eztour.domain.customercenter;

public class CustomerSearchCondition {
    private String qna_ttl;
    private String qna_cont;
    private Integer offset = 0;
    private String keyword = "";
    private String option = "";

    public CustomerSearchCondition() {
    }

    public CustomerSearchCondition(String qna_ttl, String qna_cont, String keyword, String option) {
        this.qna_ttl = qna_ttl;
        this.qna_cont = qna_cont;
        this.keyword = keyword;
        this.option = option;
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

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "CustomerSearchCondition{" +
                "qna_ttl='" + qna_ttl + '\'' +
                ", qna_cont='" + qna_cont + '\'' +
                ", offset=" + offset +
                ", keyword='" + keyword + '\'' +
                ", option='" + option + '\'' +
                '}';
    }
}
