package com.mctpay.manager.model.entity.point;

import com.xtpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:00
 */
@Data
@ApiModel(value = "可用积分，此积可分用于兑换礼物")
public class MemberUseabelPointEntity  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Long id;
	/**
	 * 会员ID
	 */
	@ApiModelProperty(value = "会员ID")
	private Long memberId;
	/**
	 * 积分
	 */
	@ApiModelProperty(value = "积分")
	private Integer point;

}
