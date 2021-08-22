package com.gxweb.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mybatis plus 看这篇就够了，一发入魂
 * https://mp.weixin.qq.com/s/lptPM8IWykVc2-dRYEBhvQ
 */
@SpringBootApplication
@MapperScan("com.gxweb.mp.mappers")
public class SpringbootMpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMpApplication.class, args);
    }

}
