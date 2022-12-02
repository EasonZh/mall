package com.itxihang.mall.service.impl;

import com.itxihang.mall.mapper.UserMapper;
import com.itxihang.mall.pojo.User;
import com.itxihang.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUser(String uname, String pwd) {
        User user = userMapper.getUser(uname,pwd);
        return user;
    }

    @Override
    public void saveUser(String uname, String pwd, String email, String phone) {
        userMapper.saveUser(uname,pwd,email,phone);
    }

    @Override
    public List<User> getAllUser() {
        List<User>users = userMapper.getAllUser();
        return users;
    }

    @Override
    public void updateUserStatus(Integer id,Integer status) {
        userMapper.updateUserStatus(id,status);
    }
}
