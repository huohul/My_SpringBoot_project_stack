package com.gxweb.iot;

/**
 * @author : CYQ
 * @version : 1.0
 * @date : Created in 2024/1/23 17:51
 * @description : Spring AOP + 自定义注解 + 动态数据源 实现主从库切换&读写分离
 * @url: https://mp.weixin.qq.com/s/IkSu71wyo9aFUuIk3xCDbg
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gxweb.iot.mapper")
public class AopSeparateLibraryAndTableApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopSeparateLibraryAndTableApplication.class, args);
    }
}
