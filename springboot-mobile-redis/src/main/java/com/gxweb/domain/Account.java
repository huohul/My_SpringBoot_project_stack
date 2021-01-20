package com.gxweb.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ： CYQ
 * @date ：Created in 2021/1/20/020 21:28
 * @description ：实体类
 * @version: 1.0
 */
@Data
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * name
     */
    private String name;

    /**
     * money
     */
    private Float money;

    public Account() {
    }
}
