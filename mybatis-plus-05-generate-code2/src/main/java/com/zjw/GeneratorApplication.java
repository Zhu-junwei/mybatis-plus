package com.zjw;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;

public class GeneratorApplication {

    public static void main(String[] args) {
        String outputDir = System.getProperty("user.dir") + "/code/src/main" ;

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mp?serverTimezone=Asia/Shanghai&useSSL=false&characterEncoding=utf-8" , "root" , "123456")
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("zjw") // 设置作者
                            .disableOpenDir()   // 禁止打开输出目录
                            .outputDir(outputDir + "/java"); // 指定输出目录
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.zjw") // 设置父包名
                            .entity("domain")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, outputDir + "/resources/mapper")); // 设置mapperXml生成路径
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder
                            // 设置需要生成的表名，不设置默认是所有表
                            .addInclude(new ArrayList<String>() {{
                                add("user");
                            }})
                            // 跳过视图
                            .enableSkipView()

                            // Entity 策略配置
                            .entityBuilder().enableLombok().enableFileOverride()

                            // Controller 策略配置
                            .controllerBuilder().enableFileOverride()

                            // Service 策略配置
                            .serviceBuilder().enableFileOverride()

                            // Mapper 策略配置
                            .mapperBuilder().enableFileOverride();
                })
                // 模板配置
                .templateConfig(builder -> {})
                // 引擎模板，默认的是Velocity引擎模板
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }

}
