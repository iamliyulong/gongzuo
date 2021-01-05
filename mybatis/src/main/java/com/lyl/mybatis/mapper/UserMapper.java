package com.lyl.mybatis.mapper;

import com.lyl.mybatis.model.User;

public interface UserMapper {

    public void addUser(User user);

    public User queryUserById(Integer id);

}
