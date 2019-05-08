package com.example.test.zxEj.bo;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class ProductBO {
    /**
     * 产品代码
     */
    private String code;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 计划名称
     */
    @JSONField(name = "package")
    private String packageName;
    /**
     * 计划代码
     */
    @JSONField(name = "package_code")
    private String packageCode;

}
