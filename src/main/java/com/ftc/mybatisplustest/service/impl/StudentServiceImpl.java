package com.ftc.mybatisplustest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ftc.mybatisplustest.dao.StudentMapper;
import com.ftc.mybatisplustest.entity.StudentEntity;
import com.ftc.mybatisplustest.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生表 服务实现类
 * </p>
 *
 * @author 冯铁城 [17615007230@163.com]
 * @since 2024-08-28
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {

}
