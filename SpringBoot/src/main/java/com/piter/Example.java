package com.piter;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

/**
 * spring boot官网文档例子
 */
@RestController
/**
 * 启动的加载AutoConfigurationImportSelector.class
 * 加载 META-INF/spring.factories
 * 加载 ...
 */
@EnableAutoConfiguration
//@SpringBootApplication
public class Example {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(Example.class, args);
    }

}