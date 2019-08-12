package com.piter.multipartfile.controller;

import com.piter.multipartfile.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TestController {

    @PostMapping("/test")
    public void test(MultipartFile file) {
        System.out.println(file.getName());
        System.out.println(file.getSize());
    }

    @PostMapping("/test2")
    public void test2(User user) {
        MultipartFile file = user.getFile();
        System.out.println(user);
        System.out.println(file.getName());
        System.out.println(file.getSize());
    }

    @PostMapping("/test3")
    public void test3(@RequestBody User user) {
        MultipartFile file = user.getFile();
        System.out.println(user);
        System.out.println(file.getName());
        System.out.println(file.getSize());
    }

}
