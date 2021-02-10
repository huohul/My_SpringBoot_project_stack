package com.gxweb;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
/**
 * springboot  整合MongoDB  + 布隆过滤器的案例 Test中
 * MongoDB 最全攻略  https://mp.weixin.qq.com/s/F-NM-NkyZ0iGMF49wwbtbQ
 */
@SpringBootApplication
//@EnableHystrixDashboard  //开启服务熔断
public class SpringbootMongodbApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootMongodbApplication.class, args);
    }
    //注入雪花算法 分布式id生成器
    @Bean
    public Snowflake snowflake() {
        return IdUtil.createSnowflake(1, 1);
    }

}