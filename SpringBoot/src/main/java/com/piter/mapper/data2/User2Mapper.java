package com.piter.mapper.data2;

import com.piter.entity.data2.User2;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Piter
 * @since 2019-07-03
 */

public interface User2Mapper {

    void insertTest(User2 test);

    User2 findTest(@Param("id") Integer id);
}
