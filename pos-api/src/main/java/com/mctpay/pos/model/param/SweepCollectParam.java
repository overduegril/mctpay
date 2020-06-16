package com.mctpay.pos.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author: guodongwei
 * @Description: 扫码收款参数
 * @Date: 2020/5/26 19:57
 */
@ApiModel("扫码收款参数")
@Data
public class SweepCollectParam {

    @NotNull
    @ApiModelProperty("收款金额保留两位有效数字")
    private BigDecimal amount;

    @NotNull
    @ApiModelProperty("付款方付款码")
    private String payer;

    @ApiModelProperty("优惠比率")
    private Integer reduceRate;

    @ApiModelProperty("优惠金额")
    private BigDecimal reduceAmount;

    @ApiModelProperty("卡券id")
    private String cardId;

    @ApiModelProperty("用户ID")
    private String userId;

    @NotNull
    @ApiModelProperty("校验字符串，一个随机字符串。用于没有获取到直接支付结果时。轮训定位")
    private String checkStr;
}
