package com.gxweb.springbootmultidatasourcemybatispluslog4j.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gxweb.springbootmultidatasourcemybatispluslog4j.po.MultiUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ： CYQ
 * @date ：Created in 2021/1/30/030 13:02
 * @description ：MultiUser接口
 * @version: 1.0
 */
@Mapper
public interface MultiUserMapper extends BaseMapper<MultiUser>{
}
