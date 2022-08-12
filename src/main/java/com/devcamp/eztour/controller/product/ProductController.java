package com.devcamp.eztour.controller.product;

import com.devcamp.eztour.domain.product.Trv_prd_dto;
import com.devcamp.eztour.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/insert")
    public String insertProduct(){
        return "productInsert";
    }

    @PostMapping("/product/insert")
    public String insertProduct(String prd_cd, String dstn_cd, String cmn_cd_thm, String prd_nm, String prd_dtl_desc,
                                String trv_per, Integer prd_str_prc, MultipartFile img_file,
                                String dpr_str_date, String dpr_fin_date){
        Trv_prd_dto trv_prd_dto = new Trv_prd_dto(prd_cd,dstn_cd,cmn_cd_thm,prd_nm,prd_dtl_desc,trv_per,
                                        prd_str_prc,"img",dpr_str_date,dpr_fin_date);
        System.out.println(trv_prd_dto);
        int result = productService.insertProduct(trv_prd_dto);
        System.out.println(result);
        return "productInsert";
    }
}
