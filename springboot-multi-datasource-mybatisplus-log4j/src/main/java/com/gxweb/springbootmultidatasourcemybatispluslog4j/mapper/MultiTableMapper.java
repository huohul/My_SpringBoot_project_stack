package com.gxweb.springbootmultidatasourcemybatispluslog4j.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxweb.springbootmultidatasourcemybatispluslog4j.po.MultiTable;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ： CYQ
 * @date ：Created in 2021/1/30/030 13:03
 * @description ：MultiTable接口
 * @version: 1.0
 */
@Mapper
public interface MultiTableMapper extends BaseMapper<MultiTable> {
}
