package com.devcamp.eztour.controller.customercenter;

import com.devcamp.eztour.domain.customercenter.CustomerInquiryDto;
import com.devcamp.eztour.domain.customercenter.CustomerPageHandler;
import com.devcamp.eztour.domain.customercenter.CustomerPropDto;
import com.devcamp.eztour.domain.customercenter.CustomerSearchCondition;
import com.devcamp.eztour.domain.user.UserDto;
import com.devcamp.eztour.service.customerCenter.CustomerCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    //    prop 시작
    @GetMapping("/proplist")
    public String readCustomerProplist(Model m) {
        try {
            List<CustomerPropDto> list = customerCenterService.getAllCustomerProp();
            m.addAttribute("list", list);
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "customer/customer_proplist.tiles";
    }

    @GetMapping("/prop")
    public String readCustomerProp() {
        return "customer/customer_prop.tiles";
    }

    @PostMapping("/prop")
    public String writeCustomerProp(CustomerPropDto customerPropDto) {
        System.out.println(customerPropDto);
        return "redirect:/customer/proplist.tiles";
    }
//  prop 끝

    //inquiry시작
    @GetMapping("/inquirylist")
    public String readCustomerInquirylist(Model m) {
        try {
            List<CustomerPropDto> list = customerCenterService.getAllCustomerProp();
            m.addAttribute("list", list);
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "customer/customer_proplist.tiles";
    }

    @GetMapping("/inquiry")
    public String readCustomerInquiry() {
        return "customer/customer_inquirylist.tiles";
    }

    @PostMapping("/inquiry")
    public String writeCustomerInquiry(CustomerInquiryDto customerInquiryDto) {
        System.out.println(customerInquiryDto);
        return "redirect:/customer/inquirylist.tiles";
    }
}

