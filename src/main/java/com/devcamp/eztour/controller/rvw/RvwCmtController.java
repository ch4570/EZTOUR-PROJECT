package com.devcamp.eztour.controller.rvw;


import com.devcamp.eztour.domain.rvw.RvwCmtDto;
import com.devcamp.eztour.domain.rvw.RvwDto;
import com.devcamp.eztour.service.rvwCmt.RvwCmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * [추가 해야하는 사항] - 1
 * 댓글은 회원가입 상태에서만 적을 수 있음
 * 비회원도 댓글 박스에 적을 수 있지만, 등록 시 팝업창이 뜨면서 회원가입으로 유도하기
 */
//@Controller
//@ResponseBody
@RestController
public class RvwCmtController {
    @Autowired
    RvwCmtService rvwCmtService;
//    {
//        "pcmt":0,
//         "cmt_cont":"hihihi",
//            "usr_nm":"푸틴"
//    }

    // 댓글 수정 메서드
    @PatchMapping("/comments/{cmt_no}") // eztour/comments/4
    public ResponseEntity<String> modify(@PathVariable Integer cmt_no, @RequestBody RvwCmtDto rvwCmtDto, HttpSession session) {
//        String usr_id = (String) session.getAttribute("id");
        String usr_id = "to9251";
        String usr_nm = "푸틴";

        rvwCmtDto.setUsr_id(usr_id);
        rvwCmtDto.setUsr_nm(usr_nm);
        rvwCmtDto.setCmt_no(cmt_no);
        System.out.println("rvwCmtDto = " + rvwCmtDto);

        try {
            if(rvwCmtService.modify(rvwCmtDto)!=1)
                throw new Exception("Modify failed");

            return new ResponseEntity<>("MOD_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("MOD_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    // 댓글을 등록하는 메서드
    @PostMapping("/comments") // eztour/comments?rvw_no=2 POST
    public ResponseEntity<String> remove(@RequestBody RvwCmtDto rvwCmtDto, Integer rvw_no, HttpSession session) {
    String usr_id = "to9251";
    String usr_nm = "푸틴";


    rvwCmtDto.setUsr_id(usr_id);
    rvwCmtDto.setUsr_nm(usr_nm);
    rvwCmtDto.setRvw_no(rvw_no);
    System.out.println("rvwCmtDto = " + rvwCmtDto);


    try {
        if(rvwCmtService.write(rvwCmtDto)!=1)
            throw new Exception("Write failed");

        return new ResponseEntity<>("WRT_OK", HttpStatus.OK);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("WRT_ERR", HttpStatus.BAD_REQUEST);
    }
}

    // 지정된 댓글을 삭제하는 메서드
    @DeleteMapping("/comments/{cmt_no}") // comments/3?rvw_no=2 <-- 삭제할 댓글 번호
    public ResponseEntity<String> remove(@PathVariable Integer cmt_no, Integer rvw_no, HttpSession session) {
//        String usr_id = (String)session.getAttribute("id"); // 나중에 회원 붙이는 사용하기
        String usr_nm = "푸틴";
        try {
            int rowCnt = rvwCmtService.remove(cmt_no, rvw_no, usr_nm);

            if(rowCnt!=1)
                throw new Exception("Delete Failed");

            return new ResponseEntity<>("DEL_OK", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
        }
    }

    // 지정된 게시물의 모든 댓글을 가져오는 메서드
    @GetMapping("/comments") // comments?rvw_no=1080 GET
    public ResponseEntity<List<RvwCmtDto>> list(Integer rvw_no) {
        List<RvwCmtDto> list = null;
        try {
            list = rvwCmtService.getList(rvw_no);
            return new ResponseEntity<List<RvwCmtDto>>(list, HttpStatus.OK); // 200 success

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<RvwCmtDto>>(HttpStatus.BAD_REQUEST); // 400 error
        }
    }


}
