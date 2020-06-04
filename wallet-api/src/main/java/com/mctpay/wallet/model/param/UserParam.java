package com.mctpay.wallet.model.param;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 管理员注册参数
 * @Date: 2020/2/24 20:13
 */
@Data
@ApiModel(value = "管理员注册参数")
public class UserParam extends BaseEntity {

    @ApiModelProperty(value = "id", hidden = true)
    private Long id;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "用户名", hidden = true)
    private String username;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", hidden = true)
    private String nickname;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态：0无效记录，1有效记录，-1冻结, 2未曾登录", hidden=true)
    private Integer status;
}
