package com.mctpay.manager.service.point.impl;

import com.mctpay.manager.mapper.point.GiftMapper;
import com.mctpay.manager.service.point.GiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: guodongwei
 * @Description: 积分
 * @Date: 2020/2/24 10:27
 */
@Service
public class GiftServiceImpl implements GiftService {

    @Autowired
    private GiftMapper giftMapper;

}
