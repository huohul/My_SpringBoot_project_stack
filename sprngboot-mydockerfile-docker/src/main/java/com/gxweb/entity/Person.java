package com.gxweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/16 16:43
 * @description ：用户实体类
 * @version: 1.0
 * <p>
 * 在jpa 数据库中字段 带 _ 实体类中可不带已大写字母替换
 */
@Entity
@Data
@Table(name = "person")
@AllArgsConstructor //生成所有参数构造
@NoArgsConstructor //生成空参构造
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private Integer age;
    private Long companyId;
    private Long schoolId;

    public Person(String name, Integer age, Long companyId) {
        this.name = name;
        this.age = age;
        this.companyId = companyId;
    }
}

