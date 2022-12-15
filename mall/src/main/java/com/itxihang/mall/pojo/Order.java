package com.itxihang.mall.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
@Data
public class Order {
    private Integer id;

    private String orderNo;

    private Integer buyId;

    private Integer addressId;

    private Integer orderStatus;

    private BigDecimal payment;

    private Integer payType;

    private Integer postage;

    private Integer status;

    private Double totalMoney;

    private Integer count;

    private LocalDateTime payTime;

    private LocalDateTime sendTime;


}
