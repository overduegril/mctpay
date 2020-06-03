package com.mctpay.wallet.service.merchant;

import com.mctpay.wallet.model.dto.merchant.MerchantDtO;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 商户service
 * @Date: 2020/2/24 10:28
 */
public interface MerchantService {


    /**
     * @Description 商户集合
     * @param  lat 维度
     * @param lon 精度
     * @Date 23:39 2020/2/28
     **/
    List<MerchantDtO> listMerchantByInput(double lat, double lon, String inputContent);

}
