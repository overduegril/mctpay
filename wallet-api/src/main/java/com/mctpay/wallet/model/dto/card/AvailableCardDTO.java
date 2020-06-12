package com.mctpay.wallet.model.dto.card;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: guodongwei
 * @Description: 可用卡券
 * @Date: 2020/6/8 10:32
 */
@Data
@ApiModel("可用卡券")
public class AvailableCardDTO {

    @ApiModelProperty(value = "")
    private String id;
    /**
     * 卡券名称
     */
    @ApiModelProperty(value = "卡券名称")
    private String cardName;
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
    /**
     * 卡券优惠类型
     */
    @ApiModelProperty(value = "卡券优惠类型 ")
    private String reduceType;

    /**
     * 卡券优惠类型名
     */
    @ApiModelProperty(value = "卡券优惠类型名")
    private String reduceTypeName;
    /**
     * 优惠金额
     */
    @ApiModelProperty(value = "优惠金额")
    private BigDecimal reduceAmount;
    /**
     * 最低消费金额
     */
    @ApiModelProperty(value = "最低消费金额")
    private BigDecimal minAmount;
    /**
     * 优惠比率
     */
    @ApiModelProperty(value = "优惠比率 ")
    private Integer reduceRate;
    /**
     * 有效期（截止日期）
     */
    @ApiModelProperty(value = "有效期（截止日期） ")
    private Date deadline;
    /**
     * 使用会员的等级code
     */
    @ApiModelProperty(value = "使用会员的等级code")
    private String requireMemberLevelCode;

    /**
     * 使用会员的等级code
     */
    @ApiModelProperty(value = "使用会员的等级code")
    private String requireMemberLevelName;

    /**
     * 库存
     */
    @ApiModelProperty(value = "库存")
    private Integer inventoryCount;


    /**
     * 库存
     */
    @ApiModelProperty(value = "0可以领取，1已经领取")
    private Integer state;



}
