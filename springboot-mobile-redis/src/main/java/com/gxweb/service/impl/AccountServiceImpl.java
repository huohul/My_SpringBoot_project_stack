package com.gxweb.service.impl;

import com.gxweb.domain.Account;
import com.gxweb.mapper.AccountMapper;
import com.gxweb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ： CYQ
 * @date ：Created in 2021/1/20/020 21:44
 * @description ：
 * @version: 1.0
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> ListAccount() {
        return accountMapper.ListAccount();
    }

}
