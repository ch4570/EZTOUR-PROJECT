package com.devcamp.eztour.controller;

import com.devcamp.eztour.domain.faq.FaqDto;
import com.devcamp.eztour.domain.faq.FaqPageHandler;
import com.devcamp.eztour.service.faq.FaqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final FaqService faqService;

    @GetMapping("/main")
    public String customer(Model m) {
        try {
            List<FaqDto> list = faqService.getAllFaq();
            m.addAttribute("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "customer/customer_main.tiles";
    }

    @GetMapping("/faqList")
    public String getAllFaq(Integer page, Integer pageSize, Model m) {
        if(page==null) page = 1;
        if(pageSize==null) pageSize = 10;

        try {
            int totalCnt = faqService.getCountFaq();
            FaqPageHandler eventPageHandler = new FaqPageHandler(totalCnt, page, pageSize);

            Map map = new HashMap();
            map.put("offset", (page - 1) * pageSize);
            map.put("pageSize", pageSize);

            List<FaqDto> list = faqService.getFaqPage(map);
            m.addAttribute("list", list);
            m.addAttribute("ph", eventPageHandler);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "faq/faq_list.tiles";
    }

    @GetMapping("/faqWrite")
    public String writeFaq(Integer page, Integer pageSize, Integer faq_no, Model m, RedirectAttributes rattr) {

        if (faq_no == null) {
            return "faq/faq_write.tiles";
        }

        try {
            FaqDto faqDto = faqService.getFaq(faq_no);
            m.addAttribute("faqDto", faqDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
            return "faq/faq_write.tiles";
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "Load Error");
            return "redirect:/faq/faq_list";
        }
    }

    @PostMapping("/faqWrite")
    public String writeFaq(FaqDto faqDto, Model m, RedirectAttributes rattr) {
        try {
            int rowCnt = faqService.writeFaq(faqDto);

            if (rowCnt != 1) {
                throw new Exception("FAQ Write Error");
            }
            rattr.addAttribute("faq_no", faqDto.getFaq_no());
            rattr.addFlashAttribute("msg", "write Success");
            return "redirect:/customer/faqList";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(faqDto);
            m.addAttribute("msg", "Write Error");
            return "faq/faq_write.tiles";
        }
    }

    @GetMapping("/faq")
    public String getFaq(Integer faq_no, Integer page, Integer pageSize, Model m) {
        try {
            FaqDto faqDto = faqService.getFaq(faq_no);
            m.addAttribute("faqDto", faqDto);
            System.out.println("faqDto = " + faqDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "faq/faq_borad.tiles";
    }

    @PostMapping("/removeFaq")
    public String removeFaq(Integer faq_no, Integer page, Integer pageSize, RedirectAttributes rattr) {
        try {
            rattr.addAttribute("page", page);
            rattr.addAttribute("pageSize", pageSize);

            int rowCnt = faqService.removeFaq(faq_no);
            if (rowCnt != 1) {
                throw new Exception("FAQ Remove Error");
            }
            rattr.addFlashAttribute("msg", "Delete Success");
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "Delete Error");
        }
        return "redirect:/customer/faqList";
    }

    @PostMapping("/modify")
    public String modifyEvent(Integer page, Integer pageSize, FaqDto faqDto, Model m, RedirectAttributes rattr) {
        try {
            int rowCnt = faqService.modifyFaq(faqDto);

            if (rowCnt != 1) {
                throw new Exception("FAQ Modify Error");
            }
            rattr.addAttribute("faq_no", faqDto.getFaq_no());
            rattr.addAttribute("page", page);
            rattr.addAttribute("pageSize", pageSize);
            rattr.addFlashAttribute("msg", "Modify Success");
            return "redirect:/customer/faq";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("event_id", faqDto.getFaq_no());
            m.addAttribute("msg", "Modify Error");
            return "customer/faq_write.tiles";
        }
    }
}
