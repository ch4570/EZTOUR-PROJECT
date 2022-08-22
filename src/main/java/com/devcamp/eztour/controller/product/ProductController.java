package com.devcamp.eztour.controller.product;

import com.devcamp.eztour.domain.product.TrvPrdDtlDto;
import com.devcamp.eztour.domain.product.TrvPrdDtlReadDto;
import com.devcamp.eztour.service.product.ProductService;
import com.devcamp.eztour.service.productDetail.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.app.event.implement.EscapeXmlReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String getAllProduct(Model m,String cntn_cd,String nt_cd, String nt_cd_nm) throws Exception{

        if(cntn_cd == null || nt_cd == null){
            try {
                List<TrvPrdDtlReadDto> list = productDetailService.getAllProduct();
                m.addAttribute("list", list);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "product/product_list.tiles";
        }else{
            Map<String,String> map = new HashMap<>();
            map.put("cntn_cd",cntn_cd);
            map.put("nt_cd",nt_cd);
            map.put("nt_cd_nm",nt_cd_nm);
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

    @GetMapping("/detail")
    public String getProductDetail(Model m, String prd_dtl_cd) {


        return "product/product_detail.tiles";
    }

}