package com.example.test.GBG.bo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class ImfBO {
    @JSONField(name = "is_all")
    private String isAll;
    @JSONField(name = "img_url")
    private String imgUrl;
    @JSONField(name = "insured_card_id")
    private String insuredCardId;
}
