package com.mctpay.merchant.model.entity.member;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:02
 */
@Data
@ApiModel(value = "会员")
public class MemberEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "")
	private String id;
	/**
	 * 昵称
	 */
	@ApiModelProperty(value = "昵称")
	private String nickname;
	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
	private String phoneNumber;
	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱")
	private String email;

	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "积分")
	private Integer point;

}
