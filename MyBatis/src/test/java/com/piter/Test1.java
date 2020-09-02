package com.piter;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

public class Test1 {

    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void test1() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
        this.sqlSessionFactory = SqlSessionManager.newInstance(reader);
    }

}
