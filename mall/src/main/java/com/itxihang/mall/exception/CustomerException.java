package com.itxihang.mall.exception;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CustomerException extends RuntimeException{
    public CustomerException (String message){
        super(message);


    }
}
