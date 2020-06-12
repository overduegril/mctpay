package com.mctpay.wallet.model.param;

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
public class TradeRecordParam extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "", hidden = true)
	private Long id;
	@ApiModelProperty(value = "用户id", hidden = true)
	private String userId;
	@ApiModelProperty(value = "交易号id")
	private String tradeNo;

}
