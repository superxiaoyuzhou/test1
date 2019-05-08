package com.example.abc.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class OracleDriveManagerDataSource implements DataSource {

    public OracleDriveManagerDataSource(){

    }
    @Override
    public String test1() {

        return "Oracle";
    }
}
