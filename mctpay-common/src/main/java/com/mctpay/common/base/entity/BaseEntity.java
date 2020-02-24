package com.xtpay.common.base.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: guodongwei
 * @Description: 实体类共有字段
 * @Date: 2020/2/23 18:28
 */
@Data
public class BaseEntity {

    /**
     * 该记录状态
     */
    @ApiModelProperty(value = "记录状态")
    private Integer status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
