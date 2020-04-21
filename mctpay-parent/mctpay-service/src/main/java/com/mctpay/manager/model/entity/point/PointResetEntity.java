package com.mctpay.manager.model.entity.point;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dongwei_guo
 * @date 2020-04-02 19:44:26
 */
@Data
@ApiModel(value = "积分清除表")
public class PointResetEntity  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private String id;

	@ApiModelProperty(value = "清除日期")
	private Date resetTime;

	@ApiModelProperty(value = "操作人")
	private String operator;

}
