package com.devcamp.eztour.domain.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FoodDto {
    private String kindValue;
    private String name;
    private int kcal;
}
