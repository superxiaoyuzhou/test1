package com.example.consumer.controller;

import com.example.consumer.client.EurekaServiceFeign;
import com.example.consumer.entity.ConfigBean;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    /**
     * restTemplate方式
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * feign的方式
     */
    @Autowired
    private EurekaServiceFeign eurekaServiceFeign;

    @RequestMapping("/find1")
    public Object Hello(){
        String url = "http://provider-hello/user/find1";
        String str = restTemplate.getForObject(url, String.class);
//        ConfigBean str = restTemplate.getForObject(url, ConfigBean.class);
        return str;
    }

    @RequestMapping("find2")
    @HystrixCommand(fallbackMethod = "helloFallback")
    public Object hello(){
        ConfigBean configBean = eurekaServiceFeign.hello();
        return configBean;
    }

    public String helloFallback(){
        return "熔断启用回调";
    }
}
