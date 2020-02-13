package com.gxweb.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.gxweb.entity.Result;
import com.gxweb.feign.StuFeign;
import com.gxweb.pojo.Student;
import com.gxweb.service.EsManageService;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

/**
 * @author ： CYQ
 * @date ：Created in 2019/07/14 20:22
 * @description ：
 * @version: 1.0
 */
@Service
public class EsManageServiceImpl implements EsManageService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;   //es注入数据

    @Autowired
    private StuFeign stuFeign;  //注入库存Feign  远程调用  查询接口

    @Autowired(required = false)
    ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 查询学生数据
     * @para
     */
    @Override
    public void importDataToESBySpuId(Integer id) {
        Map serchMap = new HashMap<>();
        //设置查询条件根据学生id查询
        serchMap.put("id", id);  //这里如果为空 就查询审核通过的所有所欲
        Result list = stuFeign.findList(serchMap);

        if (list.isFlag()) {  //是否查询成功
            //获取数据 进行类型转换
            List<Student> skuList = (List<Student>) list.getData();
            //将获取到的数据转为json格式字符串
            String skulistJsonStr = JSON.toJSONString(skuList);
            //将jsong格式字符串转换成需要的数据
            List<Map> maps = JSON.parseArray(skulistJsonStr, Map.class);
            // 调用方法 导入数据到ES索引库
            importSkuListToES(maps);
        }
    }


    /**
     * 学生数据据导入es
     * @param skuList
     */
    @Override
    public void importSkuListToES(List<Map> skuList) {
        //创建桶的请求对象
        BulkRequest bulkRequest = new BulkRequest();
        if (skuList != null) {
            //遍历导入数据
            for (Map stumap : skuList) {
                //创建索引请求对象,三个参数 风别是es中的_index , _type  , _id                                        //第三个参数最好为 id userId 不行
                IndexRequest indexRequest = new IndexRequest("stuindex", "doc", String.valueOf(stumap.get("id")));
                //将库存数据加入到索引请求对象中
                indexRequest.source(stumap);
                //请求对象加入到桶中
                bulkRequest.add(indexRequest);
            }
        }

        //导入数据到es索引中  参数一桶对象
        restHighLevelClient.bulkAsync(bulkRequest, RequestOptions.DEFAULT, new ActionListener<BulkResponse>() {
            @Override
            public void onResponse(BulkResponse bulkItemResponses) {
                System.out.println("=======导入数据到ES 索引库成功=========" + bulkItemResponses.status());
            }

            @Override
            public void onFailure(Exception e) {
                System.out.println("===============导入数据到ES 索引库 失败=============");
            }
        });
    }


    /**
     * 根据关键字进行全文搜索
     * @return
     */
    @Override
    public List<Student> search(String searchContent) {
        return null;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Object findById(Integer id) {
        GetQuery getQuery = new GetQuery();
        getQuery.setId(id.toString());
        return elasticsearchTemplate.queryForObject(getQuery, Student.class);
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public Object findAll() {
        CriteriaQuery criteriaQuery = new CriteriaQuery(new Criteria());
        List<Student> students = elasticsearchTemplate.queryForList(criteriaQuery, Student.class);
        System.out.println(students);
        return students;
    }


}

