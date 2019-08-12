package com.piter.service;

import com.piter.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

public class AccountServiceFactory implements FactoryBean {
    public Object getObject() throws Exception {
        return new AccountServiceImpl();
    }

    public Class<?> getObjectType() {
        return null;
    }
}
