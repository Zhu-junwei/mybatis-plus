package com.zjw.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjw.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * IService接口测试
 * 保存：save*
 * 删除：remove*
 * 修改：update*
 * 查询：get*、list*
 * lambdaQuery、lambdaUpdate
 * @author 朱俊伟
 * @date 2023/11/15 22:32
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
                .info(User.UserInfo.builder()
                        .age(18)
                        .gender("male")
                        .intro("青年")
                        .build())
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
                .info(User.UserInfo.builder()
                        .age(18)
                        .gender("male")
                        .intro("青年")
                        .build())
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
                .info(User.UserInfo.builder()
                        .age(18)
                        .gender("male")
                        .intro("青年")
                        .build())
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
     * 分页查询
     */
    @Test
    void testPage() {
        int pageNo = 1, pageSize = 2;
        Page<User> page = Page.of(pageNo, pageSize);
        page.addOrder(new OrderItem("id",true));
        Page<User> userPage = userService.page(page);
        //当前页
        System.out.println("page.getCurrent() = " + userPage.getCurrent());
        //总条数
        System.out.println("page.getTotal() = " + userPage.getTotal());
        //当前页条数
        System.out.println("userPage.getSize() = " + userPage.getSize());
        //数据
        List<User> userList = userPage.getRecords();
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


    private User buildUser(int i){
        return User.builder()
                .username("user_"+i)
                .password("1234")
                .info(User.UserInfo.builder()
                        .age(18)
                        .gender("male")
                        .intro("青年")
                        .build())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
    }

    /**
     * 测试批量插入
     */
    @Test
    public void testSaveOneByOne(){
        StopWatch stopWatch = new StopWatch("批量插入");
        stopWatch.start();
        for (int i = 0; i < 100000; i++) {
            userService.save(buildUser(i));
        }
        stopWatch.stop();
        //十万条 255s
        System.out.println("批量插入完成:" + stopWatch.getTotalTimeSeconds() +"s");
    }

    /**
     * 测试批量插入
     */
    @Test
    public void testSaveBatch(){
        StopWatch stopWatch = new StopWatch("批量插入");
        stopWatch.start();
        List<User> userList = new ArrayList<>(1000);
        for (int i = 0; i < 100000; i++) {
            userList.add(buildUser(i));
            if ((i+1)%1000==0){
                userService.saveBatch(userList);
                userList.clear();
            }
        }
        stopWatch.stop();
        //十万条 33s 未重写mysql批处理语句
        //十万条 6s 重写mysql批处理语句url后面加上 rewriteBatchedStatements=true
        System.out.println("批量插入完成:" + stopWatch.getTotalTimeSeconds() +"s");
    }

}