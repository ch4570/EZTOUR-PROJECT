package com.devcamp.eztour.domain.attPrd;

import org.springframework.web.util.UriComponentsBuilder;


public class SearchCondition {
    private Integer page = 1;
    private Integer pageSize = 5;

    public SearchCondition() {}

    public SearchCondition(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }



    public String getQueryString(Integer page) {
        // ?page=1&pageSize=10
        // ?page=1&pageSize=5
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
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

}