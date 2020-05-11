package com.mctpay.merchant.model.entity.template;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dongwei_guo
 * @date 2020-03-21 16:07:09
 */
@Data
@ApiModel(value = "预定模板表")
public class ReserveTemplateEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "模板名字")
	private String templateName;

	@ApiModelProperty(value = "手机号")
	private String phoneNmuber;

	@ApiModelProperty(value = "预约时间")
	private Date reserveTime;

	@ApiModelProperty(value = "人数")
	private Integer userCount;

	@ApiModelProperty(value = "备注")
	private String comment;

	@ApiModelProperty(value = "是否需要定金")
	private Integer needDeposit;

	@ApiModelProperty(value = "定金金额")
	private BigDecimal depositAmount;

	@ApiModelProperty(value = "是否需要图片")
	private Integer needPicture;

	@ApiModelProperty(value = "其余映射字段")
	private String dynamicField;

}
