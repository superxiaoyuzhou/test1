package com.example.test.zxEj.bo;

import lombok.Data;

@Data
public class AddtionalBO {
    /**
     * 生效日期
     */
    private String effect;
    /**
     * 用户id
     */
    private String ut;
    private String duration;
    private InvoiceBO invoice;
}
