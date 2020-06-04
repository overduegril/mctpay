package com.mctpay.pos.model.entity.merchant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 可登陆商户
 * @Date: 2020/5/23 16:06
 */
@ApiModel("可登陆商户")
@Data
public class AccessibleMerchantDTO {

    @ApiModelProperty("商户Id")
    private String merchantId;


    @ApiModelProperty("商户名")
    private String merchantName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;
}
