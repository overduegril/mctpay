package com.mctpay.wallet.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Author: guodongwei
 * @Description: 动态收款码请求参数
 * @Date: 2020/6/28 8:49
 */
@Data
@ApiModel("动态收款码请求参数")
public class DynamicCollectionQRCodeParam {

    @NotNull
    @ApiModelProperty("收款金额,两位小数")
    private BigDecimal amount;

    @ApiModelProperty("支付类型")
    private String payType;

    @ApiModelProperty("轮训校验码")
    private String checkStr;
}
