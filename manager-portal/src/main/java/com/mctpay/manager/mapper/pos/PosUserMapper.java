package com.mctpay.manager.mapper.pos;

import com.mctpay.manager.model.param.PosUserParam;
import org.springframework.stereotype.Repository;

/**
 * @Author: guodongwei
 * @Description: pos 端用户
 * @Date: 2020/5/19 15:45
 */
@Repository
public interface PosUserMapper {

    void insert(PosUserParam posUserParam);

}
