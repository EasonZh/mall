package com.itxihang.mall.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
@Data
public class OrderItem {
    private Integer id;

    private String orderNo;

    private Integer buyId;

    private Integer sellerId;

    private Integer storeId;

    private Integer productId;

    private String productName;

    private String productImg;

    private BigDecimal price;

    private Integer amount;

    private Double totalMoney;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


}
