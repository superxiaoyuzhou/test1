package com.piter.dao.impl;

import com.piter.dao.IAccountDao;
import org.springframework.stereotype.Repository;

/**
 * 账户的持久层实现类
 */
@Repository("iAccountDao")
public class AccountDaoImpl implements IAccountDao {

    public void saveAccount(){
        System.out.println("AccountDaoImpl的saveAccount()方法");
    }
}
