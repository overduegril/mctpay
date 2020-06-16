package com.mctpay.pos.service.merchant;

import com.mctpay.common.base.model.ResponseData;
import com.mctpay.pos.model.dto.merchant.TradeRecordDTO;
import com.mctpay.pos.model.param.PayCheckParam;
import com.mctpay.pos.model.param.SweepCollectParam;
import com.mctpay.pos.model.param.TradeRecordParam;

import java.util.List;

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
    ResponseData sweepCollect(SweepCollectParam sweepCollectParam) throws Exception;

    /**
     * 退款
     * @param order
     */
    ResponseData refund(String order);

    /**
     * 获取交易记录
     * @param merchantId
     * @return
     */
    List<TradeRecordDTO> listTradeRecord(String merchantId, String inputContent);

    /**
     * 插入交易记录
     * @param tradeRecordParam
     */
    void insertTradeRecord(TradeRecordParam tradeRecordParam);

    /**
     * 查看订单是否已经存在
     * @param tradeNo
     * @return
     */
    Integer countByTradeNo(String tradeNo);

    /**
     * 更新订单状态
     * @param orderNo
     * @param status
     * @param partnerTransId
     */
    void updateOrderStatus(String orderNo, Integer status, String partnerTransId);

    /**
     * 通过校验码获取支付结果
     * @param checkStr
     * @return
     */
    TradeRecordDTO getPayResult(String checkStr);

    /**
     * 更新添加未返回支付结果的校验订单信息
     * @param payCheckParam
     */
    void updatePayCheck(PayCheckParam payCheckParam);
}
