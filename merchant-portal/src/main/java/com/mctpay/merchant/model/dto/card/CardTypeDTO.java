package com.mctpay.merchant.model.dto.card;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 卡券类型
 * @Date: 2020/6/3 10:29
 */
@Data
@ApiModel("卡券类型")
public class CardTypeDTO {

    /**
     * 卡券类型code
     */
    @ApiModelProperty(value = "卡券类型code")
    private String typeCode;
    /**
     * 卡券类型名
     */
    @ApiModelProperty(value = "卡券类型名 ")
    private String typeName;


}
