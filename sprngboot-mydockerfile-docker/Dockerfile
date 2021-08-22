# 基础镜像 基于哪个镜像       使用 java:8 build 镜像很大 下个项目使用 op
FROM openjdk:8-jdk-alpine
#FROM java:8

# 作者信息
MAINTAINER "cyq"

# 添加一个存储空间 将本地文件夹挂在到当前容器
VOLUME /tmp

#复制文件到容器
ADD sprngboot-mydockerfile-docker-0.0.1-SNAPSHOT.jar /linusJpaDruid.jar

# 暴露8080端口
EXPOSE 9102

#配置容器启动后执行的命令
ENTRYPOINT ["java","-jar","/linusJpaDruid.jar"]