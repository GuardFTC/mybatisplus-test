package com.ftc.mybatisplustest;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ftc.mybatisplustest.dao.StudentMapper;
import com.ftc.mybatisplustest.entity.StudentEntity;
import com.ftc.mybatisplustest.mybatisplus.MybatisPlusUtil;
import com.ftc.mybatisplustest.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class StudentServicesTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper studentMapper;

    private final static int DEFAULT_SIZE = 50000;

    @BeforeEach
    void removeData() {
        studentService.remove(new QueryWrapper<>());
        log.info("测试数据清理完成");
    }

    @Test
    void insertStudentBatch() {

        //1.获取10万条学生数据集合
        List<StudentEntity> students = getStudents(DEFAULT_SIZE);

        //2.创建计时器
        TimeInterval timer = DateUtil.timer();

        //3.批量保存
        studentService.saveBatch(students);

        //4.打印时间
        log.info("原生mybatis-plus批量保存{}条数据耗时:{}ms", DEFAULT_SIZE, timer.interval());
    }

    @Test
    void insertStudentBatchMoreFast() {

        //1.获取10万条学生数据集合
        List<StudentEntity> students = getStudents(DEFAULT_SIZE);

        //2.创建计时器
        TimeInterval timer = DateUtil.timer();

        //3.批量保存
        MybatisPlusUtil.saveBatch(students, studentMapper, "学生数据");

        //4.打印时间
        log.info("优化mybatis-plus批量保存{}条数据耗时:{}ms", DEFAULT_SIZE, timer.interval());
    }

    /**
     * 获取指定数量的学生数据集合
     *
     * @param size 集合大小
     * @return 指定数量的学生数据集合
     */
    private static List<StudentEntity> getStudents(int size) {

        //1.定义结果集
        List<StudentEntity> students = CollUtil.newArrayList();

        //2.创建10万条数据
        for (int i = 0; i < size; i++) {

            //3.初始化实体类
            StudentEntity student = new StudentEntity();

            //4.设置属性
            student.setName("student" + i);
            student.setAge(i);

            //5.存入集合
            students.add(student);
        }

        //6.返回数据
        return students;
    }
}