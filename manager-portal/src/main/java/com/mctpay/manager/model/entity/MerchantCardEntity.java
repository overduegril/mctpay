package com.mctpay.manager.model.entity;

import com.xtpay.common.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author dongwei_guo
 * @date 2020-02-23 18:23:01
 */
@Data
@ApiModel(value = "商家卡券")
public class MerchantCardEntity  extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "")
	private Long id;
	/**
	 * 卡券名称
	 */
	@ApiModelProperty(value = "卡券名称")
	private String cardName;
	/**
	 * 卡券类型code
	 */
	@ApiModelProperty(value = "卡券类型code")
	private String typeCode;
	/**
	 * 卡券类型名
	 */
	@ApiModelProperty(value = "卡券类型名 ")
	private String typeName;
	/**
	 * 卡券优惠类型
	 */
	@ApiModelProperty(value = "卡券优惠类型 ")
	private Integer reducedType;
	/**
	 * 优惠金额
	 */
	@ApiModelProperty(value = "优惠金额")
	private BigDecimal reduceAmount;
	/**
	 * 最低消费金额
	 */
	@ApiModelProperty(value = "最低消费金额")
	private BigDecimal minAmount;
	/**
	 * 优惠比率
	 */
	@ApiModelProperty(value = "优惠比率 ")
	private Integer reduceRate;
	/**
	 * 有效期（截止日期）
	 */
	@ApiModelProperty(value = "有效期（截止日期） ")
	private Date deadline;
	/**
	 * 使用会员的等级code
	 */
	@ApiModelProperty(value = "使用会员的等级code")
	private String requireMemberLevelCode;
	/**
	 * 库存
	 */
	@ApiModelProperty(value = "库存")
	private Integer inventoryCount;


}
