package com.mctpay.manager.model.vo.merchantuser;

import com.mctpay.manager.keyvalue.MerchantUserTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author liqiang
 * @date 2020/4/22 15:17
 * @Description: (what)
 * (why)
 * (how)
 */
@Data
public class EditReqVo implements Serializable {
    @Id
    private String id;

    @ApiModelProperty(value = "登录名")
    private String loginName;
    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

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
