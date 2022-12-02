package com.itxihang.mall.service;

import com.itxihang.mall.pojo.User;

import java.util.List;

public interface UserService {
    User getUser(String uname, String pwd);

    void saveUser(String uname, String pwd, String email, String phone);

    List<User> getAllUser();

    void updateUserStatus(Integer id,Integer status);
}
