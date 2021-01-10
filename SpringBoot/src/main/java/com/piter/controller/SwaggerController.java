package com.piter.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Swagger测试
 * 访问路径:
 */
@RestController
@RequestMapping("/swagger")
public class SwaggerController {

    @ApiOperation(value = "获取信息", notes = "根据id获取信息")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String")
    @GetMapping("/{id}")
    public Map<String, String> getInfo(@PathVariable String id){
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        return map;
    }
}
