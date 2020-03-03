package com.mctpay.manager.model.dto.point;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 积分等级设置list查询DTO
 * @Date: 2020/2/24 15:57
 */
@Data
public class MemberLevelRulesDTO {
    @ApiModelProperty(value = "")
    private Long id;
    /**
     * 会员等级code
     */
    @ApiModelProperty(value = "会员等级名称")
    private String memberLevelName;
    /**
     * 对应积分
     */
    @ApiModelProperty(value = "对应积分")
    private Integer point;

    /**
     * 状态
     */
    @ApiModelProperty(value = "记录状态")
    private Integer status;
}
