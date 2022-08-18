package com.devcamp.eztour.controller.productDetail;

import com.devcamp.eztour.domain.product.TrvPrdDtlReadDto;
import com.devcamp.eztour.domain.productDetail.TrvPrdDetailDto;
import com.devcamp.eztour.service.productDetail.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductDetailController {

    private final ProductDetailService productDetailService;

    @GetMapping("/productList")
    public String getAllProduct(Model m) {

        try {
            List<TrvPrdDtlReadDto> list = productDetailService.getAllProduct();
            m.addAttribute("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "product_detail/product_list.tiles";
    }
}
