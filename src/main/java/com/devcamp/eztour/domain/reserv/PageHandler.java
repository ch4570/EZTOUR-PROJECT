package com.devcamp.eztour.domain.reserv;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class PageHandler {
    private int page;
    private int pageSize;
    private int totalCnt;
    private int totalPage;
    private int navSize = 3;
    private int beginPage; //nav size 안에서 nav 시작 숫자
    private int endPage;   //nav size 안에서 nav 끝 숫자
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
        this.beginPage = (page-1)/navSize*navSize + 1;
        this.endPage = Math.min((page-1)/navSize*navSize + navSize, totalPage);
        this.showPrev = beginPage != 1;
        this.showNext = endPage != totalPage;
    }
}
