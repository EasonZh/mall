package com.itxihang.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itxihang.mall.dto.ProductDto;
import com.itxihang.mall.mapper.ProductMapper;
import com.itxihang.mall.pojo.Product;
import com.itxihang.mall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
//    @Override
//    public List<Product> page() {
//        List<Product> product = productMapper.page();
//        return product;
//    }
    //分页查询产品信息
    @Override
    public PageInfo<Product> pageInfo(Integer pageNum) {
        PageHelper.startPage(pageNum,4);
        List<Product>products = productMapper.page();
        PageInfo<Product> pageInfo = new PageInfo<>(products, 5);
        return pageInfo;
    }

    @Override
    public PageInfo<ProductDto> productPage(Integer pageNum, int id) {
        PageHelper.startPage(pageNum,5);
        List<ProductDto>productList =  productMapper.productPage(pageNum,id);
        PageInfo<ProductDto> productPageInfo = new PageInfo<>(productList, 5);
        return productPageInfo;
    }

    @Override
    public void addProduct(Product product) {

        productMapper.addProduct(product);
    }

    @Override
    public Product selectProductById(Integer id) {
        Product product = productMapper.selectProductById(id);
        return product;
    }

    @Override
    public void updateProduct(Product product) {
        productMapper.updateProductById(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productMapper.deleteProductById(id);
    }

    @Override
    public PageInfo<Product> selectByName(Integer pageNum,String mohu) {
        PageHelper.startPage(pageNum,20);
        List<Product>productList= productMapper.selectByName(mohu);
        PageInfo<Product>pageInfo1 = new PageInfo<>(productList,4);
        return pageInfo1;
    }

    @Override
    public PageInfo<ProductDto> selectByCategoryId(Integer pageNum, Integer categoryId) {
        PageHelper.startPage(pageNum,20);
        List<ProductDto>productList = productMapper.selectByCategoryId(categoryId);
        PageInfo<ProductDto> pageInfo = new PageInfo<>(productList,4);
        return pageInfo;
    }
}
