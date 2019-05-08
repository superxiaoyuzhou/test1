package com.example.test.model.bo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class ApplicantBO {
    /**
     * 性别，0男，1女
     */
    private String sex;
    /**
     * 投保人姓名
     */
    private String name;
    /**
     * 投保人邮箱
     */
    private String email;
    /**
     * 投保人电话
     */
    private String phone;
    /**
     * 投保人证件号码
     */
    @JSONField(name = "card_id")
    private String cardId;
}
