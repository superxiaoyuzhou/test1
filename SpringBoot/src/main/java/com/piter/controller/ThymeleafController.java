package com.piter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 模板引擎测试
 */
@Controller
@RequestMapping("/tpl")
public class ThymeleafController {

    @RequestMapping("/template")
    public String testThymeleaf(ModelMap map) {
        map.addAttribute("name", "test模板引擎测试");
        return "TestTemplate";
    }
}
