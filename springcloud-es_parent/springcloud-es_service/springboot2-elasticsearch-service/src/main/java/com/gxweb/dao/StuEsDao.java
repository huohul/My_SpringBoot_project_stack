package com.gxweb.dao;

import com.gxweb.pojo.StudentEsDB;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author pancm
 * @Title: UserDao
 * @Description: spring-data-es 查询接口
 * @Version:1.0.0
 * @date 2018年4月25日
 */
public interface StuEsDao extends ElasticsearchRepository<StudentEsDB, Long> {

    /**
     * 自定义接口，可以进行定义查询
     *
     * @param name
     * @param pageable
     * @return
     */
    Page<StudentEsDB> findUserByName(String name, Pageable pageable);

}
