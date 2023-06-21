package com.devcamp.eztour.domain.reserv;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StatsGndrAndAgePerHourDto {
    private int teensCnt; //10대
    private int twentiesCnt;
    private int thirtiesCnt;
    private int fortiesCnt;
    private int fiftiesCnt;
    private int overSixtiesCnt; //60대 이상
    private int maleCnt; //남성 수
    private int femaleCnt; //여성 수
    private int hh; //시간
}
