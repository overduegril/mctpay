package com.mctpay.wallet.model.dto.point;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 钱包用户积分信息DTO
 * @Date: 2020/5/31 20:53
 */
@Data
@ApiModel("钱包用户积分信息")
public class PointInfoDTO {

    @ApiModelProperty("当前积分")
    private Integer currentPoint;

    @ApiModelProperty("当前等级")
    private String currentLevel;

    @ApiModelProperty("下一级所需积分")
    private Integer nextNeedPoint;

}
