package com.mctpay.manager.model.entity.system;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:22:59
 */
@Data
@ApiModel(value = "角色")
public class RoleEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Long id;
	/**
	 * 角色名
	 */
	@ApiModelProperty(value = "角色名")
	private String roleName;
	/**
	 * 角色描述
	 */
	@ApiModelProperty(value = "角色描述")
	private String description;

}
