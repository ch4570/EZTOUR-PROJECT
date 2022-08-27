package com.devcamp.eztour.controller.product;

import com.devcamp.eztour.domain.product.TrvPrdDtlDto;
import com.devcamp.eztour.domain.product.TrvPrdDtlReadDto;
import com.devcamp.eztour.service.product.ProductService;
import com.devcamp.eztour.service.productDetail.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class RestProductController {

    private final ProductDetailService productDetailService;

    private final ProductService productService;

    @PostMapping("/attractive/deleteAll")
    public ResponseEntity<String> deleteAllProductAttractive(String usr_id) throws Exception{
        int result = productDetailService.removeAllProductAttractive(usr_id);

        if(result == 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PostMapping("/attractive/delete")
    public ResponseEntity<String> deleteProductAttractive(String usr_id, String prd_cd) throws Exception{
        Map map = new HashMap();
        map.put("usr_id",usr_id);
        map.put("prd_cd",prd_cd);

        int result = productDetailService.removeProductAttractive(map);

        if(result == 1){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/like/delete")
    public ResponseEntity<String> likeDelete(String prd_cd, String usr_id) throws Exception{
        Map<String,String> map = new HashMap<>();

        map.put("prd_cd",prd_cd);
        map.put("usr_id",usr_id);

        int result = productService.removeUserLike(map);

        if(result == 1){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/like/insert")
    public ResponseEntity<String> likeInsert(String prd_cd,String usr_id,String prd_nm,Integer prd_str_prc) throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("prd_cd",prd_cd);
        map.put("usr_id",usr_id);
        map.put("prd_nm",prd_nm);
        map.put("prd_str_prc",prd_str_prc);

        int result = productService.addUserLike(map);
        if(result == 1){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/show/delete")
    public ResponseEntity<List<TrvPrdDtlReadDto>> deleteProduct(String prd_cd, HttpSession session) {

        boolean result = false;
        List<TrvPrdDtlReadDto> trvList = (List<TrvPrdDtlReadDto>)session.getAttribute("trvList");
        session.removeAttribute("trvList");
        for(int i=0; i< trvList.size(); i++){
            System.out.println(trvList.get(i).getPrd_cd());
            if(trvList.get(i).getPrd_cd().equals(prd_cd)){
                trvList.remove(i);
                result = true;
            }
        }
        session.setAttribute("trvList",trvList);

        if(result){
            return new ResponseEntity<>(trvList,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(trvList,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detailList")
    public ResponseEntity<List<TrvPrdDtlDto>> getMoreList(String prd_cd) {
        List<TrvPrdDtlDto> productList = null;
        try {
            productList = productDetailService.getAllDetailProduct(prd_cd);
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/show")
    public ResponseEntity<TrvPrdDtlReadDto> showProduct(String prd_dtl_cd, HttpSession session) throws Exception {
        TrvPrdDtlReadDto trvPrdDtlReadDto = productService.getRecentlyProduct(prd_dtl_cd);

        List<TrvPrdDtlReadDto> trvList = null;

        if(session.getAttribute("trvList") == null){
            trvList = new ArrayList<>();
        }else{
            trvList = (List<TrvPrdDtlReadDto>)session.getAttribute("trvList");
        }

        for(TrvPrdDtlReadDto t: trvList){
            if(trvPrdDtlReadDto.getPrd_cd().equals(t.getPrd_cd())){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        if(trvList.size() == 15){
            trvList.remove(0);
        }

        if(trvPrdDtlReadDto==null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }else{

            trvList.add(trvPrdDtlReadDto);
            session.setAttribute("trvList",trvList);
            session.setMaxInactiveInterval(60*60*2);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }
}
