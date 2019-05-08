package com.chinaums.wechatpre.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 'testdb.mtview' is not BASE TABLE
 * </p>
 *
 * @author lyq
 * @since 2019-04-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Table2 implements Serializable {

private static final long serialVersionUID=1L;

    private String name;

    private Integer age;

    private Integer phone;


}
