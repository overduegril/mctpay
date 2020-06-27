package com.mctpay.wallet.model.dto.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: guodongwei
 * @Description: 已经登录用户信息
 * @Date: 2020/3/7 10:44
 */
@Data
public class LoginedUserDTO {

    @ApiModelProperty(value = "")
    private String id;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String headpictureUrl;

    @ApiModelProperty(value = "用户Id二维码")
    private String userQurcodeUrl;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    /**
     * 该记录状态
     */
    @ApiModelProperty(value = "记录状态,1，2为激活管理员（2是有账号但是没有登录过系统管理员）;-1，-2为冻结管理员;")
    private Integer status;

    /**
     * 属于的角色
     */
    @ApiModelProperty(value = "属于的角色")
    private List<String> roles;

}
