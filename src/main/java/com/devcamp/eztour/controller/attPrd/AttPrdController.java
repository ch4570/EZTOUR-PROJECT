package com.devcamp.eztour.controller.attPrd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/attPrd")
public class AttPrdController {

    //1. 유저가 관심 상품 테이블에 있고 AND 좋아요 여부 = 0 이면 update 좋아요 여부 = 1로 변경
    //2. 유저가 관심 상품 테이블에 있고 AND 좋아요 여부 = 1 이면 update 좋아요 여부 = 0로 변경
    //3. 유저가 관심 상품 테이블에 없으면 insert


}
