#常用SQL语句，看这篇就够了
##一、摘要
本文主要以 Mysql 数据库为基础，对常用 SQL 语句进行一次深度总结，由于篇幅较长，难免会有些遗漏的地方，欢迎网友批评指出！

具体内容主要有以下几个部分：

    库操作
    表操作
    数据操作
    运算符
    视图
    函数
    存储过程
    触发器
    序列
    用户权限
##二、库操作
###2.1、新增库
创建数据库比较简单，在创建的时候直接指定字符集、排序规则即可！

    CREATE DATABASE IF NOT EXISTS `库名` default charset utf8mb4 COLLATE utf8mb4_unicode_ci;
例子：

    CREATE DATABASE IF NOT EXISTS test_db default charset utf8mb4 COLLATE utf8mb4_unicode_ci;

###2.2、修改库名
数据库修改库名的有三种方法，如果是MyISAM存储引擎，那么可以直接去数据库目录mv就可以了，如果是Innodb完全不行，会提示相关表不存在。
####方法一

    RENAME database olddbname TO newdbname
                
这个语法在 mysql-5.1.7 中被添加进来，到了mysql-5.1.23又去掉了，官方不推荐，会有丢失数据的危险！

####方法二
思路是先创建一个新库，之后将旧库的数据导入到新库，即可完成修改库名！

1、创建需要改成新名的数据库。

2、mysqldum 导出要改名的数据库

3、删除原来的旧库（确定是否真的需要）

当然这种方法虽然安全，但是如果数据量大，会比较耗时，同时还需要考虑到磁盘空间等硬件成本。

例子：

    # 将db1库备份到db1.sql文件
    mysqldump -u root -p db1 > /usr/db1.sql;
    
    # 导入备份文件到新库db2
    mysql -u root -p db2 < /root/db1.sql;
    
    # 删除旧库（如果真的需要）
    DROP DATABASE db1;
    
####方法三
直接跑一个 shell 脚本！

    #!/bin/bash
    # 假设将db1数据库名改为db2
    # MyISAM直接更改数据库目录下的文件即可
    
    mysql -uroot -p123456 -e 'create database if not exists db2'
    list_table=$(mysql -uroot -p123456 -Nse "select table_name from information_schema.TABLES where TABLE_SCHEMA='db1'")
    
    for table in $list_table
    do
        mysql -uroot -p123456 -e "rename table db1.$table to db2.$table"
    done
其中p123456，p是password的简称，123456表示数据库密码值！

###2.3、删除库名
删除库，比较简单，直接删除即可！

    DROP DATABASE db1;   
###2.4、使用库
    USE db2; 
     
##三、表操作
###3.1、创建表
        CREATE TABLE ts_user (
          id bigint(20) unsigned NOT NULL COMMENT '编码',
          name varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户姓名',
          mobile varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '手机号',
          create_userid varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建人',
          create_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
          update_userid varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '更新人',
          update_time datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
          PRIMARY KEY (id),
          KEY idx_create_time (create_time) USING BTREE
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';
        
###3.2、修改表名
    ALTER  TABLE ts_user RENAME TO ts_new_user;
###3.3、删除表
    DROP TABLE ts_new_user;   
###3.4、字段操作
####3.4.1、查询表字段
    show full columns from ts_user;
####3.4.2、新增字段    
    ALTER TABLE ts_user add column gender tinyint(4) NOT NULL DEFAULT '1' COMMENT '性别，1，男；2，女' AFTER mobile;
####3.4.3、修改字段
    ALTER TABLE ts_user modify column mobile varchar(30) NOT NULL DEFAULT '' COMMENT '用户手机号';
####3.4.4、删除字段
    ALTER TABLE ts_user drop column gender; 
###3.5、索引操作
####3.5.1、查询表索引
     SHOW INDEXES FROM ts_user;
####3.5.2、新增普通索引
    alter table ts_user add index idx_id (id);
####3.5.3、新增唯一索引
    alter table ts_user add unique idx_id (id);
####3.5.4、新增主键索引
    alter table ts_user add primary key idx_id (id) ;
####3.5.5、新增多列索引
    alter table ts_user add index idx_id_name (id,name) ;
####3.5.6、新增全文索引
    alter table ts_user add fulltext idx_id (id) ;
####3.5.7、删除索引
    # 删除普通索引
    alter table ts_user drop index idx_id;
    
    # 删除主键索引
    alter table ts_user drop primary key;
 ---   
##四、数据操作

###4.1、查询操作

####4.1.1、单表查询
    select * from ts_user;
或者 

    select id, name from ts_user;
####4.1.2、关键字查询
#####and 查询
    select id, name from ts_user where name = '张三'
#####or 查询
    select id, name from ts_user where name = '张三' or name = '李四'
#####in 查询（参数个数不能超过1000）
    select id, name from ts_user where name in ('张三', '李四')
#####like 模糊查询（%属于通配符）
    select id, name from ts_user where name like '张%'
#####非空查询
    select id, name from ts_user where name is not null
#####区间字段查询
    select id, name, age from ts_user where  age >= 18 and age <= 30

    select id, name, age from ts_user where age between 18 and 30
#####多条件判断
    select 
    name,
    (
    case
    when scope >= 90 then  '优'
    when 80 <= scope < 90 then  '良'
    when 80 > scope >= 70  then  '中'
    else '差'
    end
    ) as judge
    from ts_user
    
####4.1.3、连表查询
#####左连接查询
    select tu.id, tu.name,tr.role_name
    from ts_user tu
    left join ts_role tr on tu.id = tr.user_id
#####右连接查询
    select tu.id, tu.name,tr.role_name
    from ts_user tu
    right join ts_role tr on tu.id = tr.user_id
#####内连接查询
    select tu.id, tu.name,tr.role_name
    from ts_user tu
    inner join ts_role tr on tu.id = tr.user_id
#####满连接查询  
    select tu.id, tu.name,tr.role_name
    from ts_user tu
    full join ts_role tr on tu.id = tr.user_id
####4.1.4、分组查询
#####统计学生总数
    select count(id) from ts_user
#####查询学生最大的年纪
    select max(age) from ts_user
#####查询学生最小的年纪
    select min(age) from ts_user
#####查询各个学生各项成绩的总和
    select id, sum(score) from ts_user group by id
#####查询各个学生各项成绩的平均分
    select id, avg(score) from ts_user group by id
#####查询各个学生各项成绩的平均分大于100的学生信息
    select id, avg(score) from ts_user group by id having avg(score)  > 100
---
###4.2、插入操作 
#####4.2.1、单列插入
    INSERT INTO ts_user(id, name) VALUES ('1', '张三');
#####4.2.2、多列插入
    INSERT INTO ts_user(id, name)
    VALUES
    ('1', '张三'),
    ('2', '李四'),
    ('3', '王五');
--- 
###4.3、修改操作
    update ts_user set name = '李四1', age = '18' where id = '1'
---
###4.4、 删除操作
    # 删除表全部内容
    delete from ts_user
    
    # 根据判断条件进行删除
    delete from ts_user where id = '1'
    
##五、运算符
    MySQL 主要有以下几种运算符：
    
    算术运算符
    比较运算符
    逻辑运算符
    位运算符
### 5.1、算术运算符
    运算符	描述	实例
    +	加法	select 1+2; 结果为3
    -	减法	select 1-2; 结果为-1
    *	乘法	select 2*3; 结果为6
    /	除法	select 6/3; 结果为2
    %	取余	select 10%3; 结果为1
说明：在除法运算和模运算中，如果除数为0，将是非法除数，返回结果为NULL。
###5.2、比较运算符
SELECT 语句中的条件语句经常要使用比较运算符。通过这些比较运算符，可以判断表中的哪些记录是符合条件的。比较结果为真，则返回 1，为假则返回 0，比较结果不确定则返回 NULL。


    |运算符	                描述	        实例
     =	                等于	        select * from t_user where user_id = 1 查询用户ID为1的信息
     !=	                不等于	        select * from t_user where user_id != 1 查询用户ID不为1的信息
     >	                大于	        select * from t_user where user_id > 1 查询用户ID大于1的信息
     >=	                大于等于	        select * from t_user where user_id >= 1 查询用户ID大于等于1的信息
     <	                小于	        select * from t_user where user_id < 1 查询用户ID小于1的信息
     <=	                大于等于	        select * from t_user where user_id <= 1 查询用户ID小于等于1的信息
     BETWEEN AND	        在两值之间	select * from t_user where user_id between 1 and 100 查询用户ID在1和100之间的信息，类似user_id >=1 and user_id <=100
     NOT  BETWEEN AND	不在两值之间	select * from t_user where user_id not between 1 and 100 查询用户ID不在1和100之间的信息，类似user_id <1 and user_id >100
     IN	                在集合中	        select * from t_user where user_id in ('1','2') 查询用户ID为 1 或者 2 的信息
     NOT IN	                不在集合中	select * from t_user where user_id not in ('1','2') 查询用户ID不为 1 和 2 的信息
     LIKE	                模糊匹配,%表示0个或者多个匹配	select * from t_user where user_name like '%张%' 查询用户姓名包含张的信息
     IS NULL	        为空	        select * from t_user where user_name is null 查询用户姓名为空的信息
     IS NOT NULL	        不为空	        select * from t_user where user_name not is null 查询用户姓名不为空的信息
     
说明：mysql中，IN 语句中参数个数是不限制的。不过对整段 sql 语句的长度有了限制，最大不超过 4M！ 

###5.3、逻辑运算符
逻辑运算符用来判断表达式的真假。如果表达式是真，结果返回 1。如果表达式是假，结果返回 0。
    
    运算符	    描述	实例
    NOT 或 !    逻辑非	select not 1; 结果为0
    AND	    逻辑与	select 2 and 0; 结果为0
    OR	    逻辑或	select 2 or 0; 结果为1
    XOR	    逻辑异或	select null or 1; 结果为1
###5.4、位运算符
位运算符是在二进制数上进行计算的运算符。位运算会先将操作数变成二进制数，进行位运算。然后再将计算结果从二进制数变回十进制数。

    运算符	描述	       实例
    &	按位与      	select 3&5; 结果为1
    I	按位或      	select 3I5; 结果为7
    ^	按位异或    	select 3I5; 结果为7
    ^	按位异或    	select 3^5; 结果为6
    ~	按位取反    	select ~18446744073709551612; 结果为3
    >>	按位右移    	select 3>>1; 结果为1
    <<	按位左移    	select 3<<1; 结果为6
    
###5.5、运算符优先级

    优先级(从高到底)	运算符
    1	!
    2	-（负号）,~（按位取反）
    3	^（按位异或）
    4	*,/(DIV),%(MOD)
    5	+,-
    6	>>,<<
    7	&
    8	I
    9	=(比较运算),<=>,<,<=,>,>=,!=,<>,IN,IS NULL,LIKE,REGEXP
    10	BETWEEN AND,CASE,WHEN,THEN,ELSE
    11	NOT
    12	&&,AND
    13	XOR
    14	II,OR
    15	=(赋值运算),:=
    
说明：在无法确定优先级的情况下，可以使用圆括号()来改变优先级，并且这样会使计算过程更加清晰。
##六、视图
视图（view）是一种虚拟存在的表，是一个逻辑表，本身并不包含数据。作为一个select语句保存在数据字典中的。
###6.1、创建视图
    CREATE [OR REPLACE] [ALGORITHM = {UNDEFINED | MERGE | TEMPTABLE}]
        VIEW view_name [(column_list)]
        AS select_statement
       [WITH [CASCADED | LOCAL] CHECK OPTION]
       
   参数说明：
       
       OR REPLACE：表示替换已有视图
       ALGORITHM：表示视图选择算法，默认算法是UNDEFINED(未定义的)：MySQL自动选择要使用的算法 ；merge合并；temptable临时表
       select_statement：表示select语句
       [WITH [CASCADED | LOCAL] CHECK OPTION]：表示视图在更新时保证在视图的权限范围之内
       cascade：是默认值，表示更新视图的时候，要满足视图和表的相关条件
       local：表示更新视图的时候，要满足该视图定义的一个条件即可
       
   基本格式：
    
    create view <视图名称>[(column_list)]
           as select语句
           with check option;
           
   创建视图示例：
   
    create view v_user(用户名,年龄)
    as
    select user_name,age from t_user
    with check option;
       
###6.2、查看视图
使用show create view语句查看视图信息
    
    show create view v_user;
视图一旦创建完毕，就可以像一个普通表那样使用，视图主要用来查询
    
    select * from v_user;
    
###6.3、删除视图
删除视图是指删除数据库中已存在的视图，删除视图时，只能删除视图的定义，不会删除数据，也就是说不动基表：
    
    DROP VIEW [IF EXISTS]   
    view_name [, view_name] ...
删除示例：

    drop view IF EXISTS v_user;
    
##七、函数
###7.1、常用函数列表

    函数      	            描述	                                        实例
    char_length(s)	            返回字符串 s 的字符长度	                        select char_length("hello") as content;
    concat(s1,s2...sn)	    字符串 s1,s2 等多个字符串合并为一个字符串	        select concat("hello ", "world") as content;
    format(x,n)	            将数字 x 进行格式化，到小数点后 n 位，最后一位四舍五入	select format(500.5634, 2) as content;
    lower(s)	            将所有字母变成小写字母	                        select lower('HELLO');
    current_timestamp()	    返回当前日期和时间	                                select current_timestamp();
    DATE_FORMAT(date,format)    格式化时间或者日期                               	select DATE_FORMAT(current_timestamp(),"%Y-%m-%d %H:%i:%s");
    IFNULL(v1,v2)	            如果 v1 的值不为 NULL，则返回 v1，否则返回 v2     	select IFNULL(null,'hello word');
    
###7.2、自定义函数语法介绍
####7.2.1、创建函数
    CREATE FUNCTION fn_name(func_parameter[,...])
    RETURNS type
    [characteristic...]
    routine_body
    
参数说明：
    
    fn_name：自定义函数名称
    func_parameter:  param_name type
    type: 任何mysql支持的类型
    characteristic: LANGUAGE SQL
    routine_body: 函数体
####7.2.2、编辑函数
    ALTER FUNCTION fn_name [characteristic...]
参数说明：
    
    fn_name：自定义函数名称
    func_parameter:  param_name type
    characteristic: LANGUAGE SQL
    
####7.2.3、删除函数
    DROP FUNCTION  [IF EXISTS]  fn_name;
参数说明：
    
    fn_name：自定义函数名称
    func_parameter:  param_name type
    
####7.2.4、查看函数语法
    SHOW FUNCTION STATUS [LIKE 'pattern']
参数说明：
    
    pattern：函数名称
    
示例：

    SHOW FUNCTION STATUS LIKE 'user_function';

####7.2.5、查看函数的定义语法

    SHOW CREATE FUNCTION fn_name;
参数说明：

    fn_name：自定义函数名称
###7.3、实例操作介绍
####7.3.1、创建一个表
    CREATE TABLE `t_user` (
      `user_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户id,作为主键',
      `user_name` varchar(5) DEFAULT NULL COMMENT '用户名',
      `age` int(3) DEFAULT NULL COMMENT '年龄',
      PRIMARY KEY (`user_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
####7.3.2、插入数据
    INSERT INTO t_user (user_name, age)
    VALUES('张三',24),('李四',25),('王五',26),('赵六',27);
####7.3.3、创建函数
    
    -- 创建一个函数
    DELIMITER $$
    
    -- 开始创建函数
    CREATE FUNCTION user_function(v_id INT)
    RETURNS VARCHAR(50)
    READS SQL DATA
    DETERMINISTIC
    BEGIN
      -- 定义变量
      DECLARE userName VARCHAR(50);
      -- 给定义的变量赋值
      SELECT user_name INTO userName FROM t_user
      WHERE user_id = v_id;
      -- 返回函数处理结果
      RETURN userName;
    END;
    
    -- 函数创建定界符
    DELIMITER ;  
####7.3.4、调用函数
    
    //查询用户ID为1的信息
    SELECT user_function(1);
###7.3.5、删除函数
    DROP FUNCTION  IF EXISTS  user_function;
    
##八、存储过程
###8.1、创建语法
    CREATE PROCEDURE 存储过程名([[IN |OUT |INOUT ] 参数名 数据类形...])
过程与创建函数类似，其中的声明语句结束符，可以自定义:

    DELIMITER $$
    或
    DELIMITER //
参数说明：

    IN 输入参数：表示该参数的值必须在调用存储过程时指定，在存储过程中修改该参数的值不能被返回，为默认值
    OUT 输出参数：该值可在存储过程内部被改变，并可返回
    INOUT 输入输出参数：调用时指定，并且可被改变和返回
创建一个查询用户信息的存储过程示例：

    DELIMITER $$
    CREATE PROCEDURE user_procedure(IN v_id int,OUT userName varchar(255))  
        BEGIN
        SELECT user_name as userName FROM t_user WHERE user_id = v_id;
        END $$ 
    DELIMITER ;
###8.2、存储过程调用
    -- @out为输出参数
    CALL user_procedure(1, @out);
输出结果：

    张三
###8.3、存储过程删除
    DROP PROCEDURE [IF EXISTS]  proc_name;
删除示例：

    DROP PROCEDURE IF EXISTS  user_procedure;
###8.4、存储过程和函数的区别
    函数只能通过return语句返回单个值或者表对象。而存储过程不允许执行return，但是通过out参数返回多个值。
    函数是可以嵌入在sql中使用的,可以在select中调用，而存储过程不行。
    函数限制比较多，比如不能用临时表，只能用表变量，还有一些函数都不可用等等，而存储过程的限制相对就比较少
    一般来说，存储过程实现的功能要复杂一点，而函数的实现的功能针对性比较强。
    当存储过程和函数被执行的时候，SQL Manager会到procedure cache中去取相应的查询语句，如果在procedure cache里没有相应的查询语句，SQL Manager就会对存储过程和函数进行编译。
##九、触发器
触发器是与表有关的数据库对象，在满足定义条件时触发，并执行触发器中定义的语句集合。

###9.1、创建触发器
定义语法

    CREATE
        [DEFINER = { user | CURRENT_USER }]
    TRIGGER trigger_name
    trigger_time trigger_event
    ON tbl_name FOR EACH ROW
      [trigger_order]
    trigger_body
    trigger_time: { BEFORE | AFTER }
    trigger_event: { INSERT | UPDATE | DELETE }
    trigger_order: { FOLLOWS | PRECEDES } other_trigger_name
参数说明：

    FOR EACH ROW：表示任何一条记录上的操作满足触发事件都会触发该触发器，也就是说触发器的触发频率是针对每一行数据触发一次。
    
    trigger_time：BEFORE和AFTER参数指定了触发执行的时间，在事件之前或是之后。
    
    tigger_event详解：
    
    INSERT 型触发器：插入某一行时激活触发器，可能通过INSERT、LOAD DATA、REPLACE 语句触发(LOAD DAT语句用于将一个文件装入到一个数据表中，相当与一系列的INSERT操作)；
    
    UPDATE型触发器：更改某一行时激活触发器，可能通过UPDATE语句触发；
    
    DELETE型触发器：删除某一行时激活触发器，可能通过DELETE、REPLACE语句触发。
    
    trigger_order：是MySQL5.7之后的一个功能，用于定义多个触发器，使用follows(尾随)或precedes(在…之先)来选择触发器执行的先后顺序。

示例，创建了一个名为trig1的触发器，一旦在t_user表中有插入动作，就会自动往t_time表里插入当前时间。

    CREATE TRIGGER trig1 AFTER INSERT
    ON t_user FOR EACH ROW
    INSERT INTO t_time VALUES(NOW());
创建有多个执行语句的触发器语法

    CREATE TRIGGER 触发器名 BEFORE|AFTER 触发事件
    ON 表名 FOR EACH ROW
    BEGIN
            执行语句列表
    END;
示例如下：

    DELIMITER //
    CREATE TRIGGER trig2 AFTER INSERT
    ON t_user FOR EACH ROW
    BEGIN
    INSERT INTO t_time VALUES(NOW());
    INSERT INTO t_time VALUES(NOW());
    END//
    DELIMITER ;
一旦插入成功，就会执行BEGIN ...END语句！

###9.2、查询触发器
查询所有触发器

    SHOW TRIGGERS;
查询指定的触发器

    select * from information_schema.triggers where trigger_name='trig1';
所有触发器信息都存储在information_schema数据库下的triggers表中，可以使用SELECT语句查询，如果触发器信息过多，最好通过TRIGGER_NAME字段指定查询。

###9.3、删除触发器
    DROP TRIGGER [IF EXISTS] [schema_name.]trigger_name
示例如下：

    DROP TRIGGER IF EXISTS trig1
删除触发器之后最好使用上面的方法查看一遍。

###9.4、总结
触发器尽量少的使用，因为不管如何，它还是很消耗资源，如果使用的话要谨慎的使用，确定它是非常高效的：触发器是针对每一行的；对增删改非常频繁的表上切记不要使用触发器，因为它会非常消耗资源。

##10、序列
在 MySQL 中，可以有如下几种途径实现唯一值：

    自增序列
    程序自定义
    UUID() 函数
    UUID_SHORT() 函数
###10.1、自增序列
在mysql中，一般我们可以给某个主键字段设置为自增模式，例如：

    #创建一个表test_db，字段内容为id,name
    create table test_db(id int,name char(10));
    
    # 设置id主键
    alter table test_db add primary key(id);
    
    # 将id主键设置为自增长模式
    alter table test_db modify id int auto_increment;
这种模式，在单库单表的时候，没啥问题，但是如果要对test_db表进行分库分表，这个时候问题就来了，如果水平分库，这个时候向test_db_1、test_db_2中插入数据，就会出现相同的ID！

###10.2、程序自定义
当然，为了避免出现这种情况，有的大神就自己单独创建了一张自增序列表，单独维护，这样就不会出现在分表的时候出现相同的ID！

实现过程也很简单！

1、创建一个序列表

    CREATE TABLE `sequence` (
      `name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '序列的名字',
      `current_value` int(11) NOT NULL COMMENT '序列的当前值',
      `increment` int(11) NOT NULL DEFAULT '1' COMMENT '序列的自增值',
      PRIMARY KEY (`name`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
2、创建–取当前值的函数

    BEGIN 
         DECLARE value INTEGER; 
         SET value = 0; 
         SELECT current_value INTO value 
              FROM sequence 
              WHERE name = seq_name; 
         RETURN value; 
    END
3、创建–取下一个值的函数

    DROP FUNCTION IF EXISTS nextval; 
    DELIMITER $ 
    CREATE FUNCTION nextval (seq_name VARCHAR(50)) 
         RETURNS INTEGER 
         LANGUAGE SQL 
         DETERMINISTIC 
         CONTAINS SQL 
         SQL SECURITY DEFINER 
         COMMENT '' 
    BEGIN 
         UPDATE sequence 
              SET current_value = current_value + increment 
              WHERE name = seq_name; 
         RETURN currval(seq_name); 
    END 
    $ 
    DELIMITER ;
4、创建–更新当前值的函数

    DROP FUNCTION IF EXISTS setval; 
    DELIMITER $ 
    CREATE FUNCTION setval (seq_name VARCHAR(50), value INTEGER) 
         RETURNS INTEGER 
         LANGUAGE SQL 
         DETERMINISTIC 
         CONTAINS SQL 
         SQL SECURITY DEFINER 
         COMMENT '' 
    BEGIN 
         UPDATE sequence 
              SET current_value = value 
              WHERE name = seq_name; 
         RETURN currval(seq_name); 
    END 
    $ 
    DELIMITER ;
最后，直接通过函数调用，测试如下

    # 添加一个sequence名称和初始值，以及自增幅度
    INSERT INTO sequence VALUES ('testSeq', 0, 1);
    
    #设置指定sequence的初始值
    SELECT SETVAL('testSeq', 10);
    
    #查询指定sequence的当前值
    SELECT CURRVAL('testSeq');
    
    #查询指定sequence的下一个值
    SELECT NEXTVAL('testSeq');
这方案，某种情况下解决了分表的问题，但是如果分库还是会出现相同的ID！

###10.3、UUID() 函数
UUID 基于 16 进制，由 32 位小写的 16 进制数字组成，如下：

    aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee
比如d0c754a8-178e-11eb-ae3d-2a7bea22ed3d就是一个典型的 UUID。

在 MySQL 的UUID()函数中，前三组数字从时间戳中生成，第四组数字暂时保持时间戳的唯一性，第五组数字是一个IEEE 802节点标点值，保证空间唯一。

使用 UUID() 函数，可以生成时间、空间上都独一无二的值。据说只要是使用了 UUID，都不可能看到两个重复的 UUID 值。当然，这个只是在理论情况下。

使用方法也很简单，在sql可以直接当成函数调用即可！

    select uuid();
###10.4、UUID_SHORT() 函数
在 MySQL 5.1 之后的版本，提供UUID_SHORT()函数，生成一个64位无符号整数，在java中可以用Long类型接受。另外，需要注意的是，server_id 的范围必须为0-255，并且不支持 STATEMENT模式复制，否则有可能会产生重复的ID

    select UUID_SHORT();
同时，需要注意的是，UUID_SHORT()返回的是unsigned long long类型，在字段类型设置的时候，一定要勾选无符号类型，否则有可能生成的ID超过Long类型最大长度！

##11、用户权限
###11.1、用户管理
查询所有用户

    select * from mysql.user;
创建用户

    # 格式
    CREATE USER 'username'@'host' IDENTIFIED BY 'password';
    
    # 例子，创建一个用户名为admin,密码123456，可以本地访问的用户
    CREATE USER 'admin'@'localhost' IDENTIFIED BY '123456';
更改用户密码

    # 格式
    SET PASSWORD FOR 'username'@'host' = PASSWORD('newpassword');
    
    # 例子，将用户名admin,密码修改为456789，可以本地访问的用户
    SET PASSWORD FOR 'admin'@'localhost' = PASSWORD("456789");
删除用户

    # 格式
    DROP USER 'username'@'host';
    
    # 例子，删除用户名为admin的用户
    DROP USER 'admin'@'localhost';
最后刷新操作，使操作生效
刷新操作使其生效

    flush privileges
###11.2、用户权限管理
查询用户权限

    # 格式
    SHOW GRANTS FOR 'username'@'host'
    
    # 查询用户名为 'root'@'%'的权限信息
    SHOW GRANTS FOR 'root'@'%'
给用户授予某种权限

    # 格式
    GRANT privileges ON databasename.tablename TO 'username'@'host'
说明：

    privileges：用户的操作权限，如SELECT，INSERT，UPDATE、DELETE等，如果要授予所的权限则使用ALL
    databasename：数据库名
    tablename：表名，如果要授予该用户对所有数据库和表的相应操作权限则可用*表示，如*.*
    username：用户名
    host：可以访问的域名
在给其他授权前，请先用管理员账户登录！

####1、设置用户访问数据库权限
设置用户testuser，只能访问数据库test_db，其他数据库均不能访问

    grant all privileges on test_db.* to 'testuser'@'localhost';
设置用户testuser，可以访问mysql上的所有数据库

    grant all privileges on *.* to 'testuser'@'localhost';
设置用户testuser，只能访问数据库testuser的表user_info，数据库中的其他表均不能访问

    grant all privileges on test_db.user_info to 'testuser'@'localhost';
####2、设置用户操作权限
设置用户testuser，拥有所有的操作权限，也就是管理员

    grant all privileges on *.* to 'testuser'@'localhost';
设置用户testuser，只拥有【查询】操作权限
    
    grant select on *.* to 'testuser'@'localhost';
设置用户testuser，只拥有【查询/插入/修改/删除】操作权限

    grant select,insert,update,delete on *.* to 'testuser'@'localhost';
####3、设置用户远程访问权限
设置用户testuser，只能在客户端IP为192.168.1.100上才能远程访问mysql
    
    grant all privileges on *.* to 'testuser'@'192.168.1.100';
设置所有用户可以远程访问mysql，修改my.cnf配置文件，将bind-address = 127.0.0.1前面加#注释掉

    # bind-address = 127.0.0.1
####注意：用以上命令授权的用户不能给其它用户授权，如果想让该用户可以授权，用以下命令！

    GRANT privileges ON databasename.tablename TO 'username'@'host' WITH GRANT OPTION;
在结尾加上WITH GRANT OPTION就可以了！

###11.3、关于root用户的访问设置
可以使用如下命令，来一键设置root用户的密码，同时拥有所有的权限并设置为远程访问！

    grant all privileges on *.* to 'root'@'%'  identified by '123456';
如果想关闭root用户远程访问权限，使用如下命令即可！

    grant all privileges on *.* to 'root'@'localhost'  identified by '123456';
最后使用如下命令，使其生效！

    flush privileges;
创建用户并进行授权，也可以使用如下快捷命令！

    例如，创建一个admin用户，密码为admin
    grant all privileges on *.* to 'admin'@'%' identified by 'admin';

    刷新MySQL的系统权限相关表方可生效
    flush privileges;
####最后需要注意的是：mysql8，使用强校验，所以，如果密码过于简单，会报错，密码尽量搞复杂些！

##十二、总结
本文主要围绕 Mysql 中常用的语法进行一次梳理和介绍，这些语法大部分也同样适用于其他的数据库，例如 oracle、sqlserver、postgres 等等，在数据操作栏，除了分页函数以外，基本都是通用的！