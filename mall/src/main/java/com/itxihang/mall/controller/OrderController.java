package com.itxihang.mall.controller;

import com.itxihang.mall.pojo.Order;
import com.itxihang.mall.pojo.OrderItem;
import com.itxihang.mall.pojo.User;
import com.itxihang.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/addOrder/{userId}")
    public String addOrder(@PathVariable("userId")Integer userId, Model model){
        List<Order > order = orderService.addOrder(userId);
        model.addAttribute("order",order);
        return "order/order_display";
    }
    @RequestMapping("/selectOrderItem/{orderNo}")
    public String selectOrderItem(@PathVariable("orderNo")String orderNo,Model model){
        List<OrderItem>orderItems = orderService.selectOrderItem(orderNo);
        model.addAttribute("orderItems",orderItems);
        return "order/order_list";
    }
    @RequestMapping("/buyProduct/{id}")
    public String buyProduct(@PathVariable("id")int id, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        Order order = new Order();
        order = orderService.buyProduct(id,user.getId());
        model.addAttribute("order",order);
        return "order/order_display";
    }
}
