package com.mctpay.pos.model.entity.system;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dongwei_guo
 * @date 2020-06-16 21:58:28
 */
@Data
@ApiModel(value = "邮箱验证码")
public class SmsCodeEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "手机号")
	private String phoneNumber;

	@ApiModelProperty(value = "验证码")
	private String code;

	@ApiModelProperty(value = "业务类型")
	private String businessType;

	@ApiModelProperty(value = "过期时间")
	private Date expirationTime;

}
