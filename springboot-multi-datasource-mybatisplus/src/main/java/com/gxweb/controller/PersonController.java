package com.gxweb.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gxweb.entity.Person;
import com.gxweb.service.PersonService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/21 22:48
 * @description ：控制层
 * @version: 1.0
 */
@RestController
@RequestMapping
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     * 主从库添加
     */
    @GetMapping("/MSsave")
    public void addUser() {
        Person userMaster = Person.builder().age(20).companyId(1).name("主库添加controoler").schoolId(1).build();
        personService.addMasUser(userMaster);

        Person userSlave = Person.builder().age(22).companyId(1).name("从库添加controller").schoolId(2).build();
        personService.save(userSlave);
    }

    /**
     * 因为只建议从库查询  主库增删改  所以这里做一个从主复制
     */
    @GetMapping("/saltoMas")
    public String saltomas() {
        List<Person> personList = personService.list(new QueryWrapper<>());
        for (Person person : personList) {
            Person person1 = personService.fidByMasId(person.getId());
            //逻辑解析：如果拿着从库的id 去主库查询 查到了 并且与从库数据一样 就存在同样数据不执行复制 如果没有查到 就执行复制
            if (person1 != null && person.equals(person1)) {
                System.out.println("存在相同数据不执行复制,相同数据是:" + person);
            } else {
                personService.addMasUser(person);
            }
        }
        return "从主赋值成功";
    }


    /**
     * 即使不建议 也要来个主从复制
     */
    @GetMapping("/MastoSal")
    public String masToSal() {
        List<Person> personList = personService.findMasAll();
        for (Person person : personList) {
            Person person1 = personService.getById(person.getId());
            //逻辑解析：如果拿着从库的id 去主库查询 查到了 并且与从库数据一样 就存在同样数据不执行复制 如果没有查到 就执行复制
            if (person1 != null && person.equals(person1)) {
                System.out.println("存在相同数据不执行复制,相同数据是:" + person);
            } else {
                personService.save(person);
            }
        }
        return "主从复制成功";
    }

    /**
     *从库查询所有
     */
    @GetMapping("/getSalAll")
    public List<Person> getSalAll() {
        List<Person> personlist=personService.list();
        System.out.println(personlist);
        return personlist;
    }

}


