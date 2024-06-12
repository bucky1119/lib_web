package com.cau.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cau.web.entity.User;
import com.cau.web.mapper.UserMapper;
import com.cau.web.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean register(User user) {
        // 检查用户名是否已存在
        if (userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername())) != null) {
            return false; // 用户名已存在
        }
        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 插入用户数据
        return userMapper.insert(user) == 1;
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user; // 登录成功
        }
        return null; // 登录失败
    }
}