package com.mctpay.manager.model.entity.template;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dongwei_guo
 * @date 2020-02-29 14:00:37
 */
@Data
@ApiModel(value = "预定模板")
public class ReserveTemplateEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "字段名")
	private String fieldName;

	@ApiModelProperty(value = "字段值")
	private String fieldValue;

	@ApiModelProperty(value = "是否需要定金")
	private Integer needDeposit;

	@ApiModelProperty(value = "是否需要图片")
	private Integer needPicture;

}
