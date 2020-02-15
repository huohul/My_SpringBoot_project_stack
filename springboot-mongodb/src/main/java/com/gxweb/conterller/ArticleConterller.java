package com.gxweb.conterller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.gxweb.entity.Article;
import com.gxweb.entity.Result;
import com.gxweb.entity.StatusCode;
import com.gxweb.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ： CYQ
 * @date ：Created in 2020/02/15 20:45
 * @description ：MongoDB
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/mongo")
public class ArticleConterller {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Snowflake snowflake;

    /**
     * 测试新增
     */
    @PostMapping("/save")
    public Boolean Save() {
        Boolean flag = false;
        try {
            Article article = new Article(1L, RandomUtil.randomString(20), RandomUtil.randomString(150), DateUtil.date(), DateUtil
                    .date(), 0L, 0L);
            articleRepository.save(article);
            log.info("【article】= {}", JSONUtil.toJsonStr(article));
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 测试新增列表
     */
    @PostMapping("/saveAll")
    public Result SaveList() {
        List<Article> articles = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            articles.add(new Article(snowflake.nextId(), RandomUtil.randomString(20), RandomUtil.randomString(150), DateUtil
                    .date(), DateUtil.date(), 0L, 0L));
        }
        articleRepository.saveAll(articles);
        log.info("【articles】= {}", JSONUtil.toJsonStr(articles.stream()
                .map(Article::getId)
                .collect(Collectors.toList())));
        return new Result(true, StatusCode.OK, "批量增加成功");
    }

    /**
     * 测试更新 指定id 为 1L 的更新
     */
    @GetMapping("/findByIdUpdate")
    public Result Update(Long id) {
        if ("Optional.empty".equals(articleRepository.findById(id).toString()))
            return new Result(false, StatusCode.ERROR, "没有找到此条数据，请重输入正确的id号");
        articleRepository.findById(id).ifPresent(article -> {
            article.setTitle(article.getTitle() + "更新之后的标题----------------");
            article.setUpdateTime(DateUtil.date());
            articleRepository.save(article);
            log.info("》》》》》》》》》》》》》》》》》》》》》【article】= {}", JSONUtil.toJsonStr(article));
        });
        return new Result(true, StatusCode.OK, "更新成功，返回成功后数据",articleRepository.findById(id).get());
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public Result findBuId(Long id) {
//        articleRepository.findById(id).ifPresent(article -> log.info("【标题】= {}【点赞数】= {}【访客数】= {}", article.getTitle(), article.getThumbUp(), article.getVisits()));
        if ("Optional.empty".equals(articleRepository.findById(id).toString()))
            return new Result(false, StatusCode.ERROR, "没有找到此条数据，请重输入正确的id号");
        return new Result(true,StatusCode.OK,"查询成功",articleRepository.findById(id).get());
    }

    /**
     * 根据标题模糊查询
     */
    @GetMapping("/like_")
    public Result FindByTitleLike(Article article) {
        if (article.getTitle() == null)
            return new Result(false, StatusCode.ERROR, "没有找到，请输入正确的标题！！！");
        List<Article> articles = articleRepository.findByTitleLike(article.getTitle());
        log.info("【articles】= {}", JSONUtil.toJsonStr(articles));
        return new Result(true, StatusCode.OK, "查询成功", articles.size()== 0 ?"没有此数据":articles);
    }

    /**
     * 测试分页排序查询
     * 根据点赞（点赞数量） 和 修改时间排序
     */
    @GetMapping("/page")
    public Page<Article> Query(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Sort sort = Sort.by("thumbUp", "updateTime").descending();
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Article> all = articleRepository.findAll(pageRequest);
        log.info("【总页数】= {}", all.getTotalPages());
        log.info("【总条数】= {}", all.getTotalElements());
        log.info("【当前页数据】= {}", JSONUtil.toJsonStr(all.getContent()
                .stream()
                .map(article -> "文章标题：" + article.getTitle() + "点赞数：" + article.getThumbUp() + "更新时间：" + article.getUpdateTime())
                .collect(Collectors.toList())));
        System.out.println("\n" +
                "\n" +
                "     ┌─┐       ┌─┐               \n" +
                "  ┌──┘ ┴───────┘ ┴──┐            \n" +
                "  │                 │            \n" +
                "  │       ───       │            \n" +
                "  │  ─┬┘       └┬─  │            \n" +
                "  │                 │            \n" +
                "  │       ─┴─       │            \n" +
                "  │                 │            \n" +
                "  └───┐         ┌───┘            \n" +
                "      │         │                \n" +
                "      │         │                \n" +
                "      │         │                \n" +
                "      │         └──────────────┐ \n" +
                "      │                        │ \n" +
                "      │                        ├─┐\n" +
                "      │                        ┌─┘\n" +
                "      │                        │ \n" +
                "      └─┐  ┐  ┌───────┬──┐  ┌──┘ \n" +
                "        │ ─┤ ─┤       │ ─┤ ─┤    \n" +
                "        └──┴──┘       └──┴──┘    \n");

        return all;
    }


    /**
     * 根据id进行点赞、访客数，使用save方式更新点赞、访客
     */
    @GetMapping("/dianzUp")
    public String ThumbUp(Long id) {
        if ("Optional.empty".equals(articleRepository.findById(id).toString()))
            return "没有找到此条数据，请重输入正确的id号";
        articleRepository.findById(id).ifPresent(article -> {
            article.setThumbUp(article.getThumbUp() + 1);
            article.setVisits(article.getVisits() + 1);
            articleRepository.save(article);
            log.info("【标题】= {}【点赞数】= {}【访客数】= {}", article.getTitle(), article.getThumbUp(), article.getVisits());
        });
        return "更新成功 谢谢您点了一赞";
    }

    /**
     * 根据id进行取消点赞、访客数，使用save方式更新点赞、访客
     */
    @GetMapping("/canceldianzUp")
    public String EliminateThumbUp(Long id) {
        if ("Optional.empty".equals(articleRepository.findById(id).toString()))
            return "没有找到此条数据，请重输入正确的id号";
        articleRepository.findById(id).ifPresent(article -> {
            article.setThumbUp(article.getThumbUp() - 1);
            article.setVisits(article.getVisits() + 1);
            articleRepository.save(article);
            log.info("【标题】= {}【点赞数】= {}【访客数】= {}", article.getTitle(), article.getThumbUp(), article.getVisits());
        });
        return "更新成功 取消了点赞赞";
    }

    /**
     * 更新点赞数、访客数，使用更优雅/高效的方式更新点赞、访客
     * 根据 id
     */
    @GetMapping("/dianzUpPlus")
    public String ThumbUp2(Long id) {
        if ("Optional.empty".equals(articleRepository.findById(id).toString()))
            return "没有找到此条数据，请重输入正确的id号";
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbUp", 1L);
        update.inc("visits", 1L);
        mongoTemplate.updateFirst(query, update, "article");

        articleRepository.findById(id)
                .ifPresent(article -> log.info("【标题】= {}【点赞数】= {}【访客数】= {}", article.getTitle(), article.getThumbUp(), article
                        .getVisits()));
        return "更新成功 谢谢您点了一赞";
    }

    /**
     * 测试删除
     */
    @DeleteMapping("/del")
    public String Delete(Long id) {
        if (id == null) {
            // 全部删除
            articleRepository.deleteAll();
            return "未指定删除哪一条数据，执行全部删除";
        } else {
            // 根据主键删除
            articleRepository.deleteById(id);   //我靠没有返回值 不知道到底删除成功没 ？？？？？？
            return "想知道到底删除成功没，请按本id查询即可得知,如无此条数据，请输入正确的id号";
        }
    }

    /**
     * 局部服务降级方法
     * 注意事项：服务返回值，是String
     * 服务参数必须与方法名称保持一致
     * @param id
     * @return
     */
    public String LocalError(Long id) {
        return "没有找到，请重新试试！！！！";
    }
    /**
     * 全局服务降级方法  默认方法
     * 注意事项：
     * 返回值必须是String
     * 没有参数参数
     * @return
     */
    public String defautFallback() {
        return "默认回复：系统繁忙，请您稍后再来光顾！！！！";
    }


}
