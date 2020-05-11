package com.mctpay.merchant.service.card.impl;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.merchant.mapper.card.MerchantCardMapper;
import com.mctpay.merchant.model.param.MerchantCardParam;
import com.mctpay.merchant.service.card.MerchantCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantCardServiceImpl implements MerchantCardService {
    @Autowired
    private MerchantCardMapper merchantCardMapper;

    /**
     * @Description 插入商户兑换码
     * @Date 16:03 2020/5/11
     **/
    @Override
    public ResponseData insertMerchantCard(MerchantCardParam merchantCardParam) {
        merchantCardMapper.insertMerchantCard(merchantCardParam);
        return new ResponseData().success(null);
    }
}
