package com.mctpay.merchant.model.dto.card;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 优惠类型
 * @Date: 2020/6/3 10:29
 */
@Data
@ApiModel("优惠类型")
public class ReduceTypeDTO {

    /**
     * 卡券优惠类型
     */
    @ApiModelProperty(value = "卡券优惠类型")
    private String reduceType;

    /**
     * 卡券优惠类型名
     */
    @ApiModelProperty(value = "卡券优惠类型名")
    private String reduceTypeName;
}
