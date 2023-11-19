package com.zjw.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 朱俊伟
 * @date 2023/11/17 14:58
 */
public class UserEnum {

    @AllArgsConstructor
    @Getter
    public enum Status{
        NORMAL(1,"正常"),
        FREEZE(2,"冻结"),
        ;
        @EnumValue
        @JsonValue
        private final int value;
        private final String desc;
    }
}
