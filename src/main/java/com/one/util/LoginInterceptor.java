package com.one.util;

import com.one.model.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    /**
     * Handler执行完成之后调用这个方法
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exc) throws Exception {
    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * Handler执行之前调用这个方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求的URL
        String url = request.getRequestURI();
        //URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制
        if (url.indexOf("/user/checkLogin") >= 0) {
            return true;
        }
        //获取Session
        HttpSession session = request.getSession();
        User user=(User) session.getAttribute("USER_SESSION");

        if (user != null) {
            return true;
        }
        //不符合条件的，跳转到登录界面
        if (url.indexOf("/user/logout") >= 0) {
            request.getRequestDispatcher("/WEB-INF/views/sys/login.jsp").forward(request, response);
            return false;
        }
        request.setAttribute("msg", "你还没有登录，请先登录");
        request.getRequestDispatcher("/WEB-INF/views/sys/login.jsp").forward(request, response);
        return false;
    }
}
