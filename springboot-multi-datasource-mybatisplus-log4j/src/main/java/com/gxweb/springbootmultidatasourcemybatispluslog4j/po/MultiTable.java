package com.gxweb.springbootmultidatasourcemybatispluslog4j.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * (MultiTable)实体类
 *
 * @author makejava
 * @since 2021-01-30 12:55:40
 */
@TableName(value = "multi_table")
public class MultiTable implements Serializable {
    private static final long serialVersionUID = -1923859222295750467L;

    /**
     * 主键
     * TableId是 Mybatis-Plus 注解，主要是指定主键类型，这里我使用的是 Mybatis-Plus 基于 twitter 提供的 雪花算法
     */
    @TableId(type = IdType.ID_WORKER)
    private Long id;

    @TableField(value = "name", exist = true)
    private String name;

    //使用@TableLogic注解实现逻辑删除
    @TableLogic
    @TableField(value = "deleted", exist = true)
    protected Integer deleted = 0;

    //@TableField为字段注解，value为字段值，exist是否为数据库表字段（默认true存在，false不存在）
    @TableField(value = "gmt_create", exist = true)
    protected Date gmtCreate;

    @TableField(value = "gmt_modified", exist = true)
    protected Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public MultiTable() {
    }

    public MultiTable(Long id, String name, Integer deleted, Date gmtCreate, Date gmtModified) {
        this.id = id;
        this.name = name;
        this.deleted = deleted;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "MultiTable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deleted=" + deleted +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiTable that = (MultiTable) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(deleted, that.deleted) &&
                Objects.equals(gmtCreate, that.gmtCreate) &&
                Objects.equals(gmtModified, that.gmtModified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, deleted, gmtCreate, gmtModified);
    }
}