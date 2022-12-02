package com.itxihang.mall.mapper;

import com.itxihang.mall.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User getUser(@Param("uName") String uname,@Param("uPassWord") String pwd);

    void saveUser(@Param("uName") String uname,@Param("uPassWord") String pwd,@Param("email") String email, @Param("phone") String phone);
    //查询所有用户
    List<User> getAllUser();
    //更改用户状态
    void updateUserStatus(@Param("id") Integer id,@Param("status")Integer status);
}
