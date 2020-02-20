package com.gxweb.service.impl;

import com.gxweb.dao.PersonRepository;
import com.gxweb.entity.Person;
import com.gxweb.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/16 17:08
 * @description ：
 * @version: 1.0
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public boolean addPerson(Person person) {
        boolean flag = false;
        try {
            personRepository.save(person);
            flag = true;
        } catch (Exception e) {
            System.out.println("新增失败!");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean updatePerson(Person person) {
        boolean flag = false;
        try {
            personRepository.save(person);
            flag = true;
        } catch (Exception e) {
            System.out.println("修改失败!");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean deletePerson(Long id) {
        boolean flag = false;
        try {
            personRepository.deleteById(id);
            flag = true;
        } catch (Exception e) {
            System.out.println("删除失败!");
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public Person findPersonById(Long id) {
        return personRepository.findById(id).get();
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
