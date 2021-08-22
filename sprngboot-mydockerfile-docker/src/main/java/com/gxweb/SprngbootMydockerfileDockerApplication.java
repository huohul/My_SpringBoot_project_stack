package com.gxweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot + jpa + thymeleaf 实现自定义查询条件 plus 版 多表 链表查询 + druid 配置与 监控页面 + dockerFile   +  Alibaba Cloud Toolkit 部署
 * 监控页面 http://localhost:9102/druid/login.html  docker部署后无法访问 Sorry, you are not permitted to view this page.
 * SpringBoot配置MySql数据库和Druid连接池 https://www.cnblogs.com/feiyangbahu/p/9842363.html
 * <p>
 * <p>
 * ① pom 依赖参考：  https://blog.csdn.net/Axela30W/article/details/82183853?utm_source=distribute.pc_relevant.none-task
 * ② Dockerfile  参考 ： https://www.jianshu.com/p/ef3b17647c2d
 * <p>
 * 使用Dockerfile构建简单的微服务镜像
 * 这是第一个使用Dockerfile 制作docker镜像的demo  制作步骤
 * 一 ：引入依赖 上 下两部分  见①
 * 二 ：修改Dockerf 注意是项目下一级文件 不重要
 * 三 ：Manen  清理下 clean   然后打包 package  target 下生成jar 包
 * 四 ：将jar 包与 Dockerf 文件复制到 linux 内自定义目录 要知道在哪里放着 不然linux 一团糟
 * 本项目在： /usr/local/etc/dockerfilework/dockerfile002
 * 五 ：然后执行命令 docker build -t 项目名:0.0.1 .   注意后面有一点 ok查看下是否在 docker images
 * 六 ：使用命令启动docker镜像  docker run -d -p 8761:8761 dockerfile-docker-0.0.1:latest
 * 我这里失败
 * 解决方案 通过idea docker连接 找到images 下镜像 鼠标右键第一项 填好名称 与端口映射 就ok了
 * <p>
 * 选择头上导航栏 Tools  --> Alibaba Cloud -->  第一项  然后参考 https://yq.aliyun.com/articles/676151
 * 具体细节
 * 1选中ip  2保存在linux的路径  3 启动命令  4Befour 选中项目 下面 clean package 清除打包 运行就ok
 * 杀死 运行jar
 * ps aux|grep sprngboot-mydockerfile-docker-0.0.1-SNAPSHOT.jar
 * 第二行 首端口就是   杀死命令 kill -9 6916
 */
@SpringBootApplication
public class SprngbootMydockerfileDockerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SprngbootMydockerfileDockerApplication.class, args);
    }

}
