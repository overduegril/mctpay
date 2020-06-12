package com.mctpay.pos.mapper.merchant;

import com.mctpay.common.base.mapper.BaseMapper;
import com.mctpay.pos.model.entity.merchant.TradeRecordEntity;
import com.mctpay.pos.model.param.TradeRecordParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dongwei_guo
 * @date 2020-06-03 15:49:54
 */
@Repository
public interface TradeRecordMapper extends BaseMapper<TradeRecordEntity> {

    /**
     * 插入交易记录
     *
     * @param tradeRecordParam
     */
    void insert(TradeRecordParam tradeRecordParam);

    /**
     * 通过订单号获取退款金额
     *
     * @param tradeNo
     * @return
     */
    String getAmountByTradeNo(String tradeNo);

    /**
     * 获取父订单id
     *
     * @param tradeNo
     * @return
     */
    String getPartnerTransIdByTradeNo(String tradeNo);

    /**
     * 更新订单状态
     *
     * @param tradeNo
     */
    void updateOrderStatus(@Param("tradeNo") String tradeNo, @Param("orderStatus") Integer orderStatus, @Param("partnerRefundId") String partnerRefundId);

    /**
     * 获取交易记录
     * @param merchantId
     * @return
     */
    List<TradeRecordEntity> listTradeRecordByMerchantId(@Param("merchantId") String merchantId, @Param("inputContent") String inputContent);

    /**
     * 根据订单号查看订单是否存在
     */
    Integer countByTradeNo(String tradeNo);
}
