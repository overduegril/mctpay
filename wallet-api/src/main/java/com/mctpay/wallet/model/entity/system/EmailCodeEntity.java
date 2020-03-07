package com.mctpay.wallet.model.entity.system;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author dongwei_guo
 * @date 2020-03-02 16:26:16
 */
@Data
@ApiModel(value = "邮箱验证码")
public class EmailCodeEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "接受邮箱")
	private String toEmail;

	@ApiModelProperty(value = "验证吗")
	private String code;

	@ApiModelProperty(value = "业务类型")
	private String businessType;

	@ApiModelProperty(value = "过期时间")
	private Timestamp expirationTime;


}
