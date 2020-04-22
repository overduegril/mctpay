package com.mctpay.manager.model.dto.membergrade;

import com.mctpay.manager.keyvalue.StatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liqiang
 * @date 2020/4/22 17:11
 * @Description: (what)
 * (why)
 * (how)
 */
@Data
public class ListMemberGradeByInputResVo  implements Serializable {
    /**
     * 等级名字
     */
    @ApiModelProperty(value = "等级名字")
    private String name;

    /**
     * 对应积分
     */
    @ApiModelProperty(value = "对应积分")
    private Double points;

    /**
     * 折扣比例
     */
    @ApiModelProperty(value = "折扣比例")
    private Double discountRate;
    /*
     * 状态
     */
    @ApiModelProperty(value = "状态 空则为冻结")
    private StatusEnum status;

}
