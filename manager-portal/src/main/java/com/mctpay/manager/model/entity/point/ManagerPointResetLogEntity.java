package com.mctpay.manager.model.entity.point;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:01
 */
@Data
@ApiModel(value = "积分清0日志")
public class ManagerPointResetLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private Long id;
	/**
	 * 会员Id
     */
	@ApiModelProperty(value = "会员Id")
	private Long memberId;
	/**
	 * 清除前积分
     */
	@ApiModelProperty(value = "清除前积分")
	private Integer beforeResetPoint;
	/**
	 * 操作人
     */
	@ApiModelProperty(value = "操作人")
	private String operator;
	/**
	 * 清除日期
     */
	@ApiModelProperty(value = "清除日期")
	private Date resetTime;

}
