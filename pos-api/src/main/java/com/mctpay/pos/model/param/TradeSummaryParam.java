package com.mctpay.pos.model.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: guodongwei
 * @Description: 查询汇总信息参数
 * @Date: 2020/6/28 16:22
 */
@Data
@ApiModel("查询汇总信息参数")
public class TradeSummaryParam {

    @ApiModelProperty("查询类型，0 班表，1日表")
    private Date type;

    @ApiModelProperty("查询开始时间")
    private Date start;

    @ApiModelProperty("查询结束时间")
    private Date end;

    @ApiModelProperty("操作者Id")
    private String operatorId;

}
