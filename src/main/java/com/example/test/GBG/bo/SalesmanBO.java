package com.example.test.GBG.bo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class SalesmanBO {
    private String name;
    private String phone;
    @JSONField(name = "card_id")
    private String cardId;
    @JSONField(name = "card_type")
    private String cardType;
}
