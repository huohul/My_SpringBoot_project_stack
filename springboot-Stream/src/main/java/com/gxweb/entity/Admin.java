package com.gxweb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;


/**
 * @author ： CYQ
 * @date ：Created in 2021/2/11/011 21:04
 * @description ：
 * @version: 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Admin {
    private String username;
    private String password;
    private double money;
    private ArrayList<String> list = new ArrayList<String>();

    @Override
    public String toString() {
        return username + "\t" + password;
    }

    public Admin(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
}
