package com.example.test.zxEj.bo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class OrderBO {
    /**
     * 订单号
     */
    @JSONField(name = "order_id")
    private String orderId;
    @JSONField(name = "request_id")
    private String requestId;
    @JSONField(name = "core_info")
    private CoreInfoBO coreInfo;
    @JSONField(name = "policy_info")
    private List<PolicyInfoBO> policyInfo;

}
