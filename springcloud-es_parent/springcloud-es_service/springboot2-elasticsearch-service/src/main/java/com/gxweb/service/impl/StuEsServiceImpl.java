package com.gxweb.service.impl;

import com.gxweb.pojo.StudentEsDB;
import com.gxweb.dao.StuEsDao;
import com.gxweb.service.StuEsService;
import com.google.common.collect.Lists;;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/11 22:10
 * @description ：
 * @version: 1.0
 */
@Service
public class StuEsServiceImpl implements StuEsService {

    @Autowired
    private StuEsDao stuEsDao;

    //增加用户
    @Override
    public boolean insert(StudentEsDB user) {
        boolean falg = false;
        try {
            stuEsDao.save(user);
            falg = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return falg;
    }

    //根据关键字进行全文搜索
    @Override
    public List<StudentEsDB> search(String searchContent) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
        System.out.println("查询的语句:" + builder);
        Iterable<StudentEsDB> searchResult = stuEsDao.search(builder);
        //等价于下面的这个方法
        List<StudentEsDB> list = Lists.newArrayList(searchResult);
//          Iterator<User> iterator = searchResult.iterator();
//          while (iterator.hasNext()) {
//       	   	list.add(iterator.next());
//          }
        return list;
    }

    /**
     * 根据名字进行分页查询
     */
    @Override
    public List<StudentEsDB> searchByName(Integer pageNumber, Integer pageSize, String name) {

        Page<StudentEsDB> searchPageResults = stuEsDao.findUserByName(name, PageRequest.of(pageNumber, pageSize));
        return searchPageResults.getContent();
    }

    //根据关键字进行搜索并分页
    @Override
    public List<StudentEsDB> searchUser(Integer pageNumber, Integer pageSize, String searchContent) {
        // 分页参数
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(builder).build();
        System.out.println("查询的语句:" + searchQuery.getQuery().toString());
        Page<StudentEsDB> searchPageResults = stuEsDao.search(searchQuery);
        return searchPageResults.getContent();
    }


    //根据关键词权重进行查询  指定为name 属性
    @Override
    public List<StudentEsDB> searchUserByWeight(String searchContent) {
        // 根据权重进行查询
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(QueryBuilders.matchQuery("name", searchContent));
        System.out.println("查询的语句:" + functionScoreQueryBuilder.toString());
        Iterable<StudentEsDB> searchResult = stuEsDao.search(functionScoreQueryBuilder);
        List<StudentEsDB> list = Lists.newArrayList(searchResult);
        return list;
    }

}
