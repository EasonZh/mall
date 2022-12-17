package com.itxihang.mall.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws IOException {
         if(request.getSession().getAttribute("user")==null){
             response.sendRedirect("/page?page=user/login");
             return false;
         }
        return true;
    }
}
