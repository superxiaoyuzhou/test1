package com.example.test.GBG.bo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

@Data
public class PolicyInfoBO {
    /**
     * 保单号
     */
    @JSONField(name = "policy_no")
    private String policyNO;
    /**
     * 保单价格，单位分
     */
    @JSONField(name = "policy_price")
    private String policyPrice;
    /**
     * 保单生效期
     */
    @JSONField(name = "policy_effect",format = "yyyy-MM-dd")
    private Date policyEffect;
    /**
     * 保单日期
     */
    @JSONField(name = "policy_expire",format = "yyyy-MM-dd")
    private Date policyExpire;
    /**
     * 在线保单地址
     */
    @JSONField(name = "policy_url")
    private String policyUrl;

}
