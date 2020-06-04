package com.mctpay.wallet.model.entity.point;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:01
 */
@Data
@ApiModel(value = "汇总积分，用于划分会员等级")
public class SummaryPointEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private Long id;
	/**
	 * 会员ID
     */
	@ApiModelProperty(value = "用户ID")
	private Long userId;
	/**
	 * 积分
     */
	@ApiModelProperty(value = "积分")
	private Integer point;

	@ApiModelProperty(value = "会员等级名")
	private String memberLevelName;

}
