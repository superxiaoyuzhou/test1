package com.piter.mybatisplus.controller;

import com.piter.mybatisplus.entity.Test;
import com.piter.mybatisplus.mapper.TestMapper;
import com.piter.mybatisplus.service.impl.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@RestController
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @RequestMapping("test1")
    public Object test1(){
        Test test = new Test();
        test.setId(11111);
        test.setName("aaaa");
        test.setAge(11);
        testMapper.insertTest(test);
        return null;
    }

    @RequestMapping("test2")
    public Object test2(){

        Test test1 = testMapper.selectById(11111);
        return test1;
    }

    @RequestMapping("test3")
    public Object test3(){

        Test test1 = testMapper.findTest(11111);
        return test1;
    }

    @RequestMapping("test4")
    public Object test4(){

        return "test4";
    }

    @RequestMapping("/**")
    public Object test5(HttpServletRequest request){
        System.out.println(request.getRequestURI());
        return request.getContextPath();
    }
}

