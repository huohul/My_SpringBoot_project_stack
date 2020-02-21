package com.gxweb.entity.second;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/21 19:48
 * @description ：从数据源实体类
 * @version: 1.0
 */
@Entity
@Data
@Table(name="person")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecondNetworkDiskDate implements Serializable {
    @Id
    private Long id;

    private Integer age;

    private Long company_id;

    private String name;

    private Long school_id;


}

