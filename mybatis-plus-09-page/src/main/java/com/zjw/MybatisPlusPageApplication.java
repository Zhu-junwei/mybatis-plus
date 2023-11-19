package com.zjw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zjw.mapper")
public class MybatisPlusPageApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusPageApplication.class, args);
    }

}
