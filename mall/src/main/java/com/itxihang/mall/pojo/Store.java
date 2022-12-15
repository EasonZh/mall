package com.itxihang.mall.pojo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Store {
    private int id;
    private int sellerId;
    private int categoryId;
    private String storeName;
    private int storeStatus;
    private String detail;
    private LocalDateTime  createTime;
    private LocalDateTime  updateTime;


}
