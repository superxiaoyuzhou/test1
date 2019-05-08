package com.example.test.controller;

import com.example.test.model.dto.YbDTO;
import com.example.test.util.MD5Util;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("test")
    public Object test(@RequestBody YbDTO ybDTO){
        String orderId = ybDTO.getData().getOrderId();
        String sign = ybDTO.getSign();
        if (sign.equals(MD5Util.MD5Encode(orderId,"???"))){
            
        }
        System.out.println(ybDTO);
        return ybDTO;
    }

}
