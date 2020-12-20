package com.piter;

import com.piter.entity.User;
import com.piter.mapper.UserMapper;
import com.piter.modle.UserDto;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
//        this.sqlSessionFactory = SqlSessionManager.newInstance(reader);
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
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

    /**
     * mapper接口，本质是通过动态代理调用sqlSession的方法，也就是mybatis的前生ibatis的方法
     */
    @Test
    public void test() {
        //2.获取sqlSession,true自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //3.执行查询并返回结果集
        User user = sqlSession.selectOne("com.piter.mapper.UserMapper.findById", 1);
        System.out.println(user);

        UserDto userDto = new UserDto();
        userDto.setUserName("赵六");
        userDto.setAge(20);
        sqlSession.insert("com.piter.mapper.UserMapper.saveUser2", userDto);
        System.out.println("id: " + userDto.getId());
    }

}
