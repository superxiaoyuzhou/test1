package com.piter.mapper;

import com.piter.entity.User;
import com.piter.pojo.UserDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    List<User> findAll();

    User findById(Integer id);

    List<User> findByMap(Map<String, String> map);

    User findById(@Param("userName") String name, @Param("age") Integer age);

    List<User> findByObject(UserDto dto);
}