package com.gxweb.springbootmultidatasourcemybatispluslog4j.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxweb.springbootmultidatasourcemybatispluslog4j.po.MultiUser;
import com.gxweb.springbootmultidatasourcemybatispluslog4j.service.MultiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author ： CYQ
 * @date ：Created in 2021/1/30/030 13:13
 * @description ：前端控制层
 * @version: 1.0
 */
@RestController
@RequestMapping("/MultiUser")
public class MultiUserController {
    @Autowired
    private MultiUserService multiUserService;
    int i = 0;

    /**
     * 主从库添加
     *
     * @param multiUser 必须带参数
     * @return
     */
    @PostMapping
    public Object save(@RequestBody MultiUser multiUser) {
//        multiUser.setGmtCreate(new Date());  //因为mysql  版本原因 无法自动填充创建时间
        multiUser.setName("主库添加了数据" + i++);
        multiUser.setAge(new Random().nextInt(100));
        multiUser.setGmtCreate(new Date());
        boolean insert = multiUserService.insert(multiUser);
        if (!insert) return "主库添加数失败";

        //因为这里MultiUser对象为同一个对象 id 默认值是同一个 没有作更改 不+1 的话  主库从库都是同一个id  就存在两个库id相同 数据不同的问题
        multiUser.setId(null);
        multiUser.setName("从库添加了数据" + (i + 1));
        multiUser.setAge(new Random().nextInt(100));
        multiUser.setGmtCreate(new Date());
        if (!multiUserService.save(multiUser))
            return "从库添加失败";

        return "主库从库各自添加一条数据";

    }

    //批量添加 主库
    @PostMapping("/MultiUserlist")
    public Object saveBatch(@RequestBody List<MultiUser> objList) {
        return multiUserService.insertBatch(objList);
    }

    //根据id删除  因为实体类的配置 为逻辑删除 主库
    @DeleteMapping("/{id}")
    public Object removeById(@PathVariable Long id) {
        System.out.println("delete....");
        return multiUserService.deleteById(id);
    }

    //根据传入参数条件进行删除   该service 不支持 主库
    @DeleteMapping
    public Object remove(@RequestBody MultiUser multiUser) {
        return multiUserService.delete(multiUser);
    }

    //    根据id批量删除 该service 不支持 主库
    @DeleteMapping("/list")
    public Object removeBatch(@RequestBody List<Long> idList) {
        return multiUserService.deleteByIds(idList);
    }

    //根据id修改  id 必传 主库
    @PutMapping()
    public Object modifyById(@RequestBody MultiUser multiUser) {
        return multiUserService.modifyById(multiUser);
    }

    //根据id查询 主库
    @GetMapping("/{id}")
    public Object getById(@PathVariable Long id) {
        return multiUserService.selectById(id);
    }

    //根据条件进行查询 主库
    @PostMapping("/list")
    public Object list(@RequestBody MultiUser multiUser) {
        return multiUserService.selectList(multiUser);
    }

    //根据id 批量查询 参数为 List Long集合  [19,20,21] 主库
    @PostMapping("/idList")
    public Object listById(@RequestBody List<Long> idList) {
        return multiUserService.selectByIds(idList);
    }

    //根据条件分页查询  Postman  有测试案例 主库
    @PostMapping("/page")
    public Object page(@RequestBody MultiUser multiUser,
                       @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize) {
        IPage<MultiUser> iPage = multiUserService.selectPage(multiUser, pageNo, pageSize);
        System.out.println("查询到" + iPage.getTotal());
        return iPage;
    }

    /************************分隔线下为 主从复制 从主复制 ****************************/
    /**
     * 因为只建议从库查询  主库增删改  所以这里做一个从主复制
     */
    @GetMapping("/SaDBltoMasDB")
    public String saltomas() {
        List<MultiUser> SomultiUserList = multiUserService.list(new QueryWrapper<>());
        for (MultiUser SomultiUser : SomultiUserList) {
            MultiUser MamultiUser = multiUserService.selectById(SomultiUser.getId());
            //逻辑解析：如果拿着从库的id 去主库查询 查到了 并且与从库数据一样 就存在同样数据不执行复制 如果没有查到 就执行复制
            if (SomultiUser.equals(MamultiUser)) {
                System.out.println("存在相同数据不执行复制,相同数据是:" + SomultiUser);
            } else {
                System.out.println("存在不同数据进行从入主" + SomultiUser);
                if (multiUserService.insert(SomultiUser)) {
                    System.out.println("从库数据在主库中未查询到一样的数据进行从主复制 ok");
                } else {
                    System.out.println("从库数据在主库中未查询到一样的数据进行从主复制 fall");
                }
            }
        }
        return "从主复制成功";
    }


    /**
     * 即使不建议 也要来个主从复制
     */
    @GetMapping("/MastoSal")
    public String masToSal() {
        List<MultiUser> MamultiUserList = multiUserService.findMasAll();
        for (MultiUser MsmultiUser : MamultiUserList) {
            MultiUser SamultiUser = multiUserService.getById(MsmultiUser.getId());
            //逻辑解析：如果拿着从库的id 去主库查询 查到了 并且与从库数据一样 就存在同样数据不执行复制 如果没有查到 就执行复制
            if (MsmultiUser.equals(SamultiUser)) {
                System.out.println("存在相同数据不执行复制,相同数据是:" + MsmultiUser);
            } else {
                multiUserService.save(MsmultiUser);
                System.out.println("主库数据存入从库ok 数据是" + MsmultiUser);
            }
        }
        return "主从复制成功";
    }

    /**
     * 从库查询所有
     */
    @GetMapping("/getSalAll")
    public List<MultiUser> getSalAll() {
        List<MultiUser> personlist = multiUserService.list();
        System.out.println(personlist);
        return personlist;
    }
}
