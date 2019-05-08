package com.example.test.GBG.bo;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class CoreInfoBO {
    /**
     * 保费，单位分
     */
    private String total;

    private String file;

    private List<ImfBO> img;

    private List<DoctorBO> doctor;
    /**
     * 被保人信息
     */
    private List<InsuredBO> insured;
    /**
     * 投保产品信息
     */
    private ProductBO product;
    private SalesmanBO salesman;
    /**
     * 投保人信息
     */
    private ApplicantBO applicant;
    /**
     * 其它
     */
    private AddtionalBO addtional;

    private List<QuestionnaireBO> questionnaire;

    @JSONField(name = "permanent_residence")
    private List<PermanentResidenceBO> permanentResidence;
}
