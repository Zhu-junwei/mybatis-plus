package com.zjw.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.zjw.enums.UserEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author 朱俊伟
 * @since 2023/11/15 16:33
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//value: 表名和实体名不一致时可以设置
//excludeProperty: 排除字段
@TableName(value = "user",excludeProperty = {"email"},autoResultMap = true)
public class User {

    // IdType.AUTO 数据库自动增长，也可在配置文件中进行全局设置
    // 不设置默认为雪花算法生成的id
    // IdType.INPUT 为手动设置
    @TableId(type = IdType.AUTO)
    private Long id;

    //普通字段(非主键) 名字和数据库不一致可以自己指定
    @TableField("username")
    private String username;
    private String password;
    private String phone;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private UserInfo info;
    /*用户状态*/
    private UserEnum.Status status;
    private Integer balance;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    //排除的字段，数据库不存在时需要排除，不然会报错
    private String email;

    @Data
    @AllArgsConstructor(staticName = "of")
    @NoArgsConstructor
    @Builder
    public static class UserInfo {
        /*年龄*/
        private Integer age;
        /*介绍*/
        private String intro;
        /*性别*/
        private String gender;
    }

}
