package com.mctpay.wallet.service.point;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.wallet.model.dto.point.GiftDTO;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 积分
 * @Date: 2020/2/24 10:28
 */
public interface GiftService {

    /**
     * @Description  列表积分商品
     * @Date 16:01  2020/2/27
     **/
    List<GiftDTO> listGiftByInput();
    /**
     * @Description  冻结激活积分商品
     * @Date 16:38  2020/2/27
     **/
    ResponseData switchGift(String giftId, Integer state);
}
