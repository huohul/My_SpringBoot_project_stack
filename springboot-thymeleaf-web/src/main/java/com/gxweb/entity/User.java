package com.gxweb.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "t_user")
@Entity
@Data
public class User {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 姓名
     */
    //@Column(name = "username")如果属性名称与数据一致，则自动映射
    @Column(nullable = false, unique = true)
    private String name;
    /**
     * 密码
     */
    @Column(nullable = false)
    private String password;
    /**
     * 年龄
     */
    @Column(nullable = false)
    private Integer age;

}




