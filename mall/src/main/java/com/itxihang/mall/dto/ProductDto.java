package com.itxihang.mall.dto;

import com.itxihang.mall.pojo.Product;
import com.itxihang.mall.pojo.Store;

public class ProductDto extends Product {
       private String storeName;

       public String getStoreName() {
              return storeName;
       }

       public void setStoreName(String storeName) {
              this.storeName = storeName;
       }
}
