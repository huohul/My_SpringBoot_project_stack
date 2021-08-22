package com.gxweb;

import com.gxweb.entity.User;
import com.gxweb.service.PersonService;
import com.gxweb.service.UserService;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootMultiDatasourceJpaApplicationTests {
    @Autowired
    private PersonService personService;

    @Autowired
    private UserService userService;

    /**
     * 主从库添加
     */
    @Test
    public void addUser() {
        User userMaster = User.builder().name("主库添加").age(20).build();
        userService.addUser(userMaster);

        User userSlave = User.builder().name("从库添加").age(20).build();
        userService.save(userSlave);
    }

    /**
     * 主从库添加
     */
  /*  @Test
    public void addPerson() {
        Person userMaster = Person.builder().age(18).company_id(1).name("主库添加").school_id(1).build();
        personService.addMasUser(userMaster);

        Person userSlave = Person.builder().age(20).company_id(2).name("从库添加").school_id(1).build();
        personService.save(userSlave);
    }*/


}
