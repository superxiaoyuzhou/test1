package com.piter.mapper;

import com.piter.entity.User;
import com.piter.modle.UserDto;
import com.piter.modle.UserVo;
import com.piter.modle.UserVo2;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    List<User> findAll();

    User findById(Integer id);

    List<User> findByMap(Map<String, String> map);

    User findById(@Param("userName") String name, @Param("age") Integer age);

    List<User> findByObject(UserDto dto);

    @Results(id = "UserVo1",value = {
            @Result(property = "id",column = "id",id = true),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "age",column = "user_age"),
            @Result(property = "address",column = "user_address")
    })
    @Select(" select id,user_name,user_age,user_address from user WHERE id = #{id,jdbcType=INTEGER}")
    List<UserVo> findById2(Integer id);

    @ResultMap("UserVo")
    @Select("select id,user_name,user_age,user_address from user WHERE user_name = #{user_name,jdbcType=VARCHAR}")
    UserVo findById3(@Param("userName") String name, @Param("age") Integer age);

    @Insert("insert into USER(user_name,user_age,user_address) VALUES (#{userName},#{userAge},#{userAddress})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int saveUser(UserDto dto);

    int saveUser1(UserDto dto);

    int saveUser2(UserDto dto);

    List<User> testForeach(String[] ids);

    UserVo2 testAssociation1(Integer id);

    UserVo2 testAssociation2(Integer id);

}