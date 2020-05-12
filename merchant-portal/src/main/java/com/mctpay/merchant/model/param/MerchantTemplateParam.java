package com.mctpay.merchant.model.param;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 商户模板参数
 * @Date: 2020/5/12 9:10
 */
@Data
@ApiModel("商户模板参数")
public class MerchantTemplateParam extends BaseEntity {

    @ApiModelProperty("模板ID")
    private Long templateId;

    @ApiModelProperty("模板名字")
    private String templateName;

    @ApiModelProperty(value = "商户Id", hidden = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long merchantId;

}
