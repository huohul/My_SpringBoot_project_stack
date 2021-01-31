package com.gxweb.springbootmultidatasourcemybatispluslog4j.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gxweb.springbootmultidatasourcemybatispluslog4j.po.MultiTable;
import com.gxweb.springbootmultidatasourcemybatispluslog4j.service.MultiTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2021/1/30/030 13:13
 * @description ：前端控制层
 * @version: 1.0
 */
@RestController
@RequestMapping("/MultiTable")
public class MultiTableController {
    @Autowired
    private MultiTableService multiTableService;
    int i = 0;

    /**
     * 主从库添加
     *
     * @param multiTable  必须带参数
     * @return
     */
    @PostMapping
    public Object save(@RequestBody MultiTable multiTable) {
//        multiUser.setGmtCreate(new Date());  //因为mysql  版本原因 无法自动填充创建时间
        multiTable.setName("主库添加了数据" + i++);
        multiTable.setGmtCreate(new Date());
        boolean insert = multiTableService.insert(multiTable);
        if (!insert) return "主库添加数失败";

        multiTable.setName("从库添加了数据" + (i + 1));
        multiTable.setGmtCreate(new Date());
        if (!multiTableService.save(multiTable))
            return "从库添加失败";

        return "主库从库各自添加一条数据";

    }

    //批量添加 主库
    @PostMapping("/MultiUserlist")
    public Object saveBatch(@RequestBody List<MultiTable> objList) {
        return multiTableService.insertBatch(objList);
    }

    //根据id删除  因为实体类的配置 为逻辑删除 主库
    @DeleteMapping("/{id}")
    public Object removeById(@PathVariable Long id) {
        System.out.println("delete....");
        return multiTableService.deleteById(id);
    }
    //根据传入参数条件进行删除   该service 不支持 主库
    @DeleteMapping
    public Object remove(@RequestBody MultiTable multiTable) {
        return multiTableService.delete(multiTable);
    }

    //    根据id批量删除 该service 不支持 主库
    @DeleteMapping("/list")
    public Object removeBatch(@RequestBody List<Long> idList) {
        return multiTableService.deleteByIds(idList);
    }

    //根据id修改  id 必传 主库
    @PutMapping()
    public Object modifyById(@RequestBody MultiTable multiTable) {
        return multiTableService.modifyById(multiTable);
    }

    //根据id查询 主库
    @GetMapping("/{id}")
    public Object getById(@PathVariable Long id) {
        return multiTableService.selectById(id);
    }

    //根据条件进行查询 主库
    @PostMapping("/list")
    public Object list(@RequestBody MultiTable multiTable) {
        return multiTableService.selectList(multiTable);
    }

    //根据id 批量查询 参数为 List Long集合  [19,20,21] 主库
    @PostMapping("/idList")
    public Object listById(@RequestBody List<Long> idList) {
        return multiTableService.selectByIds(idList);
    }

    //根据条件分页查询  Postman  有测试案例 主库
    @PostMapping("/page")
    public Object page(@RequestBody MultiTable multiTable,
                       @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                       @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize) {
        IPage<MultiTable> iPage = multiTableService.selectPage(multiTable, pageNo, pageSize);
        System.out.println("查询到"+iPage.getTotal());
        return iPage;
    }
}
