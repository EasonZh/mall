package com.itxihang.mall.controller;

import com.itxihang.mall.dto.CartDto;
import com.itxihang.mall.pojo.Product;
import com.itxihang.mall.pojo.User;
import com.itxihang.mall.service.CartService;
import com.itxihang.mall.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @RequestMapping("/product/addCart/{id}")
    public String addCart(@PathVariable("id")Integer id, HttpSession session, Model model){
        //首先根据当前用户去查购物车中是否有这个商品
        User user = (User) session.getAttribute("user");
        System.out.println(user);
//        CartDto cartDto = cartService.selectProductById(user.getId(),id);
        cartService.selectProductById(user.getId(),id);
        return "redirect:/cart/index";
    }
    @RequestMapping("/cart/index")
    public String index(HttpSession session,Model model){
         User user = (User) session.getAttribute("user");
         List<CartDto> cartDtos = cartService.selectCart(user.getId());
         Integer totalAmount = 0;
         Double totalMoney1 = 0.0;
//        Iterator<CartDto> it = cartDtos.iterator();
//        while(it.hasNext()){
//            CartDto cartDto = it.next();
//            BigDecimal price = cartDto.getPrice();
//            BigDecimal amount = BigDecimal.valueOf(cartDto.getAmount());
//            BigDecimal totalMoney = price.multiply(amount);
//            cartDto.setTotalMoney(totalMoney.doubleValue());
//            totalMoney1+=totalMoney.doubleValue();
//            int amount1 = cartDto.getAmount();
//            totalAmount+=amount1;
//        }
        for(CartDto cartDto:cartDtos){
            BigDecimal price = cartDto.getProduct().getPrice();
             BigDecimal amount = BigDecimal.valueOf(cartDto.getAmount());
             BigDecimal totalMoney = price.multiply(amount);
             cartDto.setTotalMoney(totalMoney.doubleValue());
             totalMoney1+=totalMoney.doubleValue();
             int amount1 = cartDto.getAmount();
             totalAmount+=amount1;
         }
         model.addAttribute("totalAmount",totalAmount);
         model.addAttribute("totalMoney1",totalMoney1);
         model.addAttribute("cartDtos",cartDtos);
         return "cart/cart";
    }
    @RequestMapping("/cart/editCart")
     public String editCart(Integer id,Integer amount){
        cartService.editCart(id,amount);
        return "redirect:/cart/index";
    }
    //删除购物车的信息
    @RequestMapping("/cart/deleteCart/{id}")
      public String deleteCart(@PathVariable("id")Integer id){
        cartService.deleteCartById(id);
        return "redirect:/cart/index";
    }
    //删除对应用户购物车中所有信息
    @RequestMapping("/cart/deleteAllCart/{userId}")
    public String deleteAllCart(@PathVariable("userId")Integer userId){
        cartService.deleteAllCart(userId);
        return "redirect:/cart/index";
    }
}
