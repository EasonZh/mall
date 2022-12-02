package com.itxihang.mall.service;

import com.github.pagehelper.PageInfo;
import com.itxihang.mall.dto.ProductDto;
import com.itxihang.mall.pojo.Product;

import java.util.List;

public interface ProductService {

//    List<Product> page();
    //分页查询产品信息
    PageInfo<Product> pageInfo(Integer pageNum);

    PageInfo<ProductDto> productPage(Integer pageNum, int id);

    void addProduct(Product product);

    Product selectProductById(Integer id);

    void updateProduct(Product product);

    void deleteProduct(Integer id);

    PageInfo<Product> selectByName(Integer pageNum, String mohu);

    PageInfo<ProductDto> selectByCategoryId(Integer pageNum, Integer categoryId);
}
