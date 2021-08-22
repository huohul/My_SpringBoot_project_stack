package com.gxweb.mp.po;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ： CYQ
 * @date ：Created in 2021/6/6 18:55
 * @description ：
 * @version: 1.0
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Long managerId;
    private LocalDateTime createTime;
}