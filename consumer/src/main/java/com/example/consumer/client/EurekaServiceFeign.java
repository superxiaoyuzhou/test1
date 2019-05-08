package com.example.consumer.client;

import com.example.consumer.entity.ConfigBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("provider-hello")
public interface EurekaServiceFeign {

    @RequestMapping("/user/find1")
    public ConfigBean hello();
}
