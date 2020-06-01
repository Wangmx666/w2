package com.wmx.service.user;

import com.wmx.model.user.User;

import java.util.Map;

public interface UserService {

    //查询账号密码（登录）
    Map<String,Object> login(User user);

    //查询所有用户
    Map<String,Object> selectUserList(User user);
}
