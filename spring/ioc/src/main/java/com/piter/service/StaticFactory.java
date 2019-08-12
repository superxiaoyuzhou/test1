package com.piter.service;

import com.piter.service.impl.AccountServiceImpl;

public class StaticFactory{
    public static AccountServiceImpl getObject(){
        return new AccountServiceImpl();
    }
}
