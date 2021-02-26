package com.piter.cloud.controller;

import com.netflix.client.http.HttpRequest;
import com.piter.cloud.domain.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @RequestMapping("/**")
    public CommonResult all(HttpServletRequest request) {

        return new CommonResult(request.getRequestURI());
    }
}
