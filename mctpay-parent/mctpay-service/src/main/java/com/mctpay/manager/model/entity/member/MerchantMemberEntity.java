package com.mctpay.manager.model.entity.member;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:00
 */
@Data
@ApiModel(value = "商家会员映射")
public class MerchantMemberEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 商铺得到的会员虚拟ID
	 */
	@ApiModelProperty(value = "商铺得到的会员虚拟ID")
	private Long id;
	/**
	 * 商铺ID
	 */
	@ApiModelProperty(value = "商铺ID")
	private Long merchantId;
	/**
	 * 会员ID
	 */
	@ApiModelProperty(value = "会员ID")
	private Long memberId;

}
