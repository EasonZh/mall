package com.itxihang.mall.mapper;

import com.itxihang.mall.dto.CartDto;
import com.itxihang.mall.pojo.Cart;
import com.itxihang.mall.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    //通过userId查询购物车
    Cart getUserById(@Param("userId") Integer userId, @Param("productId") Integer productId);
    //如果不相等则直接加入购物车
    Product selectProduct(Integer productId);

    void insertCart(CartDto cartDto);
    //如果相等则数量加1
    void updateCart(@Param("userId") Integer userId,@Param("productId")Integer productId);
    //查询购物车所有信息
    List<CartDto> selectCart(Integer userId);
    //更改购物车数量
    void editCart(@Param("id") Integer id,@Param("amount") Integer amount);
    //删除购物车的一条信息
    void deleteCartById(Integer id);
    //删除购物车所有信息
    void deleteAllcartByUserId(Integer userId);
}
