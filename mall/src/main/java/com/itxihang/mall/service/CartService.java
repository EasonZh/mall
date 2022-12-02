package com.itxihang.mall.service;

import com.itxihang.mall.dto.CartDto;

import java.util.List;

public interface CartService {
    void selectProductById(int userId, Integer productId);

    List<CartDto> selectCart(Integer userId);

    void editCart(Integer id, Integer amount);

    void deleteCartById(Integer id);

    void deleteAllCart(Integer userId);
}
