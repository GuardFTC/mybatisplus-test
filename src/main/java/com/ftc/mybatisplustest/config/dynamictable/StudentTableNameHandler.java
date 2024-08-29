package com.ftc.mybatisplustest.config.dynamictable;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import org.springframework.util.Assert;

/**
 * @author: 冯铁城 [17615007230@163.com]
 * @date: 2024-04-19 15:30:32
 * @describe: 学生表名处理器
 */
public class StudentTableNameHandler implements TableNameHandler {

    /**
     * 动态更新表名
     *
     * @param sql       当前执行 SQL
     * @param tableName 表名
     * @return 动态表名 例如：student_ftc
     */
    @Override
    public String dynamicTableName(String sql, String tableName) {

        //1.获取表名后缀
        String suffix = STUDENT_SUFFIX_DATA.get();

        //2.判定后缀是否为空
        Assert.isTrue(StrUtil.isNotBlank(suffix), "表名动态更新异常：未设置后缀");

        //3.构建表名返回
        return getTableName(suffix);
    }

    /**
     * 默认表名
     */
    public static final String DEFAULT_TABLE_NAME = "student";

    /**
     * 获取表名
     *
     * @param suffix 表名后缀
     * @return 动态学生表名 例如：student_ftc
     */
    public static String getTableName(String suffix) {
        return StrUtil.format("{}_{}", DEFAULT_TABLE_NAME, suffix);
    }

    /**
     * 学生表后缀DATA
     */
    private static final ThreadLocal<String> STUDENT_SUFFIX_DATA = new ThreadLocal<>();

    /**
     * 设置当前线程学生表后缀
     *
     * @param suffix 学生表后缀
     */
    public static void setSuffix(String suffix) {
        STUDENT_SUFFIX_DATA.set(suffix);
    }

    /**
     * 移除当前线程学生表后缀
     */
    public static void removeSuffix() {
        STUDENT_SUFFIX_DATA.remove();
    }
}
