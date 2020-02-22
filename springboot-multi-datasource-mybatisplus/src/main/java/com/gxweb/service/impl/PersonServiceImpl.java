package com.gxweb.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gxweb.entity.Person;
import com.gxweb.mapper.PersonMapper;
import com.gxweb.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/21 22:44
 * @description ： 数据服务层 实现
 * @version: 1.0
 */
@Service
@DS("slave")  // 类上 {@code @DS("slave")} 代表默认从库
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

    /**
     * 类上 {@code @DS("slave")} 代表默认从库，在方法上写 {@code @DS("master")} 代表默认主库
     *
     * @param person 用户
     */
    @DS("master")
    @Override
    public void addMasUser(Person person) {
        baseMapper.insert(person);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @DS("master")
    @Override
    public Person fidByMasId(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 主库查询所有
     * @return
     */
    @DS("master")
    @Override
    public List<Person> findMasAll() {
        return baseMapper.selectList(null);
    }
//
//    @DS("master")
//    @Override
//    public Person findMasById(Integer id) {
//        return baseMapper.selectById(id);
//    }
//
//    @DS("master")
//    @Override
//    public List<Person> searchMas(Map map, Pageable pageable) {
//        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
//        for (Object key : map.keySet()) {
//            queryWrapper.eq(null != map.get(key), (String) key, map.get(key));
//        }
//        List<Person> list = baseMapper.selectMapsPage(pageable, queryWrapper);
//        return list;
//    }


}
