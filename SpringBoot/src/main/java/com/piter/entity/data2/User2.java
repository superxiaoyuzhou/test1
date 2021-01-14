package com.piter.entity.data2;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Piter
 * @since 2019-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User2 implements Serializable {

private static final long serialVersionUID=1L;

    private Integer id;

    private String name;

    private Integer age;


}
