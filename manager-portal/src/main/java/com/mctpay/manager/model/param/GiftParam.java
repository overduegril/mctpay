package com.mctpay.manager.model.param;
import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: chenshubiao
 * @Description: 积分商品参数
 * @Date: 2020-02-26 16:30:11
 */
@Data
@ApiModel(value = "积分商品参数")
public class GiftParam extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "", hidden = true)
    private Long id;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    /**
     * 最大兑换数量
     */
    @ApiModelProperty(value = "最大兑换数量")
    private Integer maxExchangeCount;

    /**
     * 商品类型码
     */
    @ApiModelProperty(value = "商品类型码 CARD是卡券  PHYSICAL实物")
    private String goodsTypeCode;
    /**
     * 商品类型名称
     */
    @ApiModelProperty(value = "商品类型名称 卡券，实物")
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
     * 兑换所需积分
     */
    @ApiModelProperty(value = "开始时间，格式为2020-03-06")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    /**
     * 兑换所需积分
     */
    @ApiModelProperty(value = "结束时间，格式为2020-03-06")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

}
