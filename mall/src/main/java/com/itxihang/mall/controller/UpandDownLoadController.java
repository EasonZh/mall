package com.itxihang.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UpandDownLoadController {
//    private String path = "D:\\mall_images\\picture";
//    private String path = "./static/uploads";
    @RequestMapping("/upload/up")
    public void upload(MultipartFile productImg, HttpSession session){
        String originalFilename = productImg.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String name = UUID.randomUUID().toString()+suffix;
        String realPath = session.getServletContext().getRealPath("/static/uploads");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            productImg.transferTo(new File(realPath+name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
