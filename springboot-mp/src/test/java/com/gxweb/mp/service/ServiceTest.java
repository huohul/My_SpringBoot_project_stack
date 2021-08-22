package com.gxweb.mp.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gxweb.mp.SpringbootMpApplicationTests;
import com.gxweb.mp.po.User;
import org.junit.Test;

import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2021/6/6 21:25
 * @description ：
 * @version: 1.0
 */

public class ServiceTest extends SpringbootMpApplicationTests {

    /**
     * SELECT id,name,age,email,manager_id,create_time FROM user WHERE (age > ?)
     */
    @Test
    public void testGetOne() {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        wrapper.gt(User::getAge, 28);
        User one = userService.getOne(wrapper, false);
        System.out.println(one);
    }

    /**
     * IService也支持链式调用
     * SELECT id,name,age,email,manager_id,create_time FROM user WHERE (age > ? AND name LIKE ?)
     */
    @Test
    public void testChain() {
        List<User> list = userService.lambdaQuery()
                .gt(User::getAge, 39)
                .likeRight(User::getName, "王")
                .list();
        list.forEach(System.out::println);
    }

    //更新示例如 UPDATE user SET email=? WHERE (age > ? AND name LIKE ?)
    @Test
    public void testUpdate() {
        userService.lambdaUpdate()
                .gt(User::getAge, 39)
                .likeRight(User::getName, "王")
                .set(User::getEmail, "w39@baomidou.com")
                .update();
    }

    //删除示例如下 DELETE FROM user WHERE (name LIKE ?)
    @Test
    public void testDeleted() {
        userService.lambdaUpdate()
                .like(User::getName, "青蛙")
                .remove();
    }
    /**
     * 条件构造器
     * mp 让我觉得极其方便的一点在于其提供了强大的条件构造器Wrapper，可以非常方便的构造 WHERE 条件。条件构造器主要涉及到 3 个类，AbstractWrapper。QueryWrapper，UpdateWrapper，它们的类关系如下
     * 在AbstractWrapper中提供了非常多的方法用于构建 WHERE 条件，而QueryWrapper针对SELECT语句，提供了select()方法，可自定义需要查询的列，而UpdateWrapper针对UPDATE语句，提供了set()方法，用于构造set语句。条件构造器也支持 lambda 表达式，写起来非常舒爽。
     * 下面对AbstractWrapper中用于构建 SQL 语句中的 WHERE 条件的方法进行部分列举
     * eq：equals，等于
     * allEq：all equals，全等于
     * ne：not equals，不等于
     * gt：greater than ，大于 >
     * ge：greater than or equals，大于等于≥
     * lt：less than，小于<
     * le：less than or equals，小于等于≤
     * between：相当于 SQL 中的 BETWEEN
     * notBetween
     * like：模糊匹配。like("name","黄")，相当于 SQL 的name like '%黄%'
     * likeRight：模糊匹配右半边。likeRight("name","黄")，相当于 SQL 的name like '黄%'
     * likeLeft：模糊匹配左半边。likeLeft("name","黄")，相当于 SQL 的name like '%黄'
     * notLike：notLike("name","黄")，相当于 SQL 的name not like '%黄%'
     * isNull
     * isNotNull
     * in
     * and：SQL 连接符 AND
     * or：SQL 连接符 OR
     * apply：用于拼接 SQL，该方法可用于数据库函数，并可以动态传参
     * .......
     * 使用示例
     * 下面通过一些具体的案例来练习条件构造器的使用。（使用前文创建的user表）
     */
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//    wrapper.like("name", "佳").lt("age", 25);
//        List<User> users = userMapper.selectList(wrapper);
//
//    wrapper.likeRight("name","黄").between("age", 20, 40).isNotNull("email");
//
//    wrapper.likeRight("name","黄").or().ge("age",40).orderByDesc("age").orderByAsc("id");
//
//    wrapper.apply("date_format(create_time, '%Y-%m-%d') = {0}", "2021-03-22")
//            .inSql("manager_id", "SELECT id FROM user WHERE name like '李%'");
//
//    wrapper.apply("date_format(create_time, '%Y-%m-%d') = '2021-03-22'");
//
//    wrapper.likeRight("name", "王").and(q -> q.lt("age", 40).or().isNotNull("email"));
//
//    wrapper.likeRight("name", "王").or(
//                q -> q.lt("age",40)
//                .gt("age",20)
//                            .isNotNull("email")
//            );
//
//    wrapper.nested(q -> q.lt("age", 40).or().isNotNull("email"))
//                .likeRight("name", "王");
//
//    wrapper.in("age", Arrays.asList(30,31,34,35));
//
//    wrapper.inSql("age","30,31,34,35");
//
//    wrapper.in("age", Arrays.asList(30,31,34,35)).last("LIMIT 1");
//
//    wrapper.select("id", "name");
//
//    wrapper.select(User.class, info -> {
//            String columnName = info.getColumn();
//            return !"create_time".equals(columnName) && !"manager_id".equals(columnName);
//        });

}
