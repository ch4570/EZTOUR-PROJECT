package com.devcamp.eztour.controller.customercenter;

import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import com.devcamp.eztour.domain.customercenter.CustomerPageHandler;
import com.devcamp.eztour.service.customerCenter.CustomerCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
public class CustomerCenterController {
    @Autowired
    private CustomerCenterService customerCenterService;

//로그인 상태 확인
    @GetMapping("/inquirylist")
    public String list(Integer page, Integer pageSize, HttpServletRequest request, Model m) {
        if(!loginCheck(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        if(page==null) page=1;
        if(pageSize==null) pageSize=10;
//페이징 처리

        try {
            int totalCnt = customerCenterService.getCountCustomerInquiry();
            CustomerPageHandler customerPageHandler = new CustomerPageHandler(totalCnt, page, pageSize);

            Map map = new HashMap();
            map.put("offset", (page-1)*pageSize);
            map.put("pageSize", pageSize);

            List<CustomerInquiryDto> list = customerCenterService.getPageCustomerInquiry(map);
            m.addAttribute("list", list);
            m.addAttribute("cph", customerPageHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
//페이징 처리

        return "customer_inquiry_list"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id")!=null;
    }

//    상세내역 insert 페이지 보기
    @GetMapping("/inquirywrite")
    public String writeCustomerInquiry() {
        return "customer/customer_inquiry_write.tiles";
    }

//상세내역 insert 등록하기
    @PostMapping("/inquiryWrite")
    public String writeCustomerInquiry(CustomerInquiryDto customerInquiryDto, HttpSession session, Model m, RedirectAttributes rattr){
        String writer = (String)session.getAttribute("usr_id");
//        매개변수는 무엇으로해야할까?? session에서 가져온 usr_id를 담은 writer를 가져온다.
        customerInquiryDto.setUsr_id(writer);

        try {
            int rowCnt = customerCenterService.writeCustomerInquiry(customerInquiryDto);
            if(rowCnt!=1)
                throw new Exception("Write failed");
//            성공시 리스트로보냄
            rattr.addFlashAttribute("msg", "WRT_OK");
            return "redirect:/customer/customer_inquiry_list";

        } catch (Exception e) {
            e.printStackTrace();
//            등록 실패 시 남겨야 할 데이터내용
            m.addAttribute("customerInquiryDto", customerInquiryDto);
            m.addAttribute("msg", "WRT_ERR");
//            실패시 다시 작성 폼으로 보냄
            return "customer/customer_inquiry_write.tiles";
        }
    }
}
