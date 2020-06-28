package com.mctpay.pos.model.dto.card;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: guodongwei
 * @Description: 卡券使用历史
 * @Date: 2020/6/28 14:21
 */
@Data
@ApiModel("卡券使用历史")
public class CardUseHistoryDTO {

    @ApiModelProperty("卡券使用人昵称")
    private String walletName;

    @ApiModelProperty("使用时间")
    private Date useTime;

    @ApiModelProperty("卡券名字")
    private String cardName;

    @ApiModelProperty("交易单号")
    private String tradeNo;

    @ApiModelProperty("支付金额")
    private BigDecimal transAmount;

}
