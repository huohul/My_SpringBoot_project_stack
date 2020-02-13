package com.gxweb.service;

import com.gxweb.pojo.Student;

import java.util.List;
import java.util.Map;

/**
 * @author ： CYQ
 * @date ：Created in 2019/07/14 20:21
 * @description ：商品数据导入ES 接口业务层
 * @version: 1.0
 */

public interface EsManageService {

    /**
     * 根据id, 导入对应的库存数据到ES索引库中  如果不传  添加所到ES
     *
     * @param id
     */
    void importDataToESBySpuId(Integer id);

    /**
     * 将集合数据导入到es的索引库中
     *
     * @param stuList
     */
    void importSkuListToES(List<Map> stuList);

    /**
     * 根据关键字进行全文搜索
     *
     * @param searchContent
     * @return
     */
    List<Student> search(String searchContent);

    /**
     *根据id查询
     */
    public Object findById(Integer id);

    /**
     * 查询所有
     */
    Object findAll();

}
