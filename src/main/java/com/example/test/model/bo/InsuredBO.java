package com.example.test.model.bo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class InsuredBO {
    /**
     * 投保人与被保人关系
     */
    private String relation;
    /**
     * 被保人姓名
     */
    private String name;
    /**
     * 被保人手机
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
