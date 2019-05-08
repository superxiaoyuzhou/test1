package com.example.test.GBG.bo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class InsuredBO {
    private String BMI;
    private String sex;
    private String city;
    /**
     * 被保人姓名
     */
    private String name;
    private String email;
    /**
     * 被保人手机
     */
    private String phone;
    private String height;
    private String weight;
    private String address;
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
    private String district;
    private String marriage;
    private String province;
    /**
     * 投保人与被保人关系
     */
    private String relation;
    /**
     * 被保人证件类型
     */
    @JSONField(name = "card_type")
    private String cardType;
    private String nationality;
    @JSONField(name = "is_address_applicant")
    private String isAddressApplicant;
}
