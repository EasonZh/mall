package com.itxihang.mall.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class Cart {
    private int id;
    private int productId;
    private int userId;
    private int storeId;
    private BigDecimal price;
    private int amount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
