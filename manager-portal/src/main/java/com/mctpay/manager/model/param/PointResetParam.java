package com.mctpay.manager.model.param;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: guodongwei
 * @Description: 积分请求参数
 * @Date: 2020/4/2 20:02
 */
@Data
@ApiModel("积分请求参数")
public class PointResetParam extends BaseEntity {

    @ApiModelProperty(value = "", hidden = true)
    private String id;

    @ApiModelProperty(value = "清除日期")
    private String resetTime;

    @ApiModelProperty(value = "操作人", hidden = true)
    private String operator;

}
