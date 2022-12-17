package com.itxihang.mall.service.impl;

import com.itxihang.mall.exception.CustomerException;
import com.itxihang.mall.mapper.OrderMapper;
import com.itxihang.mall.mapper.ProductMapper;
import com.itxihang.mall.pojo.*;
import com.itxihang.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;
//    @Transactional(propagation=Propagation.REQUIRES_NEW)
    @Transactional
    @Override
    public List<Order>addOrder(Integer userId){

        //通过userId查购物车的信息
        List<Cart> cartList = orderMapper.selectCartById(userId);
        if(cartList == null || cartList.size()==0){
              throw new CustomerException("购物车为空，不能下单");
        }
        //将购物车的信息插入到订单表
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        Double tatalMoney = 0.0;
        int count = 0;
        UUID uuid1 = UUID.randomUUID();
        for(Cart cart: cartList){
            //插入订单详情
            order.setOrderNo(uuid1.toString());
            order.setBuyId(userId);
            order.setStatus(1);
            BigDecimal price = cart.getPrice();
            int amount = cart.getAmount();
            tatalMoney += (price.multiply(BigDecimal.valueOf(amount))).doubleValue();
            order.setPayTime(LocalDateTime.now());
            count += 1;
            //插入订单详情
            orderItem.setOrderNo(uuid1.toString());
            orderItem.setBuyId(userId);
            orderItem.setStoreId(cart.getStoreId());
            orderItem.setProductId(cart.getProductId());
            Product product = productMapper.selectProductById(cart.getProductId());
            //将产品库存更新
            productMapper.updateStock(product.getId(),cart.getAmount());

            orderItem.setProductName(product.getProductName());
            BigDecimal price1 = product.getPrice();
            int amount1 = cart.getAmount();
            orderItem.setPrice(price1);
            orderItem.setAmount(amount1);
            orderItem.setProductImg(product.getProductImg());
            Double totalMoney1=(price1.multiply(BigDecimal.valueOf(amount1))).doubleValue();
            orderItem.setTotalMoney(totalMoney1);
            orderItem.setCreateTime(order.getPayTime());
            orderMapper.addOrderItem(orderItem);

        }
        order.setTotalMoney(tatalMoney);
        order.setCount(count);
        orderMapper.addOrder(order);
        //查询所有订单信息
        List<Order>orders = orderMapper.selectOrderById(userId);

        //清空用户购物车的信息
        orderMapper.deleteCartById(userId);
        return orders;
    }

    @Override
    public List<OrderItem> selectOrderItem(String orderNo) {
        List<OrderItem>orderItems = orderMapper.selectOrderItemById(orderNo);

        return orderItems;
    }

    @Override
    public Order buyProduct(int id, int userId) {
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        Product product = productMapper.selectProductById(id);
        UUID uuid = UUID.randomUUID();
        order.setOrderNo(uuid.toString());
        order.setBuyId(userId);
        order.setTotalMoney(product.getPrice().doubleValue());
//        Address address = orderMapper.selectAddressById(userId);
//        order.setAddressId(address.getId());
        order.setCount(1);
        //时间转换
        order.setPayTime(LocalDateTime.now());
        orderMapper.addOrder(order);
        orderItem.setProductId(id);
        orderItem.setBuyId(userId);
        orderItem.setOrderNo(uuid.toString());
        orderItem.setAmount(1);
        orderItem.setPrice(product.getPrice());
        orderItem.setProductName(product.getProductName());
        orderItem.setProductImg(product.getProductImg());
        orderItem.setCreateTime(order.getPayTime());
        orderItem.setStoreId(product.getStoreId());
        orderItem.setTotalMoney(product.getPrice().doubleValue());

        orderMapper.addOrderItem(orderItem);
        return order;

    }

}
