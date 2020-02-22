package com.gxweb.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (TabUser0)实体类
 *
 * @author makejava
 * @since 2020-02-22 23:36:27
 */
@Data
public class TabUser0 implements Serializable {
    private static final long serialVersionUID = 397063412580313259L;
    /**
    * 主键
    */
    private Long id;
    /**
    * 姓名
    */
    private String name;
    /**
    * 性别
    */
    private String sex;
    /**
    * 年龄
    */
    private Integer age;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 是否删除 1删除 0未删除
    */
    private Integer status;

    public TabUser0(Long id, String name, String sex, Integer age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
    }


}