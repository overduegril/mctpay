package com.mctpay.merchant.model.dto.member;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 会员等级
 * @Date: 2020/6/2 14:34
 */
@ApiModel("会员等级")
@Data
public class MemberLevelDTO {

    @ApiModelProperty(value = "")
    private Long id;
    /**
     * 等级
     */
    @ApiModelProperty(value = "会员等级")
    private String memberLevelName;

}
