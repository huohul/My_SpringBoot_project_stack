* ## My_SpringBoot_project_stack

  My_SpringBoot_project_stack 是本人在学习SpringBoot的一些工程！

  下载链接： git@github.com:huohul/My_SpringBoot_project_stack.git
  
    ## 各 Module 介绍
 Module 名称             ----------------------------------------                 Module 介绍                                                 

    springboot-dataway                          Dataway  绝了！这款工具让 Spring Boot 不在需要 Controller、Service、DAO、Mapper 了
   
    springboot-test-async                       spring-boot 使用原生提供的异步任务支持，实现异步执行任务 + debug 调试技巧  +  十大排序算法
    
    springboot-distributed-redisson             springboot-distributed-redisson Redisson 实现分布式锁 + 多线程
    
    springboot-error-handler                    统一异常处理
   
    springboot-jpa-multilist                    sprngboot 整合jpa 实现自定义查询方法 和 多表链表查询  plus版  + druid 监控配置
    
    springboot-jwt-security                    SpringBoot整合security示例实现权限注解+JWT登录认证
    
    springboot-mobile-redis                     SpringBoot实战：整合Redis、mybatis，封装RedisUtils工具类等 最后附Jmeter 压测工具
    
    springboot-mongodb                          springboot 整合mongoDB 实现CRUD + 布隆过滤器的案例（Test中）    
    
    springboot-mq-kafka-bigen                    springboot + kafka  实现消息订阅 发送接收 与 发送返回值接收
        
    springboot-multi-datasource-jpa             演示 Spring Boot 如何集成 JPA 的多数据源
    
    springboot-multi-datasource-mybatisplus     主要演示了 Spring Boot 如何集成 Mybatis 的多数据源。可以自己基于AOP实现多数据源，
                                                             
    springboot-multi-datasource-mybatisplus-log4j           Springboot + mybatis plus +log4j2 进行分库分表 多数据源 主从复制 日志处理
    
    springboot-mybatis-swagger2-document-the-export         spirngboot +  swagger2 + 文档导出 内附导出工具类
    
    springboot-mybatisplus-anntation            springboot+ mybatisplus  基于注解链表查询  加入了 index vue结合bootstrap 简单渲染数据 需引入 thymeleaf 依赖
   
    springboot-rabbitmq                         springboot 整合rebbitMQ  实现四种模式的消息发送与接收        
    
    springboot-redis-annotantion-interceptor-interface-idempotencycheck  Spring Boot + Redis + 注解 + 拦截器来实现接口幂等性校验
    
    springboot-shiro                            Shiro安全框架【快速入门】就这一篇！  没有跑通  未上传Github
    
    springboot-Stream                           2万字20个实例解析Java8 Stream，带你玩转集合四大点！+停车场系统
    
    springboot-sub-db-table-read-write          实现分库分表 ms0 ms1  库 分表   读写分离 还未实现  读数据是从 sl0/1  表中读  数据为从主库中拉取  待以后完善
   
    springboot-sub-db-table-sharding-jdbc        MybatisPlus集成 sharding-jdbc 实现分库分表操作
   
    springboot-thymeleaf-web                    采用 Bootstrp 框架 数据渲染采用 Thymeleaf 进行遍历展示 持久层使用Jpa 页面完成CRUD
    
    springboot_activemq                         实现activeMQ的消息队列 与发布订阅模式

    springcloud-es_parent                       SpringCloud基于eurka​​实现feign远程调用与Es简单操作
    
    sprngboot-mydockerfile-docker               使用Dockerfile 对springboot-jpa-multilist 进行镜像制作及部署  Alibaba Cloud Toolkit 部署
    
    

    完备的发送邮件 项目 G:\IDEAGIt\SpringBootAndSpringCloud_The_total_stack\spring-boot-demo\spring-boot-demo-email 

    spirngboot 实现过滤器与拦截器 G:\IDEAGIt\Snailclimb\springboot-guide\source-code\basis\springboot-filter-interceptor 内附文档




##获取知识 
 ####Spring 的 BeanUtils
      使用spring的BeanUtils进行对象拷贝   
                    //要保证实体类成员变量相同 
                    PersonSource personSource = new PersonSource(1, "pjmike", "12345", 21);
                    PersonDest personDest = new PersonDest();
                    BeanUtils.copyProperties(personSource,personDest);
                    
      mybatisPlus 
                根据用户名查询实体
                 public SysUserEntity selectUserByName(String username) {
                        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
                        queryWrapper.lambda().eq(SysUserEntity::getUsername,username);
                        return this.baseMapper.selectOne(queryWrapper);
                    }
                    
     
     MyBatis Plus   分布式id设成策略  springboot-mongodb  也有
      //TableId为主键注解，value为字段值，type主键ID策略类型
         @TableId(value = "id", type = IdType.AUTO)
         private Long id;      
           
                    
  ###java葵花宝典
   [还看不懂同事的代码？超强的 Stream 流操作姿势还不学习一下](https://mp.weixin.qq.com/s/4yJarUD3PhBrxa2Z2RmPag)  ------>  [GitHub地址](https://github.com/niumoo/jdk-feature/blob/master/src/main/java/net/codingme/feature/jdk8/Jdk8Stream.java )
   
   [Spring Boot + RabbitMQ发送邮件(保证消息 100% 投递成功并被消费)](https://mp.weixin.qq.com/s/nHmRJLatsvBNP3p3-Tb_7Q) 物理地址： G:\IDEABaiS\2_small_functiona_ items\springboot-wxw
   
   [冒着挂科的风险也要给你们看的 Spring Cloud 入门总结](https://mp.weixin.qq.com/s/zu39BPRXotuWm6g3R8Dr8w)