package com.devcamp.eztour.domain.product;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageHandlerProduct {
        private int totalCnt;   // 전체 데이터의 개수(게시글의 개수)
        private int page;       // 현재 페이지
        private int beginPage; // 시작 페이지
        private int totalPage; // 전체 페이지수
        private int endPage;   // 마지막 페이지
        private int naviSize = 5;  // 네비게이터의 크기
        private int pageSize = 8;  // 페이지의 크기
        private int startList; // 시작하는 offset
        private boolean preView; // 뒤로가기
        private boolean nextView; // 다음으로 가기
        private String search_option;
        private String search_keyword;

        public PageHandlerProduct(int totalCnt, int page) {
            this.totalCnt = totalCnt;
            this.page = page;
            this.startList = (page - 1) * pageSize;
            totalPage = (int) Math.ceil((double) totalCnt / pageSize);
            beginPage = (page - 1) / naviSize * naviSize + 1;
            endPage = Math.min((((page - 1) / naviSize + 1) * naviSize), totalPage);

            preView = beginPage != 1;
            nextView = endPage != totalPage;
        }

    public PageHandlerProduct(int totalCnt, int page,int pageSize) {
        this.totalCnt = totalCnt;
        this.page = page;
        this.startList = (page - 1) * pageSize;
        totalPage = (int) Math.ceil((double) totalCnt / pageSize);
        beginPage = (page - 1) / naviSize * naviSize + 1;
        endPage = Math.min((((page - 1) / naviSize + 1) * naviSize), totalPage);
        this.pageSize = pageSize;
        preView = beginPage != 1;
        nextView = endPage != totalPage;
    }

        public PageHandlerProduct(int totalCnt, int page,String search_option,String search_keyword) {
            this.totalCnt = totalCnt;
            this.page = page;
            this.startList = (page - 1) * pageSize;
            totalPage = (int) Math.ceil((double) totalCnt / pageSize);
            beginPage = (page - 1) / naviSize * naviSize + 1;
            endPage = Math.min((((page - 1) / naviSize + 1) * naviSize), totalPage);

            preView = beginPage != 1;
            nextView = endPage != totalPage;
            this.search_option = search_option;
            this.search_keyword = search_keyword;
        }

    public PageHandlerProduct(int totalCnt, int page,String search_option,String search_keyword,int pageSize) {
        this.totalCnt = totalCnt;
        this.page = page;
        this.startList = (page - 1) * pageSize;
        totalPage = (int) Math.ceil((double) totalCnt / pageSize);
        beginPage = (page - 1) / naviSize * naviSize + 1;
        endPage = Math.min((((page - 1) / naviSize + 1) * naviSize), totalPage);

        preView = beginPage != 1;
        nextView = endPage != totalPage;
        this.search_option = search_option;
        this.search_keyword = search_keyword;
        this.pageSize = pageSize;
    }

        public PageHandlerProduct(String search_option, String search_keyword) {
        this.search_option = search_option;
        this.search_keyword = search_keyword;
        }
}
