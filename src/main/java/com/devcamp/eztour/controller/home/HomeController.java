package com.devcamp.eztour.controller.home;

import com.devcamp.eztour.domain.category.DestinationCategoryDto;
import com.devcamp.eztour.domain.home.TrvPrdDto;
import com.devcamp.eztour.service.category.DestinationCategoryService;
import com.devcamp.eztour.service.home.HomeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;
    private  final DestinationCategoryService destinationCategoryService;


    @GetMapping("/")
    public String home(Model m, HttpServletRequest request) {
        try {
            List<DestinationCategoryDto> list = destinationCategoryService.getCategory();
            m.addAttribute("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "home/home.tiles";
    }

    @GetMapping("/search")
    public String getSearch(String keyword, Model m) {

        try {
            System.out.println(keyword);
            List<TrvPrdDto> list = homeService.getSearch(keyword);

            System.out.println(list);

            int resultCnt = homeService.getSearchResultCnt(keyword);

            System.out.println(resultCnt);

            m.addAttribute("list", list);
            m.addAttribute("resultCnt", resultCnt);
            m.addAttribute("keyword", keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "home/search.tiles";
    }

}

