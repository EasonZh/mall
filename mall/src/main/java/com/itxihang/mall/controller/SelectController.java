package com.itxihang.mall.controller;

import com.github.pagehelper.PageInfo;
import com.itxihang.mall.dto.ProductDto;
import com.itxihang.mall.pojo.Product;
import com.itxihang.mall.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/select")
public class SelectController {
    @Autowired
    private ProductService productService;
    @RequestMapping("/product/{pageNum}")
    public String selectFind(@PathVariable("pageNum")Integer pageNum, @Param("mohu")String mohu, Model model){
        PageInfo<Product>productPageInfo = productService.selectByName(pageNum,mohu);
        model.addAttribute("productPageInfo",productPageInfo);
        return "manager/select_productbymohu";
    }
    @RequestMapping("/category/{pageNum}/{categoryId}")
    public String selectByCategoryId(@PathVariable("pageNum")Integer pageNum,@PathVariable("categoryId")Integer categoryId,Model model){
        PageInfo<ProductDto>productPageInfo = productService.selectByCategoryId(pageNum,categoryId);
        model.addAttribute("productPageInfo",productPageInfo);
        return "manager/select_product";
    }
}
