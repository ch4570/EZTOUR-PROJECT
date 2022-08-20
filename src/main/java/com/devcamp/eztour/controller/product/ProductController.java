package com.devcamp.eztour.controller.product;

import com.devcamp.eztour.domain.product.TrvPrdDtlDto;
import com.devcamp.eztour.domain.product.TrvPrdDtlReadDto;
import com.devcamp.eztour.service.productDetail.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductDetailService productDetailService;

    @GetMapping("/list")
    public String getAllProduct(Model m) {

        try {
            List<TrvPrdDtlReadDto> list = productDetailService.getAllProduct();
            m.addAttribute("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "product/product_list.tiles";
    }

    @GetMapping("/detailList")
    public ResponseEntity<List<TrvPrdDtlDto>> getMoreList(String prd_cd) {
        List<TrvPrdDtlDto> productList = null;
        try {
            productList = productDetailService.getAllDetailProduct(prd_cd);
            System.out.println("productList = " + productList);
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}