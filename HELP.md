* ## My_SpringBoot_project_stack

  My_SpringBoot_project_stack 是本人在学习SpringBoot的一些工程！

  下载链接： git@github.com:huohul/My_SpringBoot_project_stack.git
  
    ## 各 Module 介绍
 Module 名称             ----------------------------------------                 Module 介绍                                                 

   
    springboot-error-handler                    统一异常处理
   
    springboot-jpa-multilist                    sprngboot 整合jpa 实现自定义查询方法 和 多表链表查询  plus版  + druid 监控配置
    
    springboot-mongodb                          springboot 整合mongoDB 实现CRUD     
        
    springboot-multi-datasource-jpa             演示 Spring Boot 如何集成 JPA 的多数据源
    
    springboot-multi-datasource-mybatisplus      主要演示了 Spring Boot 如何集成 Mybatis 的多数据源。可以自己基于AOP实现多数据源，
                                                             
    springboot-rabbitmq                         springboot 整合rebbitMQ  实现四种模式的消息发送与接收        
    
    springboot-sub-db-table-read-write          实现分库分表 ms0 ms1  库 分表   读写分离 还未实现  读数据是从 sl0/1  表中读  数据为从主库中拉取  待以后完善
   
    springboot-sub-db-table-sharding-jdbc        MybatisPlus集成 sharding-jdbc 实现分库分表操作
   
    springboot-test-async                       spring-boot 使用原生提供的异步任务支持，实现异步执行任务
   
    springboot-thymeleaf-web                    采用 Bootstrp 框架 数据渲染采用 Thymeleaf 进行遍历展示 持久层使用Jpa 页面完成CRUD
    
    springboot_activemq                         实现activeMQ的消息队列 与发布订阅模式

    springcloud-es_parent                       SpringCloud基于eurka​​实现feign远程调用与Es简单操作
    
    sprngboot-mydockerfile-docker               使用Dockerfile 对springboot-jpa-multilist 进行镜像制作及部署
    


    完备的发送邮件 项目 G:\IDEAGIt\SpringBootAndSpringCloud_The_total_stack\spring-boot-demo\spring-boot-demo-email 

    spirngboot 实现过滤器与拦截器 G:\IDEAGIt\Snailclimb\springboot-guide\source-code\basis\springboot-filter-interceptor 内附文档




##获取知识 
 ####Spring 的 BeanUtils
      使用spring的BeanUtils进行对象拷贝   
                    //要保证实体类成员变量相同 
                    PersonSource personSource = new PersonSource(1, "pjmike", "12345", 21);
                    PersonDest personDest = new PersonDest();
                    BeanUtils.copyProperties(personSource,personDest);