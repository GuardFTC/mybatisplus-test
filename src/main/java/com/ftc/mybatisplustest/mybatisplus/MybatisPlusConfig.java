package com.ftc.mybatisplustest.mybatisplus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2024-04-02 17:33:53
 * @describe: MybatisPlus配置
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public CustomSqlInjector sqlInjector() {
        return new CustomSqlInjector();
    }
}
