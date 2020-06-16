package com.mctpay.wallet.model.param;

import com.mctpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author dongwei_guo
 * @date 2020-06-12 17:03:13
 */
@Data
@ApiModel(value = "收货地址")
public class ShippingAddressParam extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Long id;
	//用户id
	@ApiModelProperty(value = "用户id", hidden = true)
	private String userId;
	//用户昵称
	@ApiModelProperty(value = "用户昵称")
	private String nickname;
	//手机号
	@ApiModelProperty(value = "手机号")
	private String phoneNumber;
	//省
	@ApiModelProperty(value = "省")
	private String province;
	//市
	@ApiModelProperty(value = "市")
	private String city;
	//区
	@ApiModelProperty(value = "区")
	private String district;
	//详细地址
	@ApiModelProperty(value = "详细地址")
	private String address;
	//是否为首选地址（0不是首选地址1首选地址）
	@ApiModelProperty(value = "是否为首选地址（0不是首选地址1首选地址）")
	private Integer preferred;

}
