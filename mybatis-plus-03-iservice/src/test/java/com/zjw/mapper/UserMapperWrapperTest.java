package com.zjw.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zjw.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * 简单使用
 * UpdateWrapper
 * QueryWrapper
 * LambdaQueryWrapper
 * LambdaUpdateWrapper
 * @author 朱俊伟
 * @date 2023/11/15 16:34
 */
@SpringBootTest
class UserMapperWrapperTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询出名字中带o的，存款大于等于1000的人的id、username、info、balance
     * SELECT id,username,info,balance
     * FROM user
     * WHERE username LIKE ? AND balance > ?
     */
    @Test
    public void testQueryWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .select("id", "username", "info", "balance")
                .like("username", "o")
                .ge("balance", 1000);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void testLambdaQueryWrapper() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
                .select(User::getId, User::getUsername, User::getInfo, User::getBalance)
                .like(User::getUsername, "o")
                .ge(User::getBalance, 1000);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 更新用户名为jack的用户的余额为2000
     * UPDATE user
     * SET balance = 2000
     * WHERE (username = 'jack')
     */
    @Test
    public void testUpdate() {
        User user = User.builder()
//                .username("jack")
                .balance("2002")
                .build();
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>()
                .eq("username", "jack");
        int i = userMapper.update(user, updateWrapper);
        System.out.println("i = " + i);
    }

    /**
     * 更新id为1，2，4的用户的余额，扣200
     * UPDATE user
     *  SET balance = balance - 200
     *  WHERE id IN (1,2,4)
     */
    @Test
    public void testUpdate2(){
        List<Long> idList = List.of(1L,2L,4L);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>()
                .setSql("balance = balance - 200")
                .in("id",idList);
        int i = userMapper.update(null, updateWrapper);
        System.out.println("i = " + i);
    }

    @Test
    public void testUpdate3(){
        List<Long> idList = List.of(1L,2L,4L);
        int amount = 200;
        LambdaQueryWrapper<User> updateWrapper = new LambdaQueryWrapper<User>()
                .in(User::getId,idList);
        int i = userMapper.updateBalanceByIds(updateWrapper, amount);
        System.out.println("i = " + i);
    }
}