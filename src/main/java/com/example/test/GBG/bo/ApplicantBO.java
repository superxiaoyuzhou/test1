package com.example.test.GBG.bo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class ApplicantBO {
    private String BMI;
    /**
     * 性别，0男，1女
     */
    private String sex;
    private String city;
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
    @JSONField(name = "city_zip")
    private String cityZip;
    private String district;
    private String marriage;
    private String province;
    /**
     * 被保人证件类型
     */
    @JSONField(name = "card_type")
    private String cardType;
    @JSONField(name = "address_zip")
    private String addressZip;
    private String nationality;
    @JSONField(name = "districy_zip")
    private String districyZip;
    @JSONField(name = "province_zip")
    private String provinceZip;
    @JSONField(name = "is_address_equal")
    private String isAddressEqual;

}
