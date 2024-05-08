package com.zjw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjw.domain.User;

/**
 * @author 朱俊伟
 * @since 2023/11/15 22:30
 */
public interface UserService extends IService<User> {

    public User getUserById(Long id);
}
