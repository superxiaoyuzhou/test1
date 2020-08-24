package com.piter.service;

import com.piter.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.FactoryBean;

public class AccountServiceFactory implements FactoryBean {
    public Object getObject() throws Exception {
        return new AccountServiceImpl();
    }

    public Class<?> getObjectType() {
        return AccountServiceImpl.class;
    }
}
