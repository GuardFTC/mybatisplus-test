package com.ftc.mybatisplustest.mybatisplus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2024-04-02 17:34:33
 * @describe: 自定义Mapper
 */
public interface CustomMapper<T> extends BaseMapper<T> {

    /**
     * 批量插入
     *
     * @param dataList 数据集合
     * @return 保存成功的数量
     */
    int insertBatchSomeColumn(List<T> dataList);
}
