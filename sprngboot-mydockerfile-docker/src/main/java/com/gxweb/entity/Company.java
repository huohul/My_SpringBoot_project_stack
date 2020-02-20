package com.gxweb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 公司
 */
@Entity
@Data
@Table(name = "company")
@AllArgsConstructor
@NoArgsConstructor
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String companyName;  //公司名
    private String description; //描述

    public Company(String name, String description) {
        this.companyName = name;
        this.description = description;
    }
}
