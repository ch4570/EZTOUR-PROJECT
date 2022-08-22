package com.devcamp.eztour.controller.product;

import com.devcamp.eztour.domain.product.PrdImgDto;
import com.devcamp.eztour.domain.product.PrdOptionDto;
import com.devcamp.eztour.domain.product.TrvSchImgDto;
import com.devcamp.eztour.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RestAdminController {

    private final ProductService productService;

    // 관리 상품 이미지 등록(ajax 사용 정보 전송)
    @PostMapping("/product/insert/image")
    public String insertProductImage(MultipartFile img_file, HttpServletRequest request, String prd_cd, String frs_rgs_no) throws Exception{
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
                File uploadFile = new File(uploadPath, fileName);
                img_file.transferTo(uploadFile);
                String finalPath = "/image/product/"+fileName;
                PrdImgDto prd_imgDto = new PrdImgDto(prd_cd,finalPath,frs_rgs_no);
                int result = productService.insertProductImg(prd_imgDto);
                if(result == 1){
                    return "success";
                }else{
                    return "fail";
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // 관리 상품 일정 등록(ajax 사용 정보 전송)
    @PostMapping("/product/schedule/image/insert")
    public String insertScheduleImage(MultipartHttpServletRequest meq, HttpServletRequest request, int sch_no, String prd_cd, String frs_rgs_no) throws Exception{
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
                type = Files.probeContentType(checkFile.toPath());
                if(!type.startsWith("image")){
                    return "fail";
                }else if(type == null){
                    return "fail";
                }else{
                    String fileName = UUID.randomUUID().toString() + ".jpg";
                    File uploadFile = new File(uploadPath, fileName);
                    m.transferTo(uploadFile);
                    String finalPath = "/image/product/sights/" + fileName;
                    TrvSchImgDto trv_schImgDto = new TrvSchImgDto(sch_no, prd_cd,finalPath,frs_rgs_no);
                    productService.insertScheduleImage(trv_schImgDto);
                    result++;
                }

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

    // 상품 삭제(ajax 사용정보 전송)
    @PostMapping("/product/delete")
    public String productDelete(String prd_cd) throws Exception{
        int result = productService.deleteProduct(prd_cd);
        if(result==1){
            return "success";
        }else{
            return "fail";
        }
    }

    @PostMapping("/product/detail/delete")
    public String productDetailDelete(String prd_dtl_cd) throws Exception{
        int result = productService.deleteProductDetail(prd_dtl_cd);
        if(result==1){
            return "success";
        }else{
            return "fail";
        }
    }

    @PostMapping("/product/image/delete")
    public String productImageDelete(int prd_img_no,String img_pth,HttpServletRequest request) throws Exception{
        int result = productService.deleteProductImage(prd_img_no);

        if(result==1){
            // 이미지를 삭제
            boolean flag = deleteImage(request,img_pth);
            if(flag){
                return "success";
            }else{
                return "fail";
            }
        }
        return "fail";
    }

    @PostMapping("/product/image/modify")
    public String productImageModify(MultipartFile img_file,String img_pth,String prd_cd,Integer prd_img_no,
                                     HttpServletRequest request) throws Exception{
        // 원본 파일이 이미지 파일이 맞는지 확장자를 확인
        File checkFile = new File(img_file.getOriginalFilename());
        String type = null;
        try {
            type = Files.probeContentType(checkFile.toPath());
            // 프로젝트 root 경로 확인 -> 이미지 경로 잡기
            HttpSession session = request.getSession();
            String root_path = session.getServletContext().getRealPath("/");
            String uploadPath = root_path+"resources/image/product";
            boolean flag = deleteImage(request,img_pth);
            if(flag){
                // 이미지 파일이 아닐경우 실패
                if(!type.startsWith("image")){
                    return "fail";
                }else if(type==null){
                    return "fail";
                }else{
                    String fileName = UUID.
                            randomUUID().toString()+".jpg";
                    File uploadFile = new File(uploadPath, fileName);
                    img_file.transferTo(uploadFile);
                    String finalPath = "/image/product/"+fileName;
                    PrdImgDto prdImgDto = new PrdImgDto(finalPath,prd_img_no,prd_cd);
                    int result = productService.updateProductImage(prdImgDto);
                    if(result == 1){
                        return "success";
                    }else{
                        return "fail";
                    }

                }
            }else{
                return "fail";
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/product/schedule/delete")
    public String productScheduleDelete(int sch_no) throws Exception{
        int result = productService.removeSchedule(sch_no);
        if(result == 1){
            return "success";
        }else{
            return "fail";
        }
    }

    @PostMapping("/product/price/delete")
    public String productPriceDelete(int prd_prc_no) throws Exception{
        System.out.println(prd_prc_no);
        int result = productService.removeProductPrice(prd_prc_no);
        if(result == 1){
            return "success";
        }else{
            return "fail";
        }
    }

    @PostMapping("/product/schedule/image/delete")
    public String productScheduleImageDelete(HttpServletRequest request, String[] img_pth, String prd_cd) throws Exception{
        int ImageDelCnt = 0;
        for(String s : img_pth){
            boolean flag = deleteImage(request,s);
            if(flag){
                ImageDelCnt++;
            }
        }
        int dataDelCnt = productService.removeScheduleImage(prd_cd);
        if(ImageDelCnt == 3 && dataDelCnt == 3){
            return "success";
        }else{
            return "fail";
        }
    }

    @PostMapping("/product/recognize/modify")
    public String productRecognizeModify(String prd_cd, boolean act_yn) throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("prd_cd",prd_cd);

        if(act_yn){
            map.put("act_yn",false);
        }else{
            map.put("act_yn",true);
        }

        int result = productService.modifyActivateStatus(map);

        if(result == 1){
            return "success";
        }else{
            return "fail";
        }
    }

    @PostMapping("/product/option")
    public ResponseEntity<List<PrdOptionDto>> productOption(String option) throws Exception{
        List<PrdOptionDto> optionList = productService.getProductOption(option);

        if(option == null || option == ""){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<List<PrdOptionDto>>(optionList,HttpStatus.OK);
        }

    }

    private boolean deleteImage(HttpServletRequest request,String img_pth){
        HttpSession session = request.getSession();
        String root_path = session.getServletContext().getRealPath("/");
        String uploadPath = root_path+"resources"+img_pth;
        File file = new File(uploadPath);
        boolean flag = file.delete();
        return flag;
    }
}
