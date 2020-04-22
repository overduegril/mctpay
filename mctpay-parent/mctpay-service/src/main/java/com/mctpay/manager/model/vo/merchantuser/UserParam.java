package com.mctpay.manager.model.vo.merchantuser;

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
    private String id;
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
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

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
     * 状态
     */
    @ApiModelProperty(value = "状态：0无效记录，1有效记录，-1冻结, 2未曾登录", hidden=true)
    private Integer status;
}
