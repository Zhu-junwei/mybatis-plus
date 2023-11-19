package com.zjw.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zjw.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author 朱俊伟
 * @date 2023/11/15 16:33
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 自定义接口
     * Constants.WRAPPER = "ew"
     * @param updateWrapper 封装的where条件
     * @param amount 参数
     * @return 更新数量
     */
//    int updateBalanceByIds(@Param("ew") LambdaQueryWrapper<User> updateWrapper, @Param("amount") int amount);
    int updateBalanceByIds(@Param(Constants.WRAPPER) LambdaQueryWrapper<User> updateWrapper, @Param("amount") int amount);
}
