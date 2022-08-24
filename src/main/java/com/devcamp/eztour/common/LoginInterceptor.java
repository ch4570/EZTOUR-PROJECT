package com.devcamp.eztour.common;

import com.devcamp.eztour.domain.user.UserDto;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");

        // 로그인하지 않았다면 toURL을 세션에 담아 로그인 페이지로 리다이렉트
        if (userDto == null) {
            String toURL = request.getRequestURL().toString() + "?" + request.getQueryString();
            request.getSession().setAttribute("toURL", toURL);

            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion (HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        HandlerInterceptor.super.afterCompletion(request,response,handler,ex);
    }
}
