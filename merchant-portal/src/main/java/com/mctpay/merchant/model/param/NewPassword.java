package com.mctpay.merchant.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("新密码")
public class NewPassword {

    @ApiModelProperty("新密码")
    private String newPassword;

}
