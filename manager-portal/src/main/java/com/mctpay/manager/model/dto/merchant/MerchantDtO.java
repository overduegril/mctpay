package com.mctpay.manager.model.dto.merchant;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
public class MerchantDtO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "")
    private String id;
    /**
     * 商户名
     */
    @ApiModelProperty(value = "商户名")
    private String merchantName;
    /**
     * 法人
     */
    @ApiModelProperty(value = "法人")
    private String legalPerson;
    /**
     * 身份证后4位
     */
    @ApiModelProperty(value = "身份证后4位")
    private String idLastNumber;
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
     * 注册地址
     */
    @ApiModelProperty(value = "注册地址")
    private String registeredAddress;
    /**
     * 营业地址
     */
    @ApiModelProperty(value = "营业地址")
    private String businessAddress;
    /**
     * 营业时间
     */
    @ApiModelProperty(value = "营业时间")
    private String businessTime;
    /**
     * 经营业务
     */
    @ApiModelProperty(value = "经营业务")
    private String businessContent;
    /**
     * 营业执照
     */
    @ApiModelProperty(value = "营业执照, 使用get方法访问地址可以下载此pdf")
    private String businessLicense;
    /**
     * 门头照
     */
    @ApiModelProperty(value = "门头照")
    private String shopPhoto;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String creator;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "商户状态，-1为登陆过冻结状态，-2位未登陆冻结，1为正常已登录，2未未登录")
    private String status;
}
