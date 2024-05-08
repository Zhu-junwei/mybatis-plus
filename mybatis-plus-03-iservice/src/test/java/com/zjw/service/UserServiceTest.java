package com.zjw.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zjw.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * IService接口测试
 * 保存：save*
 * 删除：remove*
 * 修改：update*
 * 查询：get*、list*
 * lambdaQuery、lambdaUpdate
 * @author 朱俊伟
 * @since 2023/11/15 22:32
 */
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testSave(){
        User user = User.builder()
                .username("zjw")
                .password("123456")
                .info("{\"info\":"+"\"我是INFO\"}")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        boolean saved = userService.save(user);
        System.out.println("saved = " + saved);
    }

    /**
     * 测试更新或插入
     */
    @Test
    public void testSaveOrUpdate(){
        User user = User.builder()
                .id(6L)
                .username("zjw")
                .password("123456")
                .info("{\"info\":"+"\"我是INFO\"}")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        //根据id进行更新
        boolean saved = userService.saveOrUpdate(user);
        System.out.println("saved = " + saved);
    }

    /**
     * 测试更新或插入
     */
    @Test
    public void testSaveOrUpdate2(){
        User user = User.builder()
                .id(6L)
                .username("zjw")
                .password("123456")
                .info("{\"info\":"+"\"我是INFO\"}")
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<User>()
                .eq(User::getUsername,"zjw")
                .eq(User::getId,6L);
        //先根据name和id更新
        //如果更新数量为0 再更具id更新，更新结果再为0再插入
        boolean saved = userService.saveOrUpdate(user,updateWrapper);
        System.out.println("saved = " + saved);
    }

    /**
     * 通过自定义的方法查询
     */
    @Test
    public void testGetUserById(){
        User user = userService.getUserById(1L);
        System.out.println("user = " + user);
    }

    /**
     * 通过IService的方法查询
     */
    @Test
    public void testSelectOne(){
        LambdaUpdateWrapper<User> queryWrapper = new LambdaUpdateWrapper<User>()
                .eq(User::getId,1L);
        User user = userService.getOne(queryWrapper);
        User user1 = userService.getById(1L);
        System.out.println("user = " + user);
        System.out.println("user1 = " + user1);
    }

    @Test
    public void testList(){
        List<User> userList = userService.list();
        userList.forEach(System.out::println);
    }

    /**
     * 计数
     */
    @Test
    public void testCount(){
        long count = userService.count();
        System.out.println("count = " + count);
    }

    /**
     * 根据id删除
     */
    @Test
    public void testRemoveById(){
        boolean removed = userService.removeById(6L);
        System.out.println("removed = " + removed);
    }
}