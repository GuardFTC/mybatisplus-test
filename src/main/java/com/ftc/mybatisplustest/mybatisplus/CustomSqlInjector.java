package com.ftc.mybatisplustest.mybatisplus;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;

import java.util.List;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2024-04-02 17:31:54
 * @describe: 自定义Sql注入器
 */
public class CustomSqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {

        //1.获取方法集合
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);

        //2.新增批量插入方法设置,更新时自动填充的字段,不用插入值
        methodList.add(new InsertBatchSomeColumn(i -> i.getFieldFill() != FieldFill.UPDATE));

        //3.返回
        return methodList;
    }
}
