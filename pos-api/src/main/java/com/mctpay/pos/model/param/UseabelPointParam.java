package com.mctpay.pos.model.param;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 可用积分参数
 * @Date: 2020/3/2 22:12
 */
@Data
public class UseabelPointParam extends BaseEntity{

    @ApiModelProperty(value = "", hidden = true)
    private Long id;
    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID")
    private Long userId;
    /**
     * 积分
     */
    @ApiModelProperty(value = "积分")
    private Integer point;


}
