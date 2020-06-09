package com.mctpay.wallet.mapper.card;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.wallet.model.entity.card.MerchantCardReceiveEntity;
import com.mctpay.wallet.model.param.MerchantCardReceiveParam;
import com.mctpay.wallet.model.param.MerchantMemberParam;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-06-05 10:04:21
 */
@Repository
public interface MerchantCardReceiveMapper extends BaseMapper<MerchantCardReceiveEntity> {

    /**
     * @Description 获取已经领取过的优惠id集合
     * @Date 19:39 2020/6/8
     **/
    List<String> listReceivedCardsByUserId(String userId);

    /**
     * 插入领取记录
     */
    void insert(MerchantCardReceiveParam cardReceiveParam);

}
