package com.mctpay.pos.model.dto.merchant;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dongwei_guo
 * @date 2020-06-03 15:49:54
 */
@Data
@ApiModel(value = "交易记录")
public class TradeRecordDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "支付人信息")
	private String buyerEmail;

	@ApiModelProperty(value = "汇率")
	private String forexRate;

	@ApiModelProperty(value = "交易状态（99为交易成功）")
	private Integer tradeStatus;

    @ApiModelProperty(value = "订单状态（99为订单已付款，109为订单已退款）")
    private Integer orderStatus;

	@ApiModelProperty(value = "交易转为加币后总金额")
	private String totalFee;

	@ApiModelProperty(value = "订单号")
	private String tradeNo;

	@ApiModelProperty(value = "商户交易账号")
	private String merchant;

	@ApiModelProperty(value = "商户id")
	private String merchantId;

	@ApiModelProperty(value = "支付类型（支付宝，微信,mct等）")
	private String payType;

	@ApiModelProperty(value = "未转换前交易金额")
	private String transAmount;

	@ApiModelProperty(value = "支付时间")
	private Date payTime;

	@ApiModelProperty(value = "操作人")
	private String operator;

}
