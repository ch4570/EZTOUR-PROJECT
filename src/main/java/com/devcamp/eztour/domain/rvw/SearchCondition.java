package com.devcamp.eztour.domain.rvw;


import org.springframework.web.util.UriComponentsBuilder;


public class SearchCondition {
    private Integer page = 1;
    private Integer pageSize = 5;
//    private Integer offset = 10;
    private String option = "";
    private String keyword = "";
    private String cntn_cd = "";
    private String sort = "";

    public SearchCondition() {}

    public SearchCondition(Integer page, Integer pageSize, String keyword, String option) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.option = option;
    }



    public String getQueryString(String option, String sort) {
        // ?page=1&pageSize=10&option=T&keyword="title"
        // ?page=1&pageSize=5&option=&keyword=
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("option", option)
                .queryParam("keyword", keyword)
                .queryParam("cntn_cd", cntn_cd)
                .queryParam("sort", sort)
                .build().toString();
    }

    public String getQueryString(Integer page) {
        // ?page=1&pageSize=10&option=T&keyword="title"
        // ?page=1&pageSize=5&option=&keyword=
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("option", option)
                .queryParam("keyword", keyword)
                .queryParam("cntn_cd", cntn_cd)
                .queryParam("sort", sort)
                .build().toString();
    }

    public String getQueryString(String cntn_cd) {
        // ?page=1&pageSize=10&option=T&keyword="title"
        // ?page=1&pageSize=5&option=&keyword=
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("option", option)
                .queryParam("keyword", keyword)
                .queryParam("cntn_cd", cntn_cd)
                .queryParam("sort", sort)
                .build().toString();
    }



    public String getQueryString() {
        return getQueryString(page);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {return (page-1) * pageSize;}


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

    public String getCntn_cd() {
        return cntn_cd;
    }

    public void setCntn_cd(String cntn_cd) {
        this.cntn_cd = cntn_cd;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
