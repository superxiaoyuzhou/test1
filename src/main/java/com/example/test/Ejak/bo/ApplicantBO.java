package com.example.test.Ejak.bo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

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
     * 被保人证件号码
     */
    @JSONField(name = "card_id")
    private String cardId;
    /**
     * 出生年月日，"1978-06-21"
     */
    @JSONField(format="yyyy-MM-dd")
    private Date birthday;
    /**
     * 被保人证件类型
     */
    @JSONField(name = "card_type")
    private String cardType;

}
