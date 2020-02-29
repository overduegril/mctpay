package com.mctpay.wallet.model.entity.system;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:00
 */
@Data
@ApiModel(value = "用户角色,对用户及角色中间表")
public class UserRoleEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Long id;
	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "用户ID")
	private Long userId;
	/**
	 * 角色ID
	 */
	@ApiModelProperty(value = "角色ID")
	private Long roleId;

}
