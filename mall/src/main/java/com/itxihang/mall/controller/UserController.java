package com.itxihang.mall.controller;

import com.itxihang.mall.pojo.User;
import com.itxihang.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
   private UserService userService;
    @RequestMapping("/login")
    public String login(String uname, String pwd, HttpSession session, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        User user = userService.getUser(uname,pwd);
         if(user!=null){
             if(user.getStatus()==1) {
                 session.setAttribute("user", user);
                 return "redirect:/productPage/1";
             }else{
                 out.print("<script language='javascript'>alert('您的账户已被禁用')</script>");
                 return "user/login";
             }
         }
//         else{
//             out.print("<script language='javascript'>alert('用户名或密码错误')</script>");
//         }
        out.print("<script language='javascript'>alert('请登录您的正确的账号')</script>");
             return "user/login";

    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "user/login";
    }
    @PostMapping("/register")
    public String register(String uname,String pwd,String email,String phone){
         userService.saveUser(uname,pwd,email,phone);
        return "user/regist_success";
    }
    //查询所有用户
    @RequestMapping("/userManage")
     public String userManage(Model model){
          List<User>users = userService.getAllUser();
          model.addAttribute("users",users);
        return "user/user_manager";
    }
    //更改用户权限
    @RequestMapping("/updateUserStatus")
    public String updateUserStatus(Integer id,Integer status){
        userService.updateUserStatus(id,status);
        return "redirect:/user/userManage";
    }
}
