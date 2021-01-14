package com.piter.controller;

import com.piter.entity.data1.User1;
import com.piter.entity.data2.User2;
import com.piter.mapper.data1.User1Mapper;
import com.piter.mapper.data2.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分布式事务
 */
@RestController
@RequestMapping("/atomikos")
public class AtomikosController {

    @Autowired
    private User1Mapper user1Mapper;
    @Autowired
    private User2Mapper user2Mapper;

    @RequestMapping("/test/{a}")
    @Transactional
    public String atomikos(@PathVariable("a") int a) {
        User1 user1 = new User1();
        user1.setUserAge(18);
        user1.setUserName("aaa");
        user1Mapper.insertUser1(user1);
        System.out.println(1/a);
        User2 user2 = new User2();
        user2.setAge(18);
        user2.setName("bbb");
        user2Mapper.insertTest(user2);
        return "OK";
    }

}
