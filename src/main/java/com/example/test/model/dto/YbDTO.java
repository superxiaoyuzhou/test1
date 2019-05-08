package com.example.test.model.dto;

import com.example.test.model.bo.OrderBO;
import lombok.Data;

/**
 * 回调同步对象
 */
@Data
public class YbDTO {
    /**
     * 错误码
     * 0为成功 1为失败
     */
    private String code;
    /**
     * 错误内容
     * 如果code不为0 可以返回相关错误信息备查
     */
    private String msg;
    /**
     * 订单编号签名
     */
    private String sign;
    /**
     * 内容
     */
    private OrderBO data;

}
