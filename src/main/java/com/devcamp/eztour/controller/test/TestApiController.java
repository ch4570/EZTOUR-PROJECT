package com.devcamp.eztour.controller.test;

import com.devcamp.eztour.domain.test.FoodDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class TestApiController {

    @GetMapping("/food/api")
    public Map<String, Object> foodApi(@RequestParam(name = "criteria", required = false) String criteria) {
        Map<String, Object> foodsMap = new HashMap<>();
        if ("회" .equals(criteria)) {

            List<FoodDto> foodList = Arrays.asList(new FoodDto("회", "광어", 200),
                    new FoodDto("회", "우럭", 300),
                    new FoodDto("회", "대방어", 500));

            foodsMap.put("body", foodList);
        }

        return foodsMap;
    }

}
