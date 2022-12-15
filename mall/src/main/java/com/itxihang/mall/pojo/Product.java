package com.itxihang.mall.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class Product {
    private int id;
    private int categoryId;
    private int storeId;
    private String productName;
    private String productImg;
    private String detail;
    private int saleStatus;
    private BigDecimal price;
    private int stock;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
