package com.gxweb.controller;

import com.gxweb.entity.Person;
import com.gxweb.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/16 17:18
 * @description ：
 * @version: 1.0
 */
@Controller
public class PersonConerller {

    @Autowired
    private PersonService personService;

    @RequestMapping("/hello")
    public String hello(Model model, String name) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        System.out.println("查询所有");
        List<Person> personList = personService.findAll();
        model.addAttribute("persons", personList);
        return "person/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "person/personAdd";
    }

    @RequestMapping("/add")
    public String add(Person person) {
        personService.addPerson(person);
        System.out.println("新增成功");
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, long id) {
        Person person = personService.findPersonById(id);
        model.addAttribute("person", person);
        return "person/personEdit";
    }

    @RequestMapping("/edit")
    public String edit(Person person) {
        personService.updatePerson(person);
        return "redirect:/list";
    }

    @RequestMapping("/toDelete")
    public String delete(long id) {
        personService.deletePerson(id);
        return "redirect:/list";
    }
}

