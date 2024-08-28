package com.ftc.mybatisplustest.dao;

import com.ftc.mybatisplustest.entity.StudentEntity;
import com.ftc.mybatisplustest.mybatisplus.CustomMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 学生表 Mapper 接口
 * </p>
 *
 * @author 冯铁城 [17615007230@163.com]
 * @since 2024-08-28
 */
@Mapper
public interface StudentMapper extends CustomMapper<StudentEntity> {

}
