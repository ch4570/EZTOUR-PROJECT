package com.devcamp.eztour.controller.product;

import com.devcamp.eztour.domain.product.PrdDtlPageDto;
import com.devcamp.eztour.domain.product.TrvPrdDtlDto;
import com.devcamp.eztour.domain.product.TrvPrdDtlReadDto;
import com.devcamp.eztour.domain.user.UserDto;
import com.devcamp.eztour.service.product.ProductService;
import com.devcamp.eztour.service.productDetail.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductDetailService productDetailService;

    private final ProductService productService;

    @GetMapping("/list")
    public String getAllProduct(Model m, String cntn_cd, String nt_cd, String nt_cd_nm, String keyword, String standard,String usr_id) throws Exception{

        Map<String,String> map = new HashMap<>();

        int option = 0;

        if((cntn_cd == null || nt_cd == null) && (keyword == null || standard == null)){
            try {
                List<TrvPrdDtlReadDto> list = productDetailService.getUserLike();
                m.addAttribute("list", list);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "product/product_list.tiles";

        }else if(keyword != null && standard != null){
            map.put("keyword",keyword);
            map.put("standard",standard);
            map.put("cntn_cd",cntn_cd);
            map.put("nt_cd",nt_cd);
            map.put("nt_cd_nm",nt_cd_nm);
            map.put("usr_id",usr_id);

            if(keyword.equals("vcnt")){
                option = 1;
            }else if(keyword.equals("prd_str_prc") && standard.equals("ASC")){
                option = 2;
            }else if(keyword.equals("prd_str_prc") && standard.equals("DESC")){
                option = 3;
            }
            System.out.println(option);
            List<TrvPrdDtlReadDto> list = productDetailService.getAllProductOrder(map);
            m.addAttribute("list",list);
            m.addAttribute("option",option);
            return "product/product_list.tiles";
        }else{
            map.put("cntn_cd",cntn_cd);
            map.put("nt_cd",nt_cd);
            map.put("nt_cd_nm",nt_cd_nm);
            map.put("usr_id",usr_id);
            List<TrvPrdDtlReadDto> list = productDetailService.getAllProductCategory(map);
            m.addAttribute("list",list);
            return "product/product_list.tiles";
        }


    }

    @ResponseBody
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

    @ResponseBody
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

    @ResponseBody
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

    @ResponseBody
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

    @GetMapping("/detail")
    public String getProductDetail(Model m, String prd_dtl_cd) throws Exception{
        System.out.println(prd_dtl_cd);
        PrdDtlPageDto prdDtlPageDto = productDetailService.getProductDetailPage(prd_dtl_cd);
        m.addAttribute("prdDto",prdDtlPageDto);
        System.out.println(prdDtlPageDto);
        return "product/product_detail.tiles";
    }

}