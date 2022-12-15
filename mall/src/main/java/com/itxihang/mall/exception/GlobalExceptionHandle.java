package com.itxihang.mall.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice(annotations = {Controller.class})
@Slf4j
public class GlobalExceptionHandle {
    @ExceptionHandler(CustomerException.class)
    public void exception(CustomerException e, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print("<script language='javascript'>alert('"+e.getMessage()+"')</script>");

    }
}
