package com.mctpay.manager.model.vo.membergrade;

import com.mctpay.manager.keyvalue.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author liqiang
 * @date 2020/4/22 16:26
 * @Description: (what)
 * (why)
 * (how)
 */
@Data
@Api("新增会员等级vo")
public class EditReqVo implements Serializable {
    @ApiModelProperty(value = "主键id")
    private String id;
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
