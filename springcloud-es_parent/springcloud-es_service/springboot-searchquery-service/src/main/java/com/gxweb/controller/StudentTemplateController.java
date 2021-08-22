package com.gxweb.controller;

import com.gxweb.dto.StudentDTO;
import com.gxweb.pojo.Student;
import com.gxweb.utils.BeanUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsExtractor;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * 复杂的操作使用 elasticsearchTemplate
 * 查询具体组合可以查看 # QueryBuilders 类
 *
 * @author majie
 * @date 2018/7/2.
 */
@RestController
@RequestMapping("/temp/product")
public class StudentTemplateController {

    private final ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    public StudentTemplateController(ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    /**
     * 查询所有 要传参数 模糊查询  默认限定10条数 比较少
     * http://localhost:9010/temp/product/?searchSourch=湖北
     * http://localhost:9010/temp/product/?searchSourch=湖北&size=400
     */
    @GetMapping
    public Object findAll(String searchSourch, @PageableDefault Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery(searchSourch)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, Student.class);
    }

    /**
     * 分页查询  有效
     */
    @GetMapping("/page")
    public Object findPage(@RequestParam(required = false, defaultValue = "1") Integer page,
                           @RequestParam(required = false, defaultValue = "10") Integer size) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withPageable(
                        PageRequest.of(page - 1, size, Sort.Direction.ASC, "id"))  //页码是从0 开始
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, Student.class);
    }

    /**
     * 单独的排序  有效 但数据不是 所有  可带size 不带默认十条
     * http://localhost:9010/temp/product/sort?size=123
     */
    @GetMapping("/sort")
    public Object findSort(@PageableDefault Pageable pageable) {
        FieldSortBuilder fieldSortBuilder = SortBuilders.fieldSort("id").order(SortOrder.ASC);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery()).withPageable(pageable)
                .withSort(fieldSortBuilder)
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, Student.class);
    }

    /**
     * 根据id查询   有效
     */
    @GetMapping("/{id}")
    public Object findById(@PathVariable String id) {
        GetQuery getQuery = new GetQuery();
        getQuery.setId(id);
        return elasticsearchTemplate.queryForObject(getQuery, Student.class);
    }

    /**
     * 根据名称查询
     * 模糊查询   有效
     *
     * @param name     参数
     * @param pageable size  可指定条数 不带查询所有
     * @return
     */
    @GetMapping("/find-by-name")
    public Object findByName(@RequestParam String name, @PageableDefault Pageable pageable) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("name", name)).build();
        return elasticsearchTemplate.queryForList(searchQuery, Student.class);
    }


    /**
     * 根据指定名称查询 有效
     */
    @GetMapping("/contain")
    public Object contain(@RequestParam String name) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(
                        matchQuery("name", name)
                                .operator(Operator.AND)    //默认使用的是or
                                .minimumShouldMatch("100%")        //匹配率
                )
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, Student.class);
    }

    /**
     * 指定查询的字段
     */
    @GetMapping("/source-filter")
    public Object sourceFilter() {

        //ES 库中对应 列名
        String[] include = {"id",
                "classAndGrade", "candidateNumber", "name",
                "sex", "nameOfMajor", "ethnic",
                "politicsStatus", "patch", "sourceProvinces",
                "areaCountyCityStates", "admissionToTheProvince", "theSource"};

        FetchSourceFilter fetchSourceFilter = new FetchSourceFilter(include, null);   //两个参数分别是要显示的和不显示的

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withSourceFilter(fetchSourceFilter)
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, Student.class);
    }

    /**
     * bool查询  精准查询 id = 3450
     */
    @GetMapping("/bool")
    public Object bool() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(
                        boolQuery()
                                .must(matchQuery("id", 3450))
                )
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, Student.class);
    }


    /**
     * filter查询
     * <p>
     * filed : name
     * filedName : 王
     * rangeName : sex
     * range : 男
     */
    @GetMapping("/filter")
    public Object filter(@RequestParam String filed,
                         @RequestParam String filedName,
                         @RequestParam(required = false, defaultValue = "price") String rangeName,
                         @RequestParam(required = false, defaultValue = "0") String range) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(
                        boolQuery()
                                .must(matchQuery(filed, filedName))
                                .filter(
                                        rangeQuery(rangeName)
                                                .gte(range)
                                )
                )
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, Student.class);
    }


    /**
     * phrase查询   指定字段  模糊查询
     * filed : sex
     * filedName : 女
     */
    @GetMapping("/phrase")
    public Object phrase(@RequestParam String filed, @RequestParam String filedName) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(
                        matchPhraseQuery(filed, filedName)
                )
                .build();
        return elasticsearchTemplate.queryForList(searchQuery, Student.class);
    }

    /**
     * highlight查询 高亮  自定义高亮字段值
     * filed : name
     * filedName :  刘
     */
    @GetMapping("/highlight")
    public Object highlight(@RequestParam String filed, @RequestParam String filedName) {

        //高亮显示的词必须是查询的词
        final List<HighlightBuilder.Field> fields = new HighlightBuilder().field(filed).fields();

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(
                        matchPhraseQuery(filed, filedName)
                )
                .withHighlightFields(fields.toArray(new HighlightBuilder.Field[0]))
                .build();

        //如果不想使用分页的写法
        return elasticsearchTemplate.query(searchQuery, (ResultsExtractor<Object>) response -> {
            List<StudentDTO> chunk = new ArrayList<>();
            for (SearchHit searchHit : response.getHits()) {
                //没有数据
                if (response.getHits().getHits().length <= 0) {
                    return null;
                }
                //hit转换成bean
                StudentDTO productDTO = BeanUtils.mapToBean(searchHit.getSourceAsMap(), new StudentDTO());
                productDTO.setHighlighted(searchHit.getHighlightFields().get("name").fragments()[0].toString());
                chunk.add(productDTO);
            }
            return chunk;
        });

//        return elasticsearchTemplate.queryForPage(searchQuery, Product.class, new SearchResultMapper() {
//            @Override
//            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
//                List<ProductDTO> chunk = new ArrayList<>();
//                for (SearchHit searchHit : response.getHits()) {
//                    if (response.getHits().getHits().length <= 0) {
//                        return null;
//                    }
//                    //hit转换成bean
//                    ProductDTO productDTO = BeanUtils.mapToBean(searchHit.getSourceAsMap(), new ProductDTO());
//                    productDTO.setHighlighted(searchHit.getHighlightFields().get("name").fragments()[0].toString());
//                    chunk.add(productDTO);
//                }
//                if (chunk.size() > 0) {
//                    return new AggregatedPageImpl<>((List<T>) chunk);
//                }
//                return null;
//            }
//        });
    }

    /**
     * 根据id删除
     */
    @DeleteMapping("/{id}")
    public Boolean deleteById(@PathVariable String id) {
        boolean flag = false;
        try {
            elasticsearchTemplate.delete(Student.class, id);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 根据指定字段删除
     * 不是完全匹配,类似模糊匹配
     */
    @DeleteMapping("/delete-by-name/{name}")
    public Boolean deleteByName(@PathVariable String name) {
        Boolean flag = false;
        try {
            DeleteQuery deleteQuery = new DeleteQuery();
            deleteQuery.setQuery(termQuery("name", name));    //类似模糊删除
            elasticsearchTemplate.delete(deleteQuery, Student.class);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    /**
     * 更新
     */
    @PutMapping
    public void update(@RequestBody Student student) {
        IndexRequest indexRequest = new IndexRequest();
        Map<String, Object> map = BeanUtils.beanToMap(student);
        indexRequest.source(map, XContentType.JSON);    //更新整个实体
//        indexRequest.source("name",product.getName());  //更新指定的字段
        UpdateQuery updateQuery = new UpdateQueryBuilder().withId(student.getId().toString())
                .withClass(Student.class).withIndexRequest(indexRequest).build();
        elasticsearchTemplate.update(updateQuery);
    }


    /**
     * 批量更新
     */
    @PutMapping("/batch")
    public void updateBatch(@RequestBody List<Student> students) {
        List<UpdateQuery> queries = new ArrayList<>();

        for (Student product : students) {
            IndexRequest indexRequest = new IndexRequest();
            Map<String, Object> map = BeanUtils.beanToMap(product);
            indexRequest.source(map, XContentType.JSON);    //更新整个实体
            UpdateQuery updateQuery = new UpdateQueryBuilder().withId(product.getId().toString())
                    .withClass(Student.class).withIndexRequest(indexRequest).build();

            queries.add(updateQuery);
        }
        elasticsearchTemplate.bulkUpdate(queries);

    }
}
