package com.devcamp.eztour.controller.product;

import com.devcamp.eztour.domain.product.*;
import com.devcamp.eztour.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;


@Controller
public class AdminController {

    @Autowired
    ProductService productService;

    // 관리자 페이지 진입
    @GetMapping("/product/admin")
    public String productAdmin(HttpSession session, RedirectAttributes rattr){
        String id = (String)session.getAttribute("usr_id");
        if(id==null || !id.equals("admin")){
            rattr.addFlashAttribute("admin_error_msg","권한이 없습니다. 홈페이지로 이동합니다.");
            return "redirect:/";
        }else{
            return "product/admin_main.tiles";
        }
    }

    // 관리자 상품 등록 페이지 진입
    @GetMapping("/product/insert")
    public String insertProduct(HttpSession session,RedirectAttributes rattr){
        String id = (String)session.getAttribute("usr_id");
        if(id==null || !id.equals("admin")){
            rattr.addFlashAttribute("admin_error_msg","권한이 없습니다. 홈페이지로 이동합니다.");
            return "redirect:/";
        }else{
            return "product/product_insert.tiles";
        }

    }

    // 관리자 상품 등록(실제 정보 전송)
    @PostMapping("/product/insert")
    public String insertProduct(TrvPrdWriteDto trvPrdWriteDto, RedirectAttributes redirectAttributes) throws Exception{
        
        int result = productService.insertProduct(trvPrdWriteDto);

        if(result == 1){
            redirectAttributes.addAttribute("prd_cd", trvPrdWriteDto.getPrd_cd());
            return "redirect:/product/detail/insert";
        }else{
            redirectAttributes.addFlashAttribute("error_msg","상품 등록에 실패했습니다.");
            return "redirect:/product/insert";
        }

    }

    // 관리자 상품 상세 등록 페이지 진입
    @GetMapping("/product/detail/insert")
    public String productDetailInsert(HttpSession session,RedirectAttributes rattr){
        String id = (String)session.getAttribute("usr_id");
        if(id==null || !id.equals("admin")){
            rattr.addFlashAttribute("admin_error_msg","권한이 없습니다. 홈페이지로 이동합니다.");
            return "redirect:/";
        }else{
            return "product/product_detail_insert.tiles";
        }

    }

    // 관리자 상품 상세 등록(실제 정보 전송)
    @PostMapping("/product/detail/insert")
    public String productDetailInsert(TrvPrdDtlDto trv_prdDtlDto, RedirectAttributes redirectAttributes){
        int result = productService.insertProductDetail(trv_prdDtlDto);
        if(result==1){
            redirectAttributes.addAttribute("prd_dtl_cd", trv_prdDtlDto.getPrd_dtl_cd());
            redirectAttributes.addAttribute("prd_cd", trv_prdDtlDto.getPrd_cd());
            return "redirect:/product/insert/price";
        }else{
            redirectAttributes.addFlashAttribute("error_msg","상품 상세 등록이 실패하였습니다.");
            return "redirect:/product/detail/insert";
        }
    }

    // 관리자 상품 가격 등록 페이지 진입
    @GetMapping("/product/insert/price")
    public String insertProductPrice(HttpSession session,RedirectAttributes rattr){
        String id = (String)session.getAttribute("usr_id");
        if(id==null || !id.equals("admin")){
            rattr.addFlashAttribute("admin_error_msg","권한이 없습니다. 홈페이지로 이동합니다.");
            return "redirect:/";
        }else{
            return "product/product_insert_price.tiles";
        }

    }

    // 관리자 상품 가격 등록(실제 정보 전송)
    @PostMapping("/product/insert/price")
    public String insertProductPrice(TrvPrdPrcDto trv_prdPrcDto, RedirectAttributes redirectAttributes){
        int result = productService.insertProductPrice(trv_prdPrcDto);
        if(result==1){
            redirectAttributes.addAttribute("prd_cd", trv_prdPrcDto.getPrd_cd());
            return "redirect:/product/insert/schedule";
        }else{
            redirectAttributes.addAttribute("error_msg","가격 추가에 실패하였습니다.");
            return "redirect:/product/insert/price";
        }

    }

    // 관리자 상품 일정 등록 페이지 진입
    @GetMapping("/product/insert/schedule")
    public String insertProductSchedule(HttpSession session, RedirectAttributes rattr){
        String id = (String)session.getAttribute("usr_id");
        if(id==null || !id.equals("admin")){
            rattr.addFlashAttribute("admin_error_msg","권한이 없습니다. 홈페이지로 이동합니다.");
            return "redirect:/";
        }else{
            return "product/product_insert_sch.tiles";
        }

    }

    // 관리자 상품 일정 등록(상세 정보 전송)
    @PostMapping("/product/insert/schedule")
    public String insertProductSchedule(TrvSchDto trv_schDto, RedirectAttributes redirectAttributes){
        int result = productService.insertProductSchedule(trv_schDto);
        if(result==1){
            redirectAttributes.addAttribute("prd_cd", trv_schDto.getPrd_cd());
            return "redirect:/product/insert/image";
        }else{
            redirectAttributes.addAttribute("error_msg","오류가 발생하였습니다.");
            return "redirect:/product/insert/schedule";
        }
    }

    // 관리 상품 이미지 등록 페이지 진입
    @GetMapping("/product/insert/image")
    public String insertProductImage(HttpSession session,RedirectAttributes rattr){
            String id = (String)session.getAttribute("usr_id");
            if(id==null || !id.equals("admin")){
                rattr.addFlashAttribute("admin_error_msg","권한이 없습니다. 홈페이지로 이동합니다.");
                return "redirect:/";
            }else{
                return "product/product_img_insert.tiles";
            }
    }

    // 관리 상품 이미지 등록(ajax 사용 정보 전송)
    @PostMapping("/product/insert/image")
    @ResponseBody
    public String insertProductImage(MultipartFile img_file, HttpServletRequest request, String prd_cd){
        // 원본 파일이 이미지 파일이 맞는지 확장자를 확인
        File checkFile = new File(img_file.getOriginalFilename());
        String type = null;
        try {
            type = Files.probeContentType(checkFile.toPath());
            // 프로젝트 root 경로 확인 -> 이미지 경로 잡기
            HttpSession session = request.getSession();
            String root_path = session.getServletContext().getRealPath("/");
            String uploadPath = root_path+"resources/image/product";
            // 이미지 파일이 아닐경우 실패
            if(!type.startsWith("image")){
                return "fail";
            }else if(type==null){
                return "fail";
            }else{
                String fileName = UUID.
                        randomUUID().toString()+".jpg";
                System.out.println(fileName);
                File uploadFile = new File(uploadPath, fileName);
                img_file.transferTo(uploadFile);
                String finalPath = "/image/product/"+fileName;
                PrdImgDto prd_imgDto = new PrdImgDto(prd_cd,finalPath);
                productService.insertProductImg(prd_imgDto);
                return "success";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // 관리 상품 일정 페이지 진입
    @GetMapping("/product/schedule/image/insert")
    public String insertScheduleImage(HttpSession session,RedirectAttributes rattr){
        String id = (String)session.getAttribute("usr_id");
        if(id==null || !id.equals("admin")){
            rattr.addFlashAttribute("admin_error_msg","권한이 없습니다. 홈페이지로 이동합니다.");
            return "redirect:/";
        }else{
            return "product/product_sch_img_insert.tiles";
        }
    }

    // 관리 상품 일정 등록(ajax 사용 정보 전송)
    @ResponseBody
    @PostMapping("/product/schedule/image/insert")
    public String insertScheduleImage(MultipartHttpServletRequest meq, HttpServletRequest request, int sch_no, String prd_cd) {
        int result = 0;
        String type = null;
        // 프로젝트 root 경로 확인 -> 이미지 경로 잡기
        HttpSession session = request.getSession();
        String root_path = session.getServletContext().getRealPath("/");
        String uploadPath = root_path + "resources/image/product/sights";

        List<MultipartFile> fileList =  meq.getFiles("prd_img");
        for (MultipartFile m : fileList) {
            File checkFile = new File(m.getOriginalFilename());
            try {

                    String fileName = UUID.randomUUID().toString() + ".jpg";
                    File uploadFile = new File(uploadPath, fileName);
                    m.transferTo(uploadFile);
                    String finalPath = "/image/product/sights/" + fileName;
                    TrvSchImgDto trv_schImgDto = new TrvSchImgDto(sch_no, prd_cd, finalPath);
                    productService.insertScheduleImage(trv_schImgDto);
                    result++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(result == 3){
            return "success";
        }else{
            return "fail";
        }
    }

    // 상품 관리 페이지 진입(리스트 뿌리기)
    @GetMapping("/product/management")
    public String productManagement(HttpSession session, Model model, @RequestParam(value = "page",defaultValue = "1") int page
                                    ,String search_option,String search_keyword){
        String id = (String)session.getAttribute("usr_id");
        if(id==null || !id.equals("admin")){
            return "redirect:/";
        }else{
            if(search_keyword==null || search_keyword==""){
                int totalCnt = productService.selectProductAdminCnt();
                PageHandlerProduct paging = new PageHandlerProduct(totalCnt,page);
                List<TrvPrdReadDto> prdDtoList = productService.selectProductAdmin(paging);
                model.addAttribute("prd_list",prdDtoList);
                model.addAttribute("paging",paging);
                return "product/product_management.tiles";
            }else{
                PageHandlerProduct option = new PageHandlerProduct(search_option,search_keyword);
                int totalCnt = productService.searchSelectProductAdminCnt(option);
                PageHandlerProduct paging = new PageHandlerProduct(totalCnt,page,search_option,search_keyword);
                List<TrvPrdReadDto> prdDtoList = productService.searchSelectProductAdmin(paging);
                model.addAttribute("prd_list",prdDtoList);
                model.addAttribute("paging",paging);
                return "product/product_management.tiles";
            }
        }
    }

    // 상품 관리 페이지 상품 정보 읽기
    @GetMapping("/product/read")
    public String productRead(HttpSession session, String prd_cd,Model model){
        String id = (String)session.getAttribute("usr_id");
        if(id==null || !id.equals("admin")){
            return "redirect:/";
        }else{
            TrvPrdReadDto trvPrdDto = productService.selectProduct(prd_cd);
            model.addAttribute("trvPrdDto",trvPrdDto);
            return "product/product_read.tiles";
        }
    }

    // 상품 수정 페이지 진입
    @GetMapping("/product/modify")
    public String productModify(HttpSession session, String prd_cd,Model model){
        String id = (String)session.getAttribute("usr_id");
        if(id==null || !id.equals("admin")){
            return "redirect:/";
        }else{
            TrvPrdReadDto trvPrdDto = productService.selectProduct(prd_cd);
            model.addAttribute("trvPrdDto",trvPrdDto);
            return "product/product_modify.tiles";
        }
    }

    // 상품 수정(실제 정보 전송)
    @PostMapping("/product/modify")
    public String productModify(TrvPrdWriteDto trvPrdWriteDto,RedirectAttributes redirectAttributes){
            int result = productService.updateProduct(trvPrdWriteDto);
            if(result==1){
                redirectAttributes.addFlashAttribute("success_msg","상품 수정에 성공했습니다.");
                return "redirect:/product/management";
            }else{
                redirectAttributes.addFlashAttribute("error_msg","상품 수정에 실패했습니다.");
                return "redirect:/product/modify?prd_cd="+trvPrdWriteDto.getPrd_cd();
            }
    }

    // 상품 삭제(ajax 사용정보 전송)
    @ResponseBody
    @PostMapping("/product/delete")
    public String productDelete(String prd_cd){
        int result = productService.deleteProduct(prd_cd);
        if(result==1){
            return "success";
        }else{
            return "fail";
        }
    }

    // 상품 상세 관리 페이지 진입(리스트 뿌리기)
    @GetMapping("/product/management/detail")
    public String productManagementDetail(HttpSession session, Model model, @RequestParam(value = "page",defaultValue = "1") int page
            ,String search_option,String search_keyword){
        String id = (String)session.getAttribute("usr_id");
        if(id==null || !id.equals("admin")){
            return "redirect:/";
        }else{
            if(search_option==null || search_option==""){
                int totalCnt = productService.selectProductAdminDetailCnt();
                PageHandlerProduct paging = new PageHandlerProduct(totalCnt,page);
                List<TrvPrdDtlDto> prdDetailList = productService.selectProductAdminDetail(paging);
                model.addAttribute("prd_dtl_list",prdDetailList);
                model.addAttribute("paging",paging);
                return "product/product_management_detail.tiles";
            }else{
                PageHandlerProduct option = new PageHandlerProduct(search_option,search_keyword);
                int totalCnt = productService.searchSelectProductAdminDetailCnt(option);
                System.out.println(totalCnt);
                PageHandlerProduct paging = new PageHandlerProduct(totalCnt,page,search_option,search_keyword);
                List<TrvPrdDtlDto> prdDetailList = productService.searchSelectProductAdminDetail(paging);
                model.addAttribute("prd_dtl_list",prdDetailList);
                model.addAttribute("paging",paging);
                return "product/product_management_detail.tiles";
            }
        }
    }
}
