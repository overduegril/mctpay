package com.mctpay.wallet.model.dto.point;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: guodongwei
 * @Description: 积分重置传输对象
 * @Date: 2020/4/2 20:15
 */
@Data
@ApiModel("积分重置传输对象")
public class PointResetDTO {

    @ApiModelProperty(value = "")
    private String id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "清除日期")
    private Date resetTime;

    @ApiModelProperty(value = "操作人")
    private String operator;

    @ApiModelProperty(value = "记录状态，1为正常，-1为冻结")
    private Integer status;
}
