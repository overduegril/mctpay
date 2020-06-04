package com.mctpay.merchant.model.dto.system;

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
    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "最低折扣比")
    private Integer minDiscountrRate;

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
