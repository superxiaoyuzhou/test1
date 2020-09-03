package com.piter;

import com.piter.entity.User;
import com.piter.mapper.UserMapper;
import com.piter.modle.UserDto;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;

public class Test1 {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        //1.读取配置文件创建SqlSessionFactory
        this.sqlSessionFactory = SqlSessionManager.newInstance(reader);
    }

    //测试自动映射
    @Test
    public void testAutoMapping() {
        //2.获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.获取对应mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4.执行查询并返回结果集
        List<User> userList = mapper.findAll();
        System.out.println(userList);
        HashMap<String, String> map = new HashMap<>();
        map.put("userName", "张三");
        map.put("age", "18");
        System.out.println(mapper.findByMap(map));
        UserDto userDto = new UserDto();
        userDto.setUserName("赵六");
        userDto.setAge(20);
        mapper.saveUser2(userDto);
        System.out.println("id: " + userDto.getId());
    }

}
