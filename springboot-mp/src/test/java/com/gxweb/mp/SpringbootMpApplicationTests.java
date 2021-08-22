package com.gxweb.mp;

import com.gxweb.mp.mappers.UserMapper;
import com.gxweb.mp.po.User;
import com.gxweb.mp.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMpApplicationTests {

    @Autowired
    public UserMapper mapper;
    @Autowired
    public UserService userService;

    @Test
    public void testSelect() {
        List<User> list = mapper.selectList(null);
        assertEquals(5, list.size());
        list.forEach(System.out::println);
    }

    @Test
    public void testCustomRawSql() {
        List<User> users = mapper.selectRaw();
        users.forEach(System.out::println);
    }

}
