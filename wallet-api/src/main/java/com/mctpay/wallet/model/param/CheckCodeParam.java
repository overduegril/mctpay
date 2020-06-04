package com.mctpay.wallet.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: guodongwei
 * @Description: 检查验证码参数
 * @Date: 2020/6/1 15:45
 */
@Data
@ApiModel("检查验证码参数")
public class CheckCodeParam {

    @NotNull
    @ApiModelProperty("邮箱验证码")
    private String emailCode;

    @NotNull
    @ApiModelProperty("邮箱")
    private String email;

    @NotNull
    @ApiModelProperty("类型")
    private String businessType;


}
