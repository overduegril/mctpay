package com.mctpay.wallet.model.entity.card;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dongwei_guo
 * @date 2020-06-05 10:04:21
 */
@Data
@ApiModel(value = "商户卡券领取")
public class MerchantCardReceiveEntity  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "兑换码")
	private String redeemCode;

	@ApiModelProperty(value = "卡券id")
	private String cardId;

	@ApiModelProperty(value = "领取用户id")
	private String userId;

	@ApiModelProperty(value = "领取时间")
	private Date receiveTime;

	@ApiModelProperty(value = "使用状态类型(0未使用，1已经使用)")
	private Integer useStateType;

	@ApiModelProperty(value = "是否被锁定（0未锁定，1已经锁定）")
	private Integer locked;

}
