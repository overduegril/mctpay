package com.mctpay.manager.model.param;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 角色参数
 * @Date: 2020/5/18 15:28
 */
@ApiModel("角色参数")
@Data
public class RoleParam extends BaseEntity {

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
