package com.mctpay.pos.model.entity.merchant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: guodongwei
 * @Description: 交易汇总视图
 * @Date: 2020/6/28 11:16
 */
@ApiModel("交易汇总视图")
@Data
public class TradeSummaryEntity {


    @ApiModelProperty("总交易金额")
    private BigDecimal totalTradeAmount;

    @ApiModelProperty("总交易次数")
    private Integer totalTradeCount;

    @ApiModelProperty("总退款次数")
    private Integer totalRefundCount;

    @ApiModelProperty("总退款金額")
    private BigDecimal totalRefundAmount;

}
