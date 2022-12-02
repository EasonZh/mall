package com.itxihang.mall.service.impl;

import com.itxihang.mall.exception.CustomerException;
import com.itxihang.mall.mapper.OrderMapper;
import com.itxihang.mall.mapper.ProductMapper;
import com.itxihang.mall.pojo.Cart;
import com.itxihang.mall.pojo.Order;
import com.itxihang.mall.pojo.OrderItem;
import com.itxihang.mall.pojo.Product;
import com.itxihang.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;
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
}
