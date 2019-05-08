package com.example.abc.service;

import org.springframework.stereotype.Service;

@Service
public class MysqlDriveManagerDataSource implements DataSource {

    public MysqlDriveManagerDataSource(){
        System.out.println("MySql");
    }
    @Override
    public String test1() {
        return "MySql";
    }
}
