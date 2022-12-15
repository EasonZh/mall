package com.itxihang.mall.mapper;

import com.itxihang.mall.pojo.Address;
import com.itxihang.mall.pojo.Cart;
import com.itxihang.mall.pojo.Order;
import com.itxihang.mall.pojo.OrderItem;

import java.util.List;

public interface OrderMapper {
    //通过userId查购物车的信息
    List<Cart> selectCartById(Integer userId);
    //插入数据到订单
    void addOrder(Order order);
    //删除购物车信息
    void deleteCartById(Integer userId);
    //增加订单详情
    void addOrderItem(OrderItem orderItem);
    //查询所有订单
    List<Order> selectOrderById(Integer userId);
    //查询订单详情
    List<OrderItem> selectOrderItemById(String userId);
    //查询地址
    Address selectAddressById(Integer userId);
}
