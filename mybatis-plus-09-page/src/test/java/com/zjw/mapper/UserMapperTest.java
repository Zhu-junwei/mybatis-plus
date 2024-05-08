package com.zjw.mapper;

import com.zjw.domain.User;
import com.zjw.enums.UserEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 朱俊伟
 * @since 2023/11/15 16:34
 */
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList(){
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelectList2(){
        List<User> userList = userMapper.selectBatchIds(List.of(1L,2L));
        userList.forEach(System.out::println);
    }

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
                .status(UserEnum.Status.NORMAL)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        int insert = userMapper.insert(user);
        System.out.println("insert = " + insert);
    }

    @Test
    public void testUpdate(){
        User user = User.builder()
                .id(5L)
                .updateTime(LocalDateTime.now())
                .build();
        int i = userMapper.updateById(user);
        System.out.println("i = " + i);
    }

    @Test
    public void testDelete(){
        int i = userMapper.deleteById("5");
        System.out.println("i = " + i);
    }

}