package com.devcamp.eztour.domain.rvw;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageHandler {
    private int page;
    private int pageSize;
    private int totalCnt;
    private int totalPage;
    private int navSize = 5;
    private int firstPage;
    private int endPage;
    private boolean showPrev;
    private boolean showNext;

    public PageHandler() {}
    public PageHandler(int page, int totalCnt){
        this(page, 10, totalCnt);
    }
    public PageHandler(int page, int pageSize, int totalCnt) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalCnt = totalCnt;

        this.totalPage = (int)Math.ceil(totalCnt/(double)pageSize);
        this.firstPage = (page-1)/pageSize * pageSize + 1;
        this.endPage = Math.min((page-1)/pageSize*pageSize + pageSize, totalPage);
        this.showPrev = firstPage != 1;
        this.showNext = endPage != totalPage;
    }
}
