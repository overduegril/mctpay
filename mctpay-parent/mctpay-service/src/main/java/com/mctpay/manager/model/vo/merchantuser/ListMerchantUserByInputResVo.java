package com.mctpay.manager.model.vo.merchantuser;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author liqiang
 * @date 2020/4/22 09:59
 * @Description: (what)
 * (why)
 * (how)
 */
@Data
public class ListMerchantUserByInputResVo  implements Serializable {
    private Long id;
    @ApiModelProperty(value = "登录账号")
    private String loginName;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "用户名字")
    private String name;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer gender;
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
    @ApiModelProperty(value = "折扣比例")
    private Double minDiscountRate;
}
