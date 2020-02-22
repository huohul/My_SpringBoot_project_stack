package com.gxweb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/21 22:37
 * @description ：实体类
 * @version: 1.0
 */
@Data
@TableName("person")   //主要是当实体类名字和表名不满足 驼峰和下划线互转 的格式时，用于表示数据库表名
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person implements Serializable {

    private static final long serialVersionUID = -1923859222295750467L;

    /**
     * 主键
     * TableId是 Mybatis-Plus 注解，主要是指定主键类型，这里我使用的是 Mybatis-Plus 基于 twitter 提供的 雪花算法
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    private Integer age;
    private Integer companyId;
    private String name;
    private Integer schoolId;

}
