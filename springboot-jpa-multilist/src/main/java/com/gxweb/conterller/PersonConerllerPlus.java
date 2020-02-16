package com.gxweb.conterller;

import com.gxweb.dao.PersonRepository;
import com.gxweb.entity.dto.UserDTO;
import com.gxweb.entity.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/16 18:51
 * @description ：自定义查询
 * @version: 1.0
 */
@RestController
@RequestMapping("/api")
public class PersonConerllerPlus {

    @Autowired
    private PersonRepository personRPlus;


    //根据姓名查询
    @GetMapping("/findName")
    public Person findByName(String name) {
        return personRPlus.findByName(name).get();
    }

    //按年龄找到大于
    @GetMapping("/findByAgeBmax")
    public List<Person> findByAgeGreaterThan(int age) {
        return personRPlus.findByAgeGreaterThan(age);
    }

    //按当前用户名查询所有字段
    @GetMapping("/findNameToallField")
    Optional<Person> findByNameCustomeQuery(String name) {
        return personRPlus.findByNameCustomeQuery(name);
    }

    //按当前用户名查询指定字段
    @GetMapping("/findSpecifiedField")
    public String findPersonNameById(Long id) {
        return personRPlus.findPersonNameById(id);
    }

    /**
     * 自定义更新
     *
     * @param name 要更新后的参数
     * @param id   更新那条
     * @return
     */
    @PutMapping("/Custompdate")
    public Person updatePersonNameById(String name, Long id) {
        personRPlus.updatePersonNameById(name, id);
        return personRPlus.findById(id).get();
    }


///////////////////////////////////////多表 链表查询//////////////////////////////////////////////

    // 根据当前人的参数 查询与之对应DTO的信息
    @GetMapping("/getUserInformation")
    public UserDTO getUserInformation(Long personId) {
        return personRPlus.getPersonInformation(personId).get();
    }

    //根据最小年龄与最大年龄为参数  查询在这中间的结果
    @GetMapping("/filterUserInfoByAge")
    List<UserDTO> filterUserInfoByAge(int small, int big) {
        return personRPlus.filterPersonInfoByAge(small, big);
    }

    //过滤信息 根据正确的参数 查询对应的所有信息 用DTO 返回 这里可变参 无效  不知道怎么传参数 只能接到一个
    @GetMapping("/Multi-parameter")
    public List<UserDTO> should_filter_user_info(String personName ,String... s) {
        List<String> list = null;
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
            list = Arrays.asList(personName,s[i]);
        }
        System.out.println(list);
        return personRPlus.filterPersonInfo(list);

    }

    /**
     * 按指定字段从大到小 分页排序 DESC
     * @param pageable
     * @param sort
     * @return
     */
    @GetMapping("/getUserInformationList")
    public Page<UserDTO> getUserInformationList(@PageableDefault Pageable pageable,String sort) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.DESC, sort);
       return personRPlus.getPersonInformationList(pageRequest);
    }

}
