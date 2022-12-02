package com.itxihang.mall.controller;

import com.github.pagehelper.PageInfo;
import com.itxihang.mall.dto.ProductDto;
import com.itxihang.mall.pojo.Product;
import com.itxihang.mall.pojo.User;
import com.itxihang.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;


@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    //分页查询产品信息
    @RequestMapping("/product/{pageNum}")
    public String pageInfo(@PathVariable("pageNum") Integer pageNum, String page, Model model){
        PageInfo<Product> pageInfo = productService.pageInfo(pageNum);
        model.addAttribute("pageInfo",pageInfo);
        return "index";
    }
    //商家管理商品
    @RequestMapping("/product/seller/{pageNum}")
    public String productPage(@PathVariable("pageNum") Integer pageNum, HttpSession session,Model model){
       User user = (User) session.getAttribute("user");
       int id = user.getId();
       PageInfo<ProductDto> productPageInfo =  productService.productPage(pageNum,id);
       model.addAttribute("productPageInfo",productPageInfo);
        return "manager/product_manager";
    }
    //商家添加商品
    @RequestMapping("/product/addproduct")
    public String addProduct(Product product){
        BigDecimal price = product.getPrice();
        productService.addProduct(product);
        return "redirect:/product/seller/1";
    }
    //商家修改商品第一步通过id查询
    @RequestMapping("/product/selectProduct/{id}")
    public String selectProduct(@PathVariable("id")Integer id,Model model){
        Product product = productService.selectProductById(id);
        model.addAttribute("product",product);
        return "manager/product_update";
    }
    //商家修改商品
    @RequestMapping("/product/updateProduct")
    public String updateProdcut(Product product, HttpServletResponse response) throws IOException {
        productService.updateProduct(product);
        return "redirect:/product/seller/1";
    }
    //商家删除商品
    @RequestMapping("/product/deleteProduct/{id}")
    public String updateProduct(@PathVariable("id")Integer id){
        productService.deleteProduct(id);
        return "redirect:/product/seller/1";
    }
}
