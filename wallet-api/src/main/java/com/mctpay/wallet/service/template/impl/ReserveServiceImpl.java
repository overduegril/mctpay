package com.mctpay.wallet.service.template.impl;

import com.mctpay.wallet.mapper.template.ReserveMapper;
import com.mctpay.wallet.model.param.ReserveParam;
import com.mctpay.wallet.service.template.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: guodongwei
 * @Description: 预约业务
 * @Date: 2020/5/31 14:43
 */
@Service
public class ReserveServiceImpl implements ReserveService {

    @Autowired
    private ReserveMapper reserveMapper;

    @Override
    public void reserve(ReserveParam reserveParam) {
        reserveParam.setCreateTime(new Date());
        reserveParam.setUpdateTime(new Date());
        reserveParam.setStatus(1);
        reserveMapper.insert(reserveParam);
    }
}
