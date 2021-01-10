package com.piter;

import com.piter.entity.User;
import com.piter.mapper.UserMapper;
import com.piter.modle.UserDto;
import com.piter.modle.UserVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
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
        //2.获取sqlSession,true自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
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

    @Test
    public void test3() {
        DefaultObjectFactory objectFactory = new DefaultObjectFactory();
        UserVo userVo = objectFactory.create(UserVo.class, Arrays.asList(Integer.class, String.class),Arrays.asList(0,""));
        DefaultObjectWrapperFactory wrapperFactory = new DefaultObjectWrapperFactory();
        DefaultReflectorFactory reflectorFactory = new DefaultReflectorFactory();
        MetaObject metaObject = MetaObject.forObject(userVo, objectFactory,wrapperFactory,reflectorFactory);
        metaObject.setValue("id", 1);
        metaObject.setValue("userName", "张三");
        System.out.println(metaObject.getSetterNames());
        System.out.println(userVo);

    }

}
