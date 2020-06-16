package com.mctpay.wallet.model.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 管理员
 * @Date: 2020/2/26 19:46
 */
@Data
public class UserDTO {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户Id二维码")
    private String userQurcodeUrl;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 该记录状态
     */
    @ApiModelProperty(value = "记录状态,1，2为激活管理员（2是有账号但是没有登录过系统管理员）;-1，-2为冻结管理员;")
    private Integer status;
}
