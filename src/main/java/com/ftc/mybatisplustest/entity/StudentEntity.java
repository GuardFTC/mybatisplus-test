package com.ftc.mybatisplustest.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author 冯铁城 [17615007230@163.com]
 * @since 2024-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("student")
@ApiModel(value = "StudentEntity对象", description = "学生表")
public class StudentEntity extends Model<StudentEntity> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
