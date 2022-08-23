package com.devcamp.eztour.domain.customercenter;

import com.devcamp.eztour.domain.rvw.PageHandler;
import org.springframework.web.util.UriComponentsBuilder;

public class CustomerPageHandler {
//    private int page; // 현재 페이지
//    private String option;
//    private String keyword;
//    private int pageSize; // 한 페이지의 크기
    private int naviSize = 10; // 페이지 내비게이션의 크기
    private int beginPage; // 네비게이션 첫번째 페이지

    private CustomerSearchCondition csc;

    private int totalCnt; // 총 게시물 갯수

    private int totalPage; // 전체 페이지의 갯수
    private int endPage; //네비게이션 마지막 페이지
    private boolean showPrev; // 이전 페이지의 이동 가능성 여부
    private boolean showNext; // 다음 페이지의 이동 가능성 여부
    public CustomerPageHandler(int totalCnt, CustomerSearchCondition csc) {
        this.totalCnt = totalCnt;
        this.csc = csc;

        CustomerDoPaging(totalCnt, csc);
    }

    public void CustomerDoPaging(int totalCnt, CustomerSearchCondition csc) {
        this.totalCnt = totalCnt;

        totalPage = (int)Math.ceil(totalCnt /(double)csc.getPageSize());
        beginPage = (csc.getPage()-1) / naviSize * naviSize + 1;
        endPage = Math.min(beginPage + naviSize-1, totalPage);
        showPrev = beginPage != 1;
        showNext = endPage != totalPage;
    }

    void print() {
        System.out.println("page = " + csc.getPage());
        System.out.print(showPrev ? "[PREV]" : "");
        for(int i = beginPage; i <= endPage; i++) {
            System.out.print(i+" ");
        }
        System.out.println(showNext ? " [NEXT]" : "");
    }


    public CustomerSearchCondition getCsc() {
        return csc;
    }

    public void setCsc(CustomerSearchCondition csc) {
        this.csc = csc;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
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

    @Override
    public String toString() {
        return "CustomerPageHandler{" +
                "beginPage=" + beginPage +
                ", csc=" + csc +
                ", totalCnt=" + totalCnt +
                ", naviSize=" + naviSize +
                ", totalPage=" + totalPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
