package com.devcamp.eztour.domain.reserv;

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

    @Override
    public String toString() {
        return "PageHandler{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", totalCnt=" + totalCnt +
                ", totalPage=" + totalPage +
                ", navSize=" + navSize +
                ", firstPage=" + firstPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getNavSize() {
        return navSize;
    }

    public void setNavSize(int navSize) {
        this.navSize = navSize;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }
}
