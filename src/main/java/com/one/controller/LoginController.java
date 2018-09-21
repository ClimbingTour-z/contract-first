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
public class LoginController {
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
    public String checkLogin(User user,String username,String password, Model model){
        //调用service方法
        user = userServivce.checkLogin(username, password);
        //若有user则添加到model里并且跳转到成功页面
        if(user != null){
            model.addAttribute("user",user);
            return "/excel/imExcel";
        }
        return "/sys/login";
    }


    //注销方法
    @RequestMapping("/outLogin")
    public String outLogin(HttpSession session){
        //通过session.invalidata()方法来注销当前的session
        session.invalidate();
        return "/sys/login";
    }
}
