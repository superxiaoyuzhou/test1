package com.example.consumer2.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class ConfigBean {
    private int age;
    private String name;

    /**
     * 在字段上加@apollojsonValue示例，默认值指定为空列表-[]
     * jsonBeanProperty=[{"id":1,"name":"李四"},{"id":2,"name":"赵六"}]
     */
    private List<User> anotherJsonBeans;
}
