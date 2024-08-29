package com.ftc.mybatisplustest;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ftc.mybatisplustest.config.dynamictable.StudentTableNameHandler;
import com.ftc.mybatisplustest.entity.StudentEntity;
import com.ftc.mybatisplustest.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class DynamicTableNameTest {

    @Autowired
    private StudentService studentService;

    @BeforeEach
    void removeData() {
        try {

            //1.定义表名后缀
            String suffix = "ftc";

            //2.清理测试数据
            StudentTableNameHandler.setSuffix(suffix);
            studentService.remove(new QueryWrapper<>());

            //3.重置后缀名
            suffix = "fyy";

            //4.清理测试数据
            StudentTableNameHandler.setSuffix(suffix);
            studentService.remove(new QueryWrapper<>());
        } finally {
            StudentTableNameHandler.removeSuffix();
        }
        log.info("测试数据清理完成");
    }

    @Test
    void dynamicTableName() {
        try {

            //1.定义表名后缀
            String suffix = "ftc";

            //2.设置表名后缀
            StudentTableNameHandler.setSuffix(suffix);

            //3.保存学生对象并进行查询校验
            saveStudentAndCheck(suffix);

            //4.重置后缀名
            suffix = "fyy";

            //5.设置表名后缀
            StudentTableNameHandler.setSuffix(suffix);

            //6.保存学生对象并进行查询校验
            saveStudentAndCheck(suffix);
        } finally {
            StudentTableNameHandler.removeSuffix();
        }
    }

    /**
     * 保存学生对象并进行查询校验
     *
     * @param suffix 表名后缀
     */
    private void saveStudentAndCheck(String suffix) {

        //1.创建学生实体类
        StudentEntity student = new StudentEntity();

        //2.设置属性
        student.setName(suffix);
        student.setAge(18);

        //3.保存对象
        studentService.save(student);

        //4.查询对象
        StudentEntity searchStudent = studentService.getOne(new QueryWrapper<StudentEntity>().eq("name", suffix));

        //5.校验
        Assert.isTrue(student.getName().equals(searchStudent.getName()));
        Assert.isTrue(student.getAge().equals(searchStudent.getAge()));
    }
}