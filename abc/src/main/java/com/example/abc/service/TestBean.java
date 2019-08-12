package com.example.abc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestBean {

    @Autowired
    @Qualifier("oracleDriveManagerDataSource")
    private DataSource dataSource;


    @RequestMapping("/test")
    public String test(){
       return dataSource.test1();
    }

    @RequestMapping("/test1")
    public String test1(@RequestBody Map<String,Object> reqest) {
        return reqest.toString();
    }

    @RequestMapping("/test2")
    public String test2(@RequestBody ReqIds reqIds) {
        return reqIds.toString();
    }

}
