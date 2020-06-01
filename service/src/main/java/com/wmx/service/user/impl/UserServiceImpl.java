package com.wmx.service.user.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wmx.mapper.user.UserMapper;
import com.wmx.model.user.User;
import com.wmx.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //查询账号密码（登录）
    @Override
    public Map<String, Object> login(User user) {
        Map<String, Object> map = new HashMap<>();
        //默认0（为假，代表用户名或密码错误）
        int code = 0;
        //查询账号密码
        User u =  userMapper.login(user);
        //判断账号密码是否存在
        if (null != u) {
            //登录成功
            code = 1;
            map.put("user", u);
        }
        map.put("code", code);
        return map;
    }

    //查询所有用户
    @Override
    public Map<String, Object> selectUserList(User user) {
        //声明map
        Map<String, Object> map = new HashMap<>();
        //开启分页
        PageHelper.startPage(user.getPage(), user.getLimit());
        //查询用户
        List<User> list = userMapper.selectUserList(user);
        //转换page类
        Page page = (Page) list;
        //获取总条数
        long total = page.getTotal();
        //封装数据到map中
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", list);
        map.put("count", total);
        return map;
    }
}
