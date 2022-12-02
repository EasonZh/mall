package com.itxihang.mall.mapper;

import com.itxihang.mall.dto.ProductDto;
import com.itxihang.mall.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    List<Product> page();

    //查询对应商家对应的产品
    List<ProductDto> productPage(@Param("pageNum") Integer pageNum,@Param("id") int id);
    //商家添加商品
    void addProduct(Product product);
    //商家通过id查询商品
    Product selectProductById(Integer id);
    //商家修改商品
    void updateProductById( Product product);
    //商家删除商品
    void deleteProductById(Integer id);
    //模糊查询
    List<Product> selectByName(String mohu);
    //根据分类查询商品
    List<ProductDto> selectByCategoryId(Integer categoryId);
    //商品数量减一
    void updateStock(@Param("productId") Integer productId,@Param("amount") Integer amount);
}
