package com.example.consumer2.client;

import com.example.consumer2.entity.ConfigBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("provider-hello")
public interface EurekaServiceFeign {

    @RequestMapping("/user/find1")
    public ConfigBean hello();
}
