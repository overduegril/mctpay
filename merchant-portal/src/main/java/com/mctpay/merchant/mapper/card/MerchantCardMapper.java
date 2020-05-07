package com.mctpay.merchant.mapper.card;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.merchant.model.entity.card.MerchantCardEntity;
import com.mctpay.merchant.model.param.MerchantCardParam;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:01
 */
@Mapper
public interface MerchantCardMapper extends BaseMapper<MerchantCardEntity> {
    /**
     * @Description 商户集合
     * @Date 23:50 2020/5/6
     **/
    void insertMerchantCard(MerchantCardParam merchantCardParam);
}
