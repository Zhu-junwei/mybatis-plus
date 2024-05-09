package com.zjw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zjw.mapper")
public class MybatisPlus01QuickstartApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlus01QuickstartApplication.class, args);
    }

}
