package com.itxihang.mall.dto;

import com.itxihang.mall.pojo.Cart;
import com.itxihang.mall.pojo.Product;
import com.itxihang.mall.pojo.User;

import java.util.Map;

public class CartDto extends Cart {
    private Product product;
    private User user;
    private Integer totalCount;
    private Integer totalProductCount;
    private Double totalMoney;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalProductCount() {
        return totalProductCount;
    }

    public void setTotalProductCount(Integer totalProductCount) {
        this.totalProductCount = totalProductCount;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
