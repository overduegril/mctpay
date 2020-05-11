package com.mctpay.merchant.mapper.card;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.merchant.model.entity.card.MerchantCardEntity;
import com.mctpay.merchant.model.param.MerchantCardParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:01
 */
@Repository
public interface MerchantCardMapper extends BaseMapper<MerchantCardEntity> {
    /**
     * @Description 添加商户卡券
     * @Date 23:50 2020/5/6
     **/
    void insertMerchantCard(MerchantCardParam merchantCardParam);
    /**
     * @Description 卡券集合
     * @Date 23:50 2020/5/11
     **/
    List<MerchantCardEntity> listCard();
    /**
     * @Description 修改卡券
     * @Date 23:51 2020/5/11
     **/
    void updateMerchantCard(MerchantCardParam merchantCardParam);
}
