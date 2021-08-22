package com.gxweb.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * MySQLConfig
 */
public class MySQLConfig extends MySQL5InnoDBDialect {
    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}