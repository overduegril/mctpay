package com.mctpay.wallet.model.entity.template;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dongwei_guo
 * @date 2020-05-30 11:06:07
 */
@Data
@ApiModel(value = "预定表")
public class ReserveEntity  extends BaseEntity implements Serializable {
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

	@ApiModelProperty(value = "定金金额")
	private BigDecimal depositAmount;

	@ApiModelProperty(value = "预约图片")
	private String picture;

	@ApiModelProperty(value = "其余映射字段")
	private String dynamicField;


}
