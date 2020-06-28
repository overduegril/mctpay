package com.mctpay.wallet.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author: guodongwei
 * @Description: 调起支付页面参数
 * @Date: 2020/6/28 9:26
 */
@Data
@ApiModel("调起支付页面参数")
public class PayPageParam {

    @NotNull
    @ApiModelProperty("收款金额,两位小数")
    private BigDecimal amount;

    @ApiModelProperty("支付类型")
    private String payType;

}
