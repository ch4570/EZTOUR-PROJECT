package com.devcamp.eztour.controller.product;

import com.devcamp.eztour.domain.product.PrdDtlPageDto;
import com.devcamp.eztour.domain.product.TrvPrdDtlReadDto;
import com.devcamp.eztour.domain.user.UserDto;
import com.devcamp.eztour.service.product.ProductService;
import com.devcamp.eztour.service.productDetail.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
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

    @GetMapping("/detail")
    public String getProductDetail(Model m, String prd_dtl_cd) throws Exception{
        System.out.println(prd_dtl_cd);
        PrdDtlPageDto prdDtlPageDto = productDetailService.getProductDetailPage(prd_dtl_cd);
        m.addAttribute("prdDto",prdDtlPageDto);
        System.out.println(prdDtlPageDto);
        return "product/product_detail.tiles";
    }

    @GetMapping("recent/list")
    public String getRecentProducts(String prd_cd,Model m,String keyword, String standard) throws Exception{
        TrvPrdDtlReadDto readDto = productDetailService.getOneProductDetail(prd_cd);
        m.addAttribute("list",readDto);
        return "product/product_recently.tiles";
    }

    @GetMapping("/attractive")
    public String getProductAttractive(HttpSession session, Model m) throws Exception{
        UserDto userDto = (UserDto)session.getAttribute("userDto");

        if(userDto == null){
            return "redirect:/user/login";
        }

        List<TrvPrdDtlReadDto> list = productDetailService.getProductAttractive(userDto.getUsr_id());
        int cnt = productDetailService.getProductAttractiveCnt(userDto.getUsr_id());
        m.addAttribute("list",list);
        m.addAttribute("cnt",cnt);

        return "product/product_attractive.tiles";
    }

}