package com.devcamp.eztour.domain.rvw;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@ToString
public class PageHandler {
//    private int page;
//    private int pageSize;
//    private String option;
//    private String keyword;
    private SearchCondition sc;

    private int totalCnt;
    private int totalPage;
    private int navSize = 5;
    private int beginPage;
    private int endPage;
    private boolean showPrev;
    private boolean showNext;

    public PageHandler(int totalCnt, SearchCondition sc) {
        this.totalCnt = totalCnt; //127
        this.sc = sc;

        doPaging(totalCnt, sc);
    }

    public PageHandler() {}

    public void doPaging(int totalCnt, SearchCondition sc) {
        this.totalCnt = totalCnt;

        totalPage = (int)Math.ceil(totalCnt / (double)sc.getPageSize()); // Math.ceil = 올림
        beginPage = (sc.getPage()-1) / navSize * navSize + 1;
        endPage = Math.min(beginPage + navSize - 1, totalPage);
        showPrev = beginPage != 1;
        showNext = endPage != totalPage;
    }

}
