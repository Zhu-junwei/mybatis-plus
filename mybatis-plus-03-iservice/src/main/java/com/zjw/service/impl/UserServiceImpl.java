package com.zjw.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjw.domain.User;
import com.zjw.mapper.UserMapper;
import com.zjw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 朱俊伟
 * @date 2023/11/15 22:30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

}
