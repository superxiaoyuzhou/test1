package com.piter.service;

import com.piter.service.impl.AccountServiceImpl;

public class InstanceFactory {

    public IAccountService createAccountService(){
        return new AccountServiceImpl();
    }
}
