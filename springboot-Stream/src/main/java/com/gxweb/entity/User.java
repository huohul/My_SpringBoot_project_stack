package com.gxweb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * @author ： CYQ
 * @date ：Created in 2021/2/11/011 21:07
 * @description ：
 * @version: 1.0
 */
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Getter
public class User {
    private String carid; //车牌登录

    @Override
    public String toString() {
        return "车牌为" + carid;
    }

}
