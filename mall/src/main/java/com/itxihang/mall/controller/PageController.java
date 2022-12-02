package com.itxihang.mall.controller;

import com.itxihang.mall.pojo.Product;
import com.itxihang.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PageController {

    @RequestMapping("/page")
    public String page(String page){
//          if(page.equals("index")){
//           List<Product> products = productService.page();
//           model.addAttribute("products",products);
//          }
          return page;
    }

}
