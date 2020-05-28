package com.mctpay.wallet.model.param;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 积分等级规则参数
 * @Date: 2020/3/2 10:30
 */
@Data
public class MemberLevelRulesParam extends BaseEntity{

    @ApiModelProperty(value = "新增数据时，不传此参数，修改时传入此参数")
    private Long id;

    @ApiModelProperty(value = "商户Id", hidden = true)
    private String merchantId;

    /**
     * 会员等级名称/
     */
    @ApiModelProperty(value = "会员等级名称")
    private String memberLevelName;
    /**
     * 对应积分
     */
    @ApiModelProperty(value = "对应积分")
    private Integer point;
    /**
     * 最后操作人
     */
    @ApiModelProperty(value = "最后操作人", hidden = true)
    private String lastOperator;

}
