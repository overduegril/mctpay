package com.mctpay.manager.model.vo.merchant;

import com.mctpay.manager.keyvalue.MerchantUserTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liqiang
 * @date 2020/4/22 09:21
 * @Description: (what)
 * (why)
 * (how)
 */
@Data
public class LoginedResVo implements Serializable {
    @ApiModelProperty(value = "商户id")
    private String merchantId;
    @ApiModelProperty(value = "用户id")
    private String id;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    /**
     * 该记录状态
     */
    @ApiModelProperty(value = "记录状态,1为激活，-1，-2为冻结管理员;")
    private Integer status;


    @ApiModelProperty(value = "是否是默认密码登录,用于 验证是否重置密码")
    private Boolean defaultPassword;

    /**
     * 属于的角色
     */
    @ApiModelProperty(value = "属于的角色,merchantManager为商户管理员,merchant为商户")
    private MerchantUserTypeEnum merchantUserType;
}
