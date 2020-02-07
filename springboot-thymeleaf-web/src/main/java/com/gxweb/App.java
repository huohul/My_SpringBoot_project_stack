package com.gxweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot 整合 web 项目找不到 jsp 文件   BUG
 * https://www.cnblogs.com/ming-blogs/p/11032450.html
 * 访问报404  找不到 资源  运行 配置 Environment 下 Working directory  为本项目绝对路径
 *  还要注意 Spring 版本不要过高
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("springboot 启动成功");
    }
}



