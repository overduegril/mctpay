package com.mctpay.manager.model.entity.point;

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
@ApiModel(value = "会员等级规则，根据会员积分划分会员等级。")
public class MemberLevelRulesEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private Long id;
	/**
	 * 会员等级code
     */
	@ApiModelProperty(value = "会员等级code")
	private String memberLevelName;
	/**
	 * 会员等级名称/
     */
	@ApiModelProperty(value = "会员等级名称")
	private String memberLevelCode;
	/**
	 * 对应积分
     */
	@ApiModelProperty(value = "对应积分")
	private Integer point;
	/**
	 * 最后操作人
     */
	@ApiModelProperty(value = "最后操作人")
	private String lastOperator;

}
