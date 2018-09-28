package com.one.controller;

import com.one.model.User;
import com.one.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userServivce;

    /**
     * 登陆页面
     * @return
     */
    @RequestMapping(value = "/login")
    public String login() {
        return "/sys/login";
    }

    //表单提交过来的路径
    @RequestMapping("/checkLogin")
    public String checkLogin(User user,String username,String password, Model model,HttpSession session){
        //调用service方法
        user =(User) userServivce.checkLogin(username, password);
        //若有user则添加到model里并且跳转到成功页面
        if(user != null){
            session.setAttribute("USER_SESSION", user);

            return "/excel/imExcel";
        }
        model.addAttribute("msg","账号或者密码错误！");
        return "/sys/login";
    }


    //注销方法
    @RequestMapping("/logout")
    public String outLogin(HttpSession session){
        //通过session.invalidata()方法来注销当前的session
        session.invalidate();
        return "/sys/login";
    }
}
