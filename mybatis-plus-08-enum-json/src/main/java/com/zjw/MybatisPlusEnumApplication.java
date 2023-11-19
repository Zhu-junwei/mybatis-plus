package com.zjw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zjw.mapper")
public class MybatisPlusEnumApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusEnumApplication.class, args);
    }

}
