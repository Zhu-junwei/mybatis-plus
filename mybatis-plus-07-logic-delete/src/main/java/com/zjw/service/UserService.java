package com.zjw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjw.domain.User;
import com.zjw.domain.vo.UserVO;

import java.util.List;

/**
 * @author 朱俊伟
 * @since 2023/11/15 22:30
 */
public interface UserService extends IService<User> {

    public User getUserById(Long id);

    void decuctBalance(Long id, Integer money);

    List<User> queryUsers(String name, Integer status, Integer minBalance, Integer maxBalance);

    UserVO queryUserAndAddressById(Long id);
}
