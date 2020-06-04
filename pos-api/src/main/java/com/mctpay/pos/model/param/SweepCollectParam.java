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

}
