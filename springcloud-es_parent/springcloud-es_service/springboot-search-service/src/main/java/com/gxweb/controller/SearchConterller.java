package com.gxweb.controller;

import com.gxweb.entity.Result;
import com.gxweb.entity.StatusCode;
import com.gxweb.service.EsManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ： CYQ
 * @date ：Created in 2019/07/14 21:15
 * @description ：导入数据
 * @version: 1.0
 */
@RestController
@RequestMapping("/stu_search")
public class SearchConterller {

    @Autowired
    private EsManageService esManageService;

    /**
     * 导入所有数据
     *
     * @return
     */
    @GetMapping("/importAll")
    public Result importAllDataToES() {
        esManageService.importDataToESBySpuId(null);
        return new Result(true, StatusCode.OK, "导入所有数据成功");
    }

}
