package com.gxweb;

import net.hasor.spring.boot.EnableHasor;
import net.hasor.spring.boot.EnableHasorWeb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Dataway  绝了！这款工具让 Spring Boot 不在需要 Controller、Service、DAO、Mapper 了
 * https://mp.weixin.qq.com/s/vBSkWIeY7wXIchMf5jQrqA
 */
@EnableHasor()
@EnableHasorWeb()
@SpringBootApplication(scanBasePackages = {"com.gxweb.hasor"})
public class SpringbootDatawayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDatawayApplication.class, args);
    }

}
