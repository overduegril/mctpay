package com.mctpay.pos.service.merchant;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.pos.model.param.SweepCollectParam;

/**
 * @Author: guodongwei
 * @Description: 商户接口
 * @Date: 2020/5/23 21:02
 */
public interface MerchantService {

    /**
     * 获取商户会员二维码
     * @return
     */
    String getMemberQRCode();

    /**
     * 获取静态 收款二维码
     * @return
     */
    ResponseData getCollectionQRCode();

    /**
     * 获取商铺名称
     * @param merchantId
     * @return
     */
    String getMerchantName(String merchantId);

    /**
     * 扫码收款
     * @param sweepCollectParam
     */
    ResponseData sweepCollect(SweepCollectParam sweepCollectParam);
}
