package com.devcamp.eztour.controller.rvw;


import com.devcamp.eztour.domain.rvw.RvwCmtDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


/**
 * [추가 해야하는 사항] - 1
 * 댓글은 회원가입 상태에서만 적을 수 있음.
 * 비회원도 댓글 박스도 적을 수는 있지만, 등록 시 팝업창이 뜨면서 회원가입으로 유도하기
 */
@Controller
public class RvwCmtController {
    @GetMapping("/comments") // comments?bno=1080 GET
    public List<RvwCmtDto> list(Integer rvw_no) {


    }


}
