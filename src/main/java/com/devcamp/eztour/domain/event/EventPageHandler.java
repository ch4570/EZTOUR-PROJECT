package com.devcamp.eztour.domain.event;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class EventPageHandler {

    private int eventTotalCnt; // 총 게시물 갯수
    private int eventPageSize; // 한 페이지의 크기
    private int eventNaviSize = 10; // 페이지 내비게이션의 크기
    private int eventTotalPage;// 전체 페이지의 갯수
    private int eventPage;     // 현재 페이지
    private int eventBeginPage; // 내비게이션의 첫번째 페이지
    private int eventEndPage;   // 내비게이션의 마지막 페이지
    private boolean eventShowPrev;  // 이전 페이지로 이동하는 링크를 보여줄 것인지의 여부
    private boolean eventShowNext;  // 다음 페이지로 이동하는 링크를 보여줄 것인지의 여부

    public EventPageHandler(int eventTotalCnt,int eventPage){
        this(eventTotalCnt,eventPage,10);
    }

    public EventPageHandler(int eventTotalCnt, int eventPage,int eventPageSize){
   this.eventTotalCnt = eventTotalCnt;
   this.eventPage = eventPage;
   this.eventPageSize = eventPageSize;


   eventTotalPage = (int)Math.ceil(eventTotalCnt / (double)eventPageSize);
   eventBeginPage = (eventPage-1) / eventNaviSize * eventNaviSize + 1;
   eventEndPage = Math.min(eventBeginPage + eventNaviSize -1,eventTotalPage);
   eventShowPrev = eventBeginPage != 1;
   eventShowNext = eventEndPage != eventTotalPage;
    }
    void print(){
        System.out.println("eventPage = " + eventPage);
        System.out.print(eventShowPrev ? "[PREV] " : "");
        for(int i = eventBeginPage; i <= eventEndPage; i++){
            System.out.print(i +" ");
        }
        System.out.println(eventShowNext ? " [NEXT]" : "");

    }
}
