package com.ftc.mybatisplustest.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.ftc.mybatisplustest.config.dynamictable.StudentTableNameHandler;
import com.ftc.mybatisplustest.config.savebatch.CustomSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2024-04-02 17:33:53
 * @describe: MybatisPlus配置
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * MybatisPlus拦截器
     *
     * @return MybatisPlus拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        //1.初始化MybatisPlus拦截器
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        //2.创建动态表名处理器Map
        Map<String, TableNameHandler> tableNameHandlerMap = new HashMap<>(1);
        tableNameHandlerMap.put(StudentTableNameHandler.DEFAULT_TABLE_NAME, new StudentTableNameHandler());

        //3.获取动态表名拦截器
        DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();

        //4.设置动态表名处理器Map
        dynamicTableNameInnerInterceptor.setTableNameHandlerMap(tableNameHandlerMap);

        //5.设置动态表名拦截器
        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);

        //8.返回拦截器
        return interceptor;
    }

    /**
     * 自定义批量保存拦截器
     *
     * @return 自定义批量保存拦截器
     */
    @Bean
    public CustomSqlInjector sqlInjector() {
        return new CustomSqlInjector();
    }
}
