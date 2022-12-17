package com.itxihang.mall.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
public class UpandDownLoadController {
//    private String path = "D:\\mall_images\\picture";
    private String path = "/static/uploads";
    @RequestMapping("/upload/up")
    public void upload(MultipartFile productImg, HttpSession session){
        String originalFilename = productImg.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String name = UUID.randomUUID().toString()+suffix;
        String realPath = session.getServletContext().getRealPath(path);
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdir();
        }
        String filePath = file+File.separator+name;
        try {
            productImg.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/test/downLoad")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws
            IOException {
//获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
//获取服务器中文件的真实路径
        String realPath = servletContext.getRealPath("img");
        realPath = realPath + File.separator + "body.jpg";
//创建输入流
        InputStream is = new FileInputStream(realPath);
//创建字节数组
        byte[] bytes = new byte[is.available()];
//将流读到字节数组中
        is.read(bytes);
//创建HttpHeaders对象设置响应头信息
        HttpHeaders headers;
        headers = new HttpHeaders();
//设置要下载方式以及下载文件的名字
        headers.add("Content-Disposition", "attachment;filename=1.jpg");
//设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
//创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers,
                statusCode);
//关闭输入流
        is.close();
        return responseEntity;
    }
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        //通过输入流读取文件内容
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(path+name));
            //输出流，通过输出流将文件写回浏览器，在浏览器展示图片
            ServletOutputStream outputStream = response.getOutputStream();
            //响应回去的格式
            response.setContentType("image/jpeg");
            int len = 0;
            byte[] bytes = new byte[1024];
            while((len = fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            //关闭
            fileInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
