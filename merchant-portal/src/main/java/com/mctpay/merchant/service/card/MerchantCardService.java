package com.mctpay.merchant.service.card;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.merchant.model.param.MerchantCardParam;

public interface MerchantCardService {
    /**
     * @Description 商户集合
     * @Date 23:39 2020/5/6
     **/
    ResponseData insertMerchantCard(MerchantCardParam merchantCardParam);
}
