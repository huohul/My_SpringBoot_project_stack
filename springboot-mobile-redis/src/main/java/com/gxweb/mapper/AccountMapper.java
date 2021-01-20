package com.gxweb.mapper;

import com.gxweb.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2021/1/20/020 21:40
 * @description ：
 * @version: 1.0
 */
@Mapper
@Repository
public interface AccountMapper {
    List<Account> ListAccount();
}
