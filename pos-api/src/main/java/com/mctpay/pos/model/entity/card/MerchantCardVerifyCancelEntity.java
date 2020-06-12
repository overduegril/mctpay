package com.mctpay.pos.model.entity.card;

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
@ApiModel(value = "卡券使用核销记录")
public class MerchantCardVerifyCancelEntity  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private String id;

	@ApiModelProperty(value = "卡券id")
	private String cardId;

	@ApiModelProperty(value = "兑换码")
	private String redeemCode;

	@ApiModelProperty(value = "用户id")
	private String userId;

	@ApiModelProperty(value = "使用时间")
	private Date useTime;

}
