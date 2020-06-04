package com.mctpay.merchant.service.card;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.merchant.model.dto.card.CardDTO;
import com.mctpay.merchant.model.param.MerchantCardParam;

import java.util.List;

public interface MerchantCardService {
    /**
     * @Description 添加商户卡券
     * @Date 23:39 2020/5/6
     **/
    ResponseData insertMerchantCard(MerchantCardParam merchantCardParam);
    /**
     * @Description 修改商户集合
     * @Date 23:39 2020/5/11
     **/
    List<CardDTO> listCard(String inputContent);
    /**
     * @Description  修改商户卡券
     * @Date 23:49 2020/5/11
     **/
    ResponseData updateMerchantCard(MerchantCardParam merchantCardParam);

    /**
     * 商户商户卡券
     * @param id
     */
    void deleteMerchantCard(Long id);
}
