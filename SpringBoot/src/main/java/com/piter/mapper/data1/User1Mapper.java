package com.piter.mapper.data1;

import com.piter.entity.data1.User1;

import java.util.List;

public interface User1Mapper {

    List<User1> findAll();

    void insertUser1(User1 user1);

}