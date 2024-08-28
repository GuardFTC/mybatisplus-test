package com.ftc.mybatisplustest.mybatisplus;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2024-04-02 17:23:19
 * @describe: MybatisPlus扩展工具类
 */
@Slf4j
public class MybatisPlusUtil {

    /**
     * 默认触发批量保存数量阈值
     */
    private final static int DEFAULT_THRESHOLD = 2000;

    /**
     * 分批次批量保存数据
     *
     * @param dataList     数据集合
     * @param customMapper 自定义Mapper,用于批量插入
     * @param dataLogo     数据标识-用于日志打印
     * @param <T>          数据泛型类
     */
    public static <T> void saveBatch(List<T> dataList, CustomMapper<T> customMapper, String dataLogo) {
        saveBatch(dataList, customMapper, DEFAULT_THRESHOLD, dataLogo);
    }

    /**
     * 分批次批量保存数据
     *
     * @param dataList       数据集合
     * @param customMapper   自定义Mapper,用于批量插入
     * @param countThreshold 触发批量保存数量阈值
     * @param dataLogo       数据标识-用于日志打印
     * @param <T>            数据泛型类
     */
    public static <T> void saveBatch(List<T> dataList, CustomMapper<T> customMapper, int countThreshold, String dataLogo) {

        //1.初始化计数器
        int count = 0;

        //2.定义暂存结果集
        List<T> saveList = CollUtil.newArrayList();

        //3.初始化计时器
        TimeInterval timer = DateUtil.timer();

        //4.循环保存
        for (T data : dataList) {

            //5.存入暂存结果集,计数器++
            saveList.add(data);
            count++;

            //6.到达存储数量阈值开始储存
            if (countThreshold == saveList.size() || count == dataList.size()) {
                customMapper.insertBatchSomeColumn(saveList);
//                log.info("[{}]数据批量保存完成 共保存[{}]条", dataLogo, saveList.size());
                saveList.clear();
            }
        }

        //7.打印完成日志
//        log.info("[{}]数据保存完成，共保存:[{}]条 耗时:[{}]ms", dataLogo, dataList.size(), timer.interval());
    }
}
