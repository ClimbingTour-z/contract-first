package com.one.dao;

import com.one.model.User;

public interface UserMapper {

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByUsername(String username);
}
