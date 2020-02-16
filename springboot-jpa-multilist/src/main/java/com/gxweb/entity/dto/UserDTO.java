package com.gxweb.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Builder(toBuilder = true)   //可以方便的时间建造者模式   https://www.codercto.com/a/84108.html
@AllArgsConstructor
public class UserDTO implements Serializable {
    private String name;
    private int age;
    private String companyName;
    private String schoolName;
}
