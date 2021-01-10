package com.piter.provider1.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello/{name}")
    public String Hello(@PathVariable String name){
        return "provider1 hello:" + name;
    }
}
