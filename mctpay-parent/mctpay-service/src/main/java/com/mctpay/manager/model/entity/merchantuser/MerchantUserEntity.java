package com.mctpay.manager.model.entity.merchantuser;

import com.mctpay.common.base.entity.BaseEntity;
import com.mctpay.manager.keyvalue.MerchantUserTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:01
 */
@Data
@ApiModel(value = "商户平台用户")
@Table(name = "merchant_user")
public class MerchantUserEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @ApiModelProperty(value = "商户密码")
    private String password;
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
    @ApiModelProperty(value = "所属商户")
    private String merchantId;
    /**
     * 账号类型
     */
    private MerchantUserTypeEnum merchantUserType;


}
