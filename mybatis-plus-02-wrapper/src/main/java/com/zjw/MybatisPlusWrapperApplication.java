package com.zjw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zjw.mapper")
public class MybatisPlusWrapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusWrapperApplication.class, args);
    }

}
