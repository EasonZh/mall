package com.itxihang.mall.service;

import com.itxihang.mall.pojo.Order;
import com.itxihang.mall.pojo.OrderItem;

import java.io.IOException;
import java.util.List;

public interface OrderService {
    List<Order> addOrder(Integer userId);

    List<OrderItem> selectOrderItem(String orderNo);
}
