package com.gxweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ： CYQ
 * @date ：Created in 2021/2/10/010 19:01
 * @description ：实体类 供StreamTest 使用
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;  // 姓名
    private int salary; // 薪资
    private int age; // 年龄
    private String sex; //性别
    private String area;  // 地区

    public Person(String tom, int i, String male, String new_york) {
    }
}
