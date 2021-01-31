package com.gxweb.springbootmultidatasourcemybatispluslog4j.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.gxweb.springbootmultidatasourcemybatispluslog4j.mapper.MultiTableMapper;
import com.gxweb.springbootmultidatasourcemybatispluslog4j.po.MultiTable;
import com.gxweb.springbootmultidatasourcemybatispluslog4j.service.MultiTableService;
import org.springframework.stereotype.Service;

/**
 * @author ： CYQ
 * @date ：Created in 2021/1/30/030 15:47
 * @description ：
 * @version: 1.0
 */
@DS("slave")
@Service
public class MultiTableServiceImpl extends BaseServiceImpl<MultiTableMapper, MultiTable> implements MultiTableService {
}
