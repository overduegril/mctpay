package com.mctpay.pos.model.param;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author dongwei_guo
 * @date 2020-06-07 14:37:14
 */
@Data
@ApiModel(value = "钱包交易记录")
public class WalletTradeRecordDTO extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Long id;

	@ApiModelProperty(value = "用户id")
	private String userId;

	@ApiModelProperty(value = "交易号id")
	private String tradeNo;

	@ApiModelProperty(value = "交易金额（新币")
	private String transAmount;

	@ApiModelProperty(value = "变动积分")
	private String changePoint;
}
