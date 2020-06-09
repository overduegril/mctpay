package com.mctpay.wallet.model.entity.merchant;

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
public class TradeRecordEntity  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Long id;
	@ApiModelProperty(value = "用户id")
	private String userId;
	@ApiModelProperty(value = "交易号id")
	private String tradeNo;

}
