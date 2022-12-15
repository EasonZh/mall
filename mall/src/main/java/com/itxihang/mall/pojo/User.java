package com.itxihang.mall.pojo;

import lombok.Data;

@Data
public class User {
    int id;
    String uName;
    String uPassWord;
    String phone;
    String email;
    int role;
    int status;

}