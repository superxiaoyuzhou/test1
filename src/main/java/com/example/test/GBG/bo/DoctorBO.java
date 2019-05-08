package com.example.test.GBG.bo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class DoctorBO {
    private String name;
    @JSONField(name = "is_all")
    private String isAll;
    @JSONField(name = "insured_card_id")
    private String insuredCardId;
}
