package com.one.serivce;

import com.one.dao.UserMapper;
import com.one.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 检验用户登录
     * @param username
     * @param password
     * @return
     */
    public User checkLogin(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
