package com.mctpay.manager.model.dto.point;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: guodongwei
 * @Description: 礼品DTO
 * @Date: 2020/2/24 11:00
 */
@Data
public class GiftDTO {

    @ApiModelProperty(value = "")
    private String id;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片")
    private String goodsPicture;


    /**
     * 最大兑换数量
     */
    @ApiModelProperty(value = "最大兑换数量")
    private Integer maxExchangeCount;

    /**
     * 商品类型码
     */
    @ApiModelProperty(value = "商品类型码")
    private String goodsTypeCode;
    /**
     * 商品类型名称
     */
    @ApiModelProperty(value = "商品类型名称")
    private String goodsTypeName;
    /**
     * 商品价值
     */
    @ApiModelProperty(value = "商品价值")
    private BigDecimal goodsAmount;

    /**
     * 兑换所需积分
     */
    @ApiModelProperty(value = "兑换所需积分")
    private Integer exchangePoint;

    /**
     * 兑换结束时间
     */
    @ApiModelProperty(value = "兑换结束时间")
    private Date endDate;

    /**
     * 兑换结束时间
     */
    @ApiModelProperty(value = "记录状态，-1为冻结，1为未冻结")
    private Integer status;

}
