package com.zjw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zjw.mapper")
public class MybatisPlusDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusDbApplication.class, args);
    }

}
