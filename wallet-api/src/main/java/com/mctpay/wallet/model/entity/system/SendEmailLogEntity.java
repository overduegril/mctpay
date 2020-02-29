package com.mctpay.wallet.model.entity.system;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:00
 */
@Data
@ApiModel(value = "邮箱验证发送记录")
public class SendEmailLogEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Long id;
	/**
	 * 业务类型
	 */
	@ApiModelProperty(value = "业务类型")
	private String businessTypes;
	/**
	 * 接收邮箱
	 */
	@ApiModelProperty(value = "接收邮箱")
	private String acceptEmail;
	/**
	 * 邮件内容
	 */
	@ApiModelProperty(value = "邮件内容")
	private String emailContent;
	/**
	 * 发送时间
	 */
	@ApiModelProperty(value = "发送 时间")
	private Date sendTime;

}
