//package com.itxihang.mall.interceptor;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//import java.util.ArrayList;
////注册处理器拦截器
//@Configuration
//public class LoginInterceptorConfigurer implements WebMvcConfigurer {
//    //        创建拦截器对象
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        HandlerInterceptor loginInterceptor = new LoginInterceptor();
//        ArrayList<String> list = new ArrayList<>();
//        list.add("/product/**");
//        list.add("/page");
//        list.add("user/login");
//        //        通过注册工具添加拦截器
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(list);
//    }
//}
