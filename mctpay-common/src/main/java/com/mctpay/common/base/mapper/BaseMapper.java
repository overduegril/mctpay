package com.mctpay.common.base.mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author: guodongwei
 * @Description: 基础mapper
 * @Date: 2020/2/23 20:05
 */
public interface BaseMapper<T> {

    /**
     * @param id
     * @return
     */
    T get(String id);

    /**
     * @param parameter
     * @return
     */
    List<T> list(Object parameter);

}
