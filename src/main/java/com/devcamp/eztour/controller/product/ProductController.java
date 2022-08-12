package com.devcamp.eztour.controller.product;

import com.devcamp.eztour.domain.product.*;
import com.devcamp.eztour.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;


@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product/insert")
    public String insertProduct(){
        return "product_Insert";
    }

    @PostMapping("/product/insert")
    public String insertProduct(Trv_prd_dto trv_prd_dto,RedirectAttributes redirectAttributes){

        int result = productService.insertProduct(trv_prd_dto);
        if(result == 1){
            redirectAttributes.addAttribute("prd_cd",trv_prd_dto.getPrd_cd());
           redirectAttributes.addAttribute("prd_str_prc",trv_prd_dto.getPrd_str_prc());
            return "redirect:/product/detail/insert";
        }else{
            redirectAttributes.addFlashAttribute("error_msg","상품 등록에 실패했습니다.");
            return "redirect:/product/insert";
        }

    }

    @GetMapping("/product/detail/insert")
    public String productDetailInsert(){
        return "product_detail_insert";
    }

    @PostMapping("/product/detail/insert")
    public String productDetailInsert(Trv_prd_dtl_dto trv_prd_dtl_dto,RedirectAttributes redirectAttributes){
        int result = productService.insertProductDetail(trv_prd_dtl_dto);
        if(result==1){
            redirectAttributes.addAttribute("prd_dtl_cd",trv_prd_dtl_dto.getPrd_dtl_cd());
            redirectAttributes.addAttribute("prd_cd",trv_prd_dtl_dto.getPrd_cd());
            return "redirect:/product/insert/price";
        }else{
            redirectAttributes.addFlashAttribute("error_msg","상품 상세 등록이 실패하였습니다.");
            return "redirect:/product/detail/insert";
        }
    }

    @GetMapping("/product/insert/price")
    public String insertProductPrice(){
        return "product_insert_price";
    }

    @PostMapping("/product/insert/price")
    public String insertProductPrice(Trv_prd_prc_dto trv_prd_prc_dto, RedirectAttributes redirectAttributes){
        int result = productService.insertProductPrice(trv_prd_prc_dto);
        if(result==1){
            redirectAttributes.addAttribute("prd_cd",trv_prd_prc_dto.getPrd_cd());
            return "redirect:/product/insert/schedule";
        }else{
            redirectAttributes.addAttribute("error_msg","가격 추가에 실패하였습니다.");
            return "redirect:/product/insert/price";
        }

    }

    @GetMapping("/product/insert/schedule")
    public String insertProductSchedule(){
        return "product_insert_sch";
    }


    @PostMapping("/product/insert/schedule")
    public String insertProductSchedule(Trv_sch_dto trv_sch_dto, RedirectAttributes redirectAttributes){
        System.out.println(trv_sch_dto);
        int result = productService.insertProductSchedule(trv_sch_dto);
        if(result==1){
            redirectAttributes.addAttribute("prd_cd",trv_sch_dto.getPrd_cd());
            return "redirect:/product/insert/image";
        }else{
            redirectAttributes.addAttribute("error_msg","오류가 발생하였습니다.");
            return "";
        }
    }


    @GetMapping("/product/insert/image")
    public String insertProductImage(){
        return "product_img_insert";
    }

    @ResponseBody
    @PostMapping("/product/insert/image")
    public String insertProductImage(MultipartFile img_file, HttpServletRequest request, String prd_cd) throws IOException {
        // 원본 파일이 이미지 파일이 맞는지 확장자를 확인
        File checkFile = new File(img_file.getOriginalFilename());
        String type = Files.probeContentType(checkFile.toPath());
        // 프로젝트 root 경로 확인 -> 이미지 경로 잡기
        HttpSession session = request.getSession();
        String root_path = session.getServletContext().getRealPath("/");
        String uploadPath = root_path+"resources/image/product";
        // 이미지 파일이 아닐경우 실패
        if(!type.startsWith("image")){
            return "fail";
        }else{
            String fileName = UUID.randomUUID().toString()+".jpg";
            File uploadFile = new File(uploadPath, fileName);
            img_file.transferTo(uploadFile);
            String finalPath = "/image/product/"+fileName;
            Prd_img_dto prd_img_dto = new Prd_img_dto(prd_cd,finalPath);
            productService.insertProductImg(prd_img_dto);
            return "success";
        }

    }
}
