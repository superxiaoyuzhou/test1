package com.example.test.model.bo;


import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Data
public class CoreInfoBO {
    /**
     * 保费，单位分
     */
    private String total;
    /**
     * 被保人信息
     */
    private List<InsuredBO> insured;
    /**
     * 投保产品信息
     */
    private ProductBO product;
    /**
     * 其它
     */
    private AddtionalBO addtional;
    /**
     * 投保人信息
     */
    private ApplicantBO applicant;
}
