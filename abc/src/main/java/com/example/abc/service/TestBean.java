package com.example.abc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestBean {

    @Autowired
    @Qualifier("oracleDriveManagerDataSource")
    private DataSource dataSource;


    @RequestMapping("/test")
    public String test(){
       return dataSource.test1();
    }

}
