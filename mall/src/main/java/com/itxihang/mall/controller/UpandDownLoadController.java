package com.itxihang.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class UpandDownLoadController {
    private String path = "D:\\mall_images\\picture";
    @RequestMapping("/upload/up")
    public void upload(MultipartFile productImg){
        String originalFilename = productImg.getOriginalFilename();
        System.out.println(originalFilename);
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            productImg.transferTo(new File(path+originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
