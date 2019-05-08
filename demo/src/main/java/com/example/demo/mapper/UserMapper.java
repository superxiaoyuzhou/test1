package com.example.demo.mapper;

import com.example.demo.entity.User;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface UserMapper extends Mapper<User>,MySqlMapper<User>, IdsMapper<User>, ConditionMapper<User> {

    List<User> findAll();

    User findById(Integer id);
}