package com.itxihang.mall.service.impl;

import com.itxihang.mall.dto.CartDto;
import com.itxihang.mall.exception.CustomerException;
import com.itxihang.mall.mapper.CartMapper;
import com.itxihang.mall.mapper.ProductMapper;
import com.itxihang.mall.pojo.Cart;
import com.itxihang.mall.pojo.Product;
import com.itxihang.mall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public void selectProductById(int userId, Integer productId) {
        //通过userId查询购物车
//        List<Integer> productIds = cartMapper.getUserById(userId);
//        if(productIds!=null) {
//            for (Integer id : productIds) {
//                if (productId == id) {
//                    //如果相等则需使数量+1
//
//                }
//
//            }
//        }
//        //将商品的库存减一
//        productMapper.updateStock(productId);

        CartDto cartDto = new CartDto();
        Product product = cartMapper.selectProduct(productId);
        //判断商品的售卖状态和库存
        int saleStatus = product.getSaleStatus();
        int stock = product.getStock();
        if(saleStatus==0){
            throw new CustomerException("已停售");

        }
        if(stock == 0){
            throw new CustomerException("无货，请耐心等待");
        }
//        Map<Integer,Product> products = new HashMap<>();
//        products.put(productId,product);
        cartDto.setProduct(product);
        cartDto.setProductId(productId);
        cartDto.setUserId(userId);
        cartDto.setCreateTime(LocalDateTime.now());
        cartDto.setUpdateTime(LocalDateTime.now());
        cartDto.setPrice(product.getPrice());
        Cart cart =  cartMapper.getUserById(userId,productId);

        if(cart!=null){
            //如果购物车不为空则更新数量
                   cartMapper.updateCart(userId,productId);
                   cartDto.setAmount(cart.getAmount()+1);
                    return;
        }
        //如果购物车为空则直接加入购物车
        cartDto.setAmount(1);
        cartMapper.insertCart(cartDto);
        return;
    }

    @Override
    public List<CartDto> selectCart(Integer userId) {
        List<CartDto>cartDtos = cartMapper.selectCart(userId);
        return cartDtos;
    }

    @Override
    public void editCart(Integer id, Integer amount) {
        cartMapper.editCart(id,amount);
    }

    @Override
    public void deleteCartById(Integer id) {
        cartMapper.deleteCartById(id);
    }

    @Override
    public void deleteAllCart(Integer userId) {
        cartMapper.deleteAllcartByUserId(userId);
    }

}
