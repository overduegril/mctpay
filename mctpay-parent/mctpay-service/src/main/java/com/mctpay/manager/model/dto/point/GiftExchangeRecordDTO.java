package com.mctpay.manager.model.dto.point;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: guodongwei
 * @Description: 礼物兑换记录
 * @Date: 2020/4/13 9:04
 */
@ApiModel("礼物兑换记录")
@Data
public class GiftExchangeRecordDTO {

    @ApiModelProperty(value = "")
    private String id;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品类型名称
     */
    @ApiModelProperty(value = "商品类型名称")
    private String goodsTypeName;

    /**
     * 兑换数量
     */
    @ApiModelProperty(value = "兑换数量")
    private Integer exchangeCount;

    /**
     * 兑换日期
     */
    @ApiModelProperty(value = "兑换日期")
    private String exchangeDate;

    /**
     * 收货方式
     */
    @ApiModelProperty(value = "收货方式名")
    private String receiveWayName;


    /**
     * 收货人姓名
     */
    @ApiModelProperty(value = "收货人姓名")
    private String receivePesrsonName;

    /**
     * 收货人联系方式
     */
    @ApiModelProperty(value = "收货人联系方式")
    private String phoneNumber;

    /**
     * 收货方式
     */
    @ApiModelProperty(value = "收货地址")
    private String receiveAdderess;

    /**
     * 收货方式
     */
    @ApiModelProperty(value = "发送状态：0未发送，1已发送")
    private Integer sendStatus;


}
