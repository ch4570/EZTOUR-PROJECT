//package com.devcamp.eztour.controller.customercenter;
//
//import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
//import com.devcamp.eztour.service.customerCenter.CustomerInquiryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//@RequestMapping("/customer")
//public class CustomerCenterController {
//    @Autowired
//    private CustomerInquiryService customerInquiryService;
//
////    상세내역 insert 페이지 보기
//    @GetMapping("/inquiryWrite")
//    public String writeCustomerInquiry() {
//        return "customer/customer_inquiry_write.tiles";
//    }
//
////상세내역 insert 등록하기
//    @PostMapping("/inquiryWrite")
//    public String writeCustomerInquiry(CustomerInquiryDto customerInquiryDto, HttpSession session, Model m, RedirectAttributes rattr){
//        String writer = (String)session.getAttribute("usr_id");
////        매개변수는 무엇으로해야할까?? session에서 가져온 usr_id를 담은 writer를 가져온다.
//        customerInquiryDto.setUsr_id(writer);
//
//        try {
//            int rowCnt = customerInquiryService.writeCustomerInquiry(customerInquiryDto);
//            if(rowCnt!=1)
//                throw new Exception("Write failed");
////            성공시 리스트로보냄
//            rattr.addFlashAttribute("msg", "WRT_OK");
//            return "redirect:/customer/customer_inquiry_list";
//
//        } catch (Exception e) {
//            e.printStackTrace();
////            등록 실패 시 남겨야 할 데이터내용
//            m.addAttribute("customerInquiryDto", customerInquiryDto);
//            m.addAttribute("msg", "WRT_ERR");
////            실패시 다시 작성 폼으로 보냄
//            return "customer/customer_inquiry_write.tiles";
//        }
//    }
//}
